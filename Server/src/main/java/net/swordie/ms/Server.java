package net.swordie.ms;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.connection.api.ApiAcceptor;
import net.swordie.ms.connection.netty.ChannelHandler;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.client.User;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.WorldId;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.handlers.TaskMan;
import net.swordie.ms.loaders.*;
import net.swordie.ms.connection.crypto.MapleCrypto;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.netty.ChannelAcceptor;
import net.swordie.ms.connection.netty.ChatAcceptor;
import net.swordie.ms.connection.netty.LoginAcceptor;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.Channel;
import net.swordie.ms.world.World;
import net.swordie.ms.world.shop.cashshop.CashShop;
import net.swordie.ms.world.shop.cashshop.CashShopCategory;
import net.swordie.ms.world.shop.cashshop.CashShopItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import net.swordie.ms.util.Loader;
import net.swordie.ms.util.container.Tuple;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2/18/2017.
 */
public class Server extends Properties {

	final Logger log = LogManager.getRootLogger();

	private static final Server server = new Server();

	public long upTime;
	public boolean MAINTENANCE_MODE = false;
	public boolean MAINTENANCE_ACTIVE = false;

	private List<World> worldList = new ArrayList<>();
	private Map<String, Tuple<Integer, FileTime>> authTokens = new ConcurrentHashMap<>();
	private Set<Integer> users = new HashSet<>(); // just save the ids, no need to save the references
	private CashShop cashShop;
	private Set<ScheduledFuture> shutdownFutures = new HashSet<>();
	private boolean opcodeEnc = true;
	private ScheduledFuture shutdownTimer;
	private boolean shutdownFromCommand = false;
	private boolean online = false;

	public static Server getInstance() {
		return server;
	}

	public List<World> getWorlds() {
		return worldList;
	}

	public World getWorldById(int id) {
		return Util.findWithPred(getWorlds(), w -> w.getWorldId().getVal() == id);
	}

	private void init(String[] args) {
		log.info("Starting server.");
		long startNow = System.currentTimeMillis();
		DatabaseManager.init();
		log.info("Loaded Hibernate in " + (System.currentTimeMillis() - startNow) + "ms");

		try {
			checkAndCreateDat();
			loadWzData();
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		StringData.load();
		FieldData.loadWorldMap();
		ChannelHandler.initHandlers(false);
		SkillData.loadAllSkills();
		FieldData.loadNPCFromSQL();
		DressingRoom.load();
		StyleRoom.load();

		ShutDownTask shutDownTask = new ShutDownTask();
		shutDownTask.start();

		TaskMan.FieldUpdate.getInstance().start();

		MapleCrypto.initialize(ServerConstants.VERSION);
		new Thread(new ApiAcceptor()).start();
		new Thread(new LoginAcceptor()).start();
		new Thread(new ChatAcceptor()).start();
		worldList.add(new World(ServerConfig.WORLD_ID, ServerConfig.SERVER_NAME, GameConstants.CHANNELS_PER_WORLD, ServerConfig.EVENT_MSG));
        long start = System.currentTimeMillis();
        VCoreData.loadVCoreData();
        log.info("Loaded VCore in " + (System.currentTimeMillis() - start) + "ms");

        long startCashShop = System.currentTimeMillis();
		initCashShop();
		log.info("Loaded CashShop in " + (System.currentTimeMillis() - startCashShop) + "ms");

		MonsterCollectionData.loadFromSQL();

		for (World world : getWorlds()) {
			for (Channel channel : world.getChannels()) {
				ChannelAcceptor ca = new ChannelAcceptor();
				ca.channel = channel;
				new Thread(ca).start();
			}
		}
		log.info(String.format("Finished loading server in %dms", System.currentTimeMillis() - startNow));
		new Thread(() -> {
			// inits the script engine
			log.info(String.format("Starting %s script engine.", ScriptManagerImpl.SCRIPT_ENGINE_NAME));
		}).start();

		this.online = true;

	}

	public ScheduledFuture getShutdownTimer() {
		return shutdownTimer;
	}

	public void setShutdownTimer(ScheduledFuture shutdownTimer) {
		this.shutdownTimer = shutdownTimer;
	}

	public boolean isShutdownFromCommand() {
		return shutdownFromCommand;
	}

	public void setShutdownFromCommand(boolean shutdownFromCommand) {
		this.shutdownFromCommand = shutdownFromCommand;
	}

	public void sendShutdownMessage(int time) {
		String msg = "Server is shutting down in ";
		String timeMsg = time + (!isShutdownFromCommand() ? " seconds." :" minutes. ");
		String end = "Please log off safely before the server shuts down.";
		getWorldById(1).broadcastPacket(UserLocal.chatMsg(ChatType.Notice2, "[Notice] " + msg + timeMsg + end));
		getWorldById(1).broadcastPacket(UserLocal.addPopupSay(9010063, 10000,
				"#e#b[Notice]#k#n " + msg + "#e#r" + timeMsg + "#k#n" + end, "FarmSE.img/boxResult"));
		ServerConfig.SERVER_MSG = msg + timeMsg + end;
		getWorldById(1).broadcastPacket(WvsContext.broadcastMsg(BroadcastMsg.slideNotice(ServerConfig.SERVER_MSG, true)));
	}

	private void checkAndCreateDat() {
		File file = new File(ServerConstants.DAT_DIR + "/equips");
		boolean exists = file.exists();
		if (!exists) {
			log.info("Dat files cannot be found (at least not the equip dats). All dats will now be generated. This may take a long while.");
			Util.makeDirIfAbsent(ServerConstants.DAT_DIR);
			for (Class c : DataClasses.datCreators) {
				try {
					Method m = c.getMethod("generateDatFiles");
					m.invoke(null);
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void loadWzData() throws IllegalAccessException, InvocationTargetException {
		String datFolder = ServerConstants.DAT_DIR;
		for (Class c : DataClasses.dataClasses) {
			for (Method method : c.getMethods()) {
				String name;
				Loader annotation = method.getAnnotation(Loader.class);
				if (annotation != null) {
					name = annotation.varName();
					File file = new File(datFolder, name + ".dat");
					boolean exists = file.exists();
					long start = System.currentTimeMillis();
					method.invoke(c, file, exists);
					long total = System.currentTimeMillis() - start;
					if (exists) {
						log.info(String.format("Took %dms to load from %s", total, file.getName()));
					} else {
						log.info(String.format("Took %dms to load using %s", total, method.getName()));
					}
				}
			}
		}
	}


	public Tuple<Byte, Client> getChannelFromTransfer(int charId, int worldId) {
		for (Channel c : getWorldById(worldId).getChannels()) {
			if (c.getTransfers().containsKey(charId)) {
				return c.getTransfers().get(charId);
			}
		}
		return null;
	}
	public void clearCache() {
		ChannelHandler.initHandlers(true);
		DropData.clear();
		FieldData.clear();
		ItemData.clear();
		MobData.clear();
		NpcData.clear();
		QuestData.clear();
		SkillData.clear();
		ReactorData.clear();
		EtcData.clear();
		for (World world : getWorlds()) {
			world.clearCache();
		}
	}

	public void initCashShop() {
		cashShop = new CashShop();
		try(Session session = DatabaseManager.getSession()) {
			Transaction transaction = session.beginTransaction();

			Query query = session.createQuery("FROM CashShopCategory");
			List<CashShopCategory> categories = query.list();
			categories.sort(Comparator.comparingInt(CashShopCategory::getIdx));
			cashShop.setCategories(new ArrayList<>(categories));

			query = session.createQuery("FROM CashShopItem");
			List<CashShopItem> items = query.list();
			items.forEach(item -> cashShop.addItem(item));

			transaction.commit();
		}

	}


	public class ShutDownTask {

		private static final int shutdownTime = 30000; // 30 secs

		public void start() {
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {

				log.info("Shutting down sever...");
				Server.getInstance().setOnline(false);
				if (!isShutdownFromCommand()) {
					// broadcast message if manually shutting down...
					sendShutdownMessage(shutdownTime / 1000);
					// wait for manual shut down time (shutdownTime)...
					try {
						Thread.sleep(shutdownTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// proceed to shutdown
				for (World world : getWorlds()) {
					world.shutdown();
				}

				TaskMan.FieldUpdate.getInstance().stop();

				log.info("Shutdown complete!");
			}));
		}
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}


	public CashShop getCashShop() {
		return this.cashShop;
	}

	public void addUser(User user) {
		users.add(user.getId());
	}

	public void removeUser(User user) {
		users.remove(user.getId());
	}

	public boolean isUserLoggedIn(User user) {
		return users.contains(user.getId());
	}

	public boolean isOpcodeEnc() {
		return opcodeEnc;
	}

	public void setOpcodeEnc(boolean opcodeEnc) {
		this.opcodeEnc = opcodeEnc;
	}

	private Map<String, Tuple<Integer, FileTime>> getAuthTokens() {
		return authTokens;
	}

	public void addAuthToken(byte[] token, int userID) {
		String tokenStr = new String(token);
		FileTime expiryDate = FileTime.fromDate(LocalDateTime.now().plusMinutes(ServerConstants.TOKEN_EXPIRY_TIME));
		Tuple<Integer, FileTime> entry = new Tuple<>(userID, expiryDate);
		getAuthTokens().put(tokenStr, entry);
	}

	public int getUserIdFromAuthToken(String token) {
		Tuple<Integer, FileTime> value = getAuthTokens().getOrDefault(token, null);
		if (value == null || value.getRight() == null || value.getRight().isExpired()) {
			return 0;
		} else {
			return value.getLeft();
		}
	}

	public void cancelShutdown() {
		if (shutdownFutures.size() > 0) {
			for (ScheduledFuture sf : shutdownFutures) {
				sf.cancel(false);
			}
			for (World world : getWorlds()) {
				world.broadcastPacket(UserLocal.chatMsg(ChatType.Notice2, "[Notice] Cancelled shutdown."));
			}
		}
	}


	public static void main(String[] args) {
		getInstance().init(args);
	}

}
