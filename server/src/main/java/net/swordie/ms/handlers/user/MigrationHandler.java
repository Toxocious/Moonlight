package net.swordie.ms.handlers.user;

import net.swordie.ms.Server;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.User;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.HyperTPRock;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.items.HotTimeReward;
import net.swordie.ms.client.character.skills.TownPortal;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.friend.result.FriendResult;
import net.swordie.ms.client.jobs.JobManager;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.client.party.PartyResult;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.ClientSocket;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.loaders.NpcData;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.auction.AuctionResult;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Portal;
import net.swordie.ms.world.shop.cashshop.CashShop;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static net.swordie.ms.enums.ChatType.Notice2;

public class MigrationHandler {

    private static final Logger log = Logger.getLogger(MigrationHandler.class);


    @Handler(op = InHeader.MIGRATE_IN)
    public static void handleMigrateIn(Client c, InPacket inPacket) {
        int worldId = inPacket.decodeInt();
        int charId = inPacket.decodeInt();
        byte[] machineID = inPacket.decodeArr(16);
        Tuple<Byte, Client> info = Server.getInstance().getChannelFromTransfer(charId, worldId);
        byte channel = info.getLeft();
        Client oldClient = info.getRight();
        //   if (!oldClient.hasCorrectMachineID(machineID)) {
        //      c.write(WvsContext.returnToTitle());
        //      return;
        //  }
        c.setMachineID(machineID);
        c.setOldChannel(oldClient.getOldChannel());
        User user = oldClient.getUser();
        c.setUser(user);
        Account acc = oldClient.getAccount();
        c.setAccount(acc);
        Server.getInstance().getWorldById(worldId).getChannelById(channel).removeClientFromTransfer(charId);
        c.setChannel(channel);
        c.setWorldId((byte) worldId);
        c.setChannelInstance(Server.getInstance().getWorldById(worldId).getChannelById(channel));
        Char chr = oldClient.getChr();
        if (chr == null || chr.getId() != charId) {
            chr = acc.getCharById(charId);
        }

        chr.renewDragon();

        user.setCurrentChr(chr);
        user.setCurrentAcc(acc);
        chr.setUser(user);
        chr.setClient(c);
        chr.setAccount(acc);
        acc.setCurrentChr(chr);
        acc.setUser(user);
        acc.getUnion().setAccount(acc);
        chr.initEquips();
        chr.initDamageSkin();
        chr.initAndroid(false);
        chr.initBaseStats();
        c.setChr(chr);
        c.getChannelInstance().addChar(chr);
        if (chr.getJobHandler() == null) {
            chr.setJobHandler(JobManager.getJobById(chr.getJob(), chr));
        }
        chr.setInstance(null);
        Server.getInstance().addUser(user);
        Field field = chr.getOrCreateFieldByCurrentInstanceType(chr.getFieldID() <= 0 ? 100000000 : chr.getFieldID());
        if (Server.getInstance().isOpcodeEnc()) {
            c.sendOpcodeEncryption(charId);
        }
        if (chr.getHP() <= 0) { // automatically revive when relogging
            chr.heal(chr.getMaxHP() / 2);
        }
        // blessing has to be split up, as adding skills before SetField is send will crash the client
        chr.initBlessingSkillNames();
        chr.setOnline(true); // v195+: respect 'invisible login' setting

        // if (user.getAccountType() == AccountType.Admin) {
          //   chr.setHide(true);
          // }

        if (chr.getPartyID() != 0) {
            Party party = c.getWorld().getPartybyId(chr.getPartyID());
            if (party == null) {
                chr.setPartyID(0);
            } else {
                for (PartyMember member : party.getMembers()) {
                    if (member.getCharID() == chr.getId() && member.getCharName().equals(chr.getName())) {
                        chr.setParty(party);
                    }
                }
            }
        }
        chr.warp(field, true);

       // if (chr.isHide()) {
      //      chr.chatMessage("Hide is enabled.");
      //  }

        chr.initBlessingSkills();
        c.write(WvsContext.updateEventNameTag(new int[]{}));
        if (chr.getGuild() != null) {
            chr.setGuild(chr.getClient().getWorld().getGuildByID(chr.getGuild().getId()));
        }
        if (JobConstants.isBeastTamer(chr.getJob())) {
            c.write(FieldPacket.beastTamerFuncKeyMappedManInit(chr.getFuncKeyMaps()));
        } else {
            c.write(FieldPacket.funcKeyMappedManInit(chr.getFuncKeyMap()));
        }
        c.write(FieldPacket.quickslotInit(chr.getQuickslotKeys()));
        chr.setBulletIDForAttack(chr.calculateBulletIDForAttack());
        c.write(WvsContext.friendResult(FriendResult.loadFriends(chr.getAllFriends())));
        c.write(WvsContext.macroSysDataInit(chr.getMacros()));
        c.write(UserLocal.damageSkinSaveResult(DamageSkinType.Req_SendInfo, null, chr));
        acc.getMonsterCollection().init(chr);
        chr.checkAndRemoveExpiredItems();
        chr.updatePartyHP();
        chr.getOffenseManager().setChr(chr);
        chr.getJobHandler().giveHyperAfBuff();
        acc.initAuctions();
        chr.recalcStats(EnumSet.of(BaseStat.mhp, BaseStat.mmp));
        chr.checkHotTimeRewards();
    }


    @Handler(op = InHeader.USER_TRANSFER_FIELD_REQUEST)
    public static void handleUserTransferFieldRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (inPacket.getUnreadAmount() == 0) {
            // Coming back from the cash shop
//            chr.warp(chr.getOrCreateFieldByCurrentInstanceType(chr.getFieldID()));
            c.getChannelInstance().addClientInTransfer(c.getChannel(), chr.getId(), c);
            c.write(ClientSocket.migrateCommand(true, (short) c.getChannelInstance().getPort()));
            return;
        }
        byte fieldKey = inPacket.decodeByte();
        int targetField = inPacket.decodeInt();
        int x = inPacket.decodeShort();
        int y = inPacket.decodeShort();
        String portalName = inPacket.decodeString();
        if (portalName != null && !"".equals(portalName)) {
            Field field = chr.getField();
            Portal portal = field.getPortalByName(portalName);
            if (portal.getScript() != null && !portal.getScript().equals("")) {
                chr.getScriptManager().startScript(portal.getId(), portal.getScript(), ScriptType.Portal);
            } else {
                Field toField = chr.getOrCreateFieldByCurrentInstanceType(portal.getTargetMapId());
                if (toField == null) {
                    return;
                }
                Portal toPortal = toField.getPortalByName(portal.getTargetPortalName());
                if (toPortal == null) {
                    toPortal = toField.getPortalByName("sp");
                }
                chr.warp(toField, toPortal);
            }
        } else if (chr.getHP() <= 0) {
            // Character is dead, respawn request
            inPacket.decodeByte(); // always 0
            byte tarfield = inPacket.decodeByte(); // ?
            byte reviveType = inPacket.decodeByte();
            int returnMap = chr.getField().getReturnMap();
            switch (reviveType) {
                // so far only got 0?
            }
            if (!chr.hasBuffProtector()) {
                chr.getTemporaryStatManager().removeAllStats();
            }
            int deathcount = chr.getDeathCount();
            if (deathcount != 0) {
                if (deathcount > 0) {
                    deathcount--;
                    chr.setDeathCount(deathcount);
                    chr.write(UserLocal.deathCountInfo(deathcount));
                }
                chr.warp(chr.getOrCreateFieldByCurrentInstanceType(returnMap));
            } else {
                if (chr.getParty() != null) {
                    //   chr.getParty().clearFieldInstances(0);
                } else {
                    if (chr.getTransferField() == targetField && chr.getTransferFieldReq() == chr.getField().getId()) {
                        Field toField = chr.getOrCreateFieldByCurrentInstanceType(chr.getTransferField());
                        if (toField != null && chr.getTransferField() > 0) {
                            chr.warp(toField);
                        }
                        chr.setTransferField(0);
                        return;
                    } else {
                        chr.warp(chr.getOrCreateFieldByCurrentInstanceType(chr.getField().getForcedReturn()));
                    }
                }
            }
            chr.heal(chr.getMaxHP());
            chr.setBuffProtector(false);
        } else {
            if (chr.getTransferField() == targetField && chr.getTransferFieldReq() == chr.getField().getId()) {
                Field toField = chr.getOrCreateFieldByCurrentInstanceType(chr.getTransferField());
                if (toField != null && chr.getTransferField() > 0) {
                    chr.warp(toField);
                }
                chr.setTransferField(0);
            }
        }
    }

    @Handler(op = InHeader.USER_PORTAL_SCRIPT_REQUEST)
    public static void handleUserPortalScriptRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        byte portalID = inPacket.decodeByte();
        String portalName = inPacket.decodeString();
        Portal portal = chr.getField().getPortalByName(portalName);
        String script = portalName;
        if (portal != null) {
            portalID = (byte) portal.getId();
            script = "".equals(portal.getScript()) ? portalName : portal.getScript();
            chr.getScriptManager().startScript(portalID, script, ScriptType.Portal);
        } else {
            chr.chatMessage("Could not find that portal.");
        }
    }


    @Handler(op = InHeader.USER_TRANSFER_CHANNEL_REQUEST)
    public static void handleUserTransferChannelRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        byte channelId = (byte) (inPacket.decodeByte() + 1);
        if (c.getWorld().getChannelById(channelId) == null) {
            chr.chatMessage("Could not find that world.");
            return;
        }
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.MigrateLimit.getVal()) > 0
                || channelId < 1 || channelId > c.getWorld().getChannels().size()
                 || chr.getInstance() != null) {
            chr.dispose();
            return;
        }
        chr.changeChannel(channelId);
    }

    @Handler(op = InHeader.USER_MIGRATE_TO_CASH_SHOP_REQUEST)
    public static void handleUserMigrateToCashShopRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.MigrateLimit.getVal()) > 0) {
            chr.dispose();
            return;
        }
        chr.getScriptManager().startScript(9010063, 0, "dressing_room", ScriptType.Npc);
    }

    @Handler(op = InHeader.USER_MAP_TRANSFER_REQUEST)
    public static void handleUserMapTransferRequest(Char chr, InPacket inPacket) {
        chr.punishLieDetectorEvasion();

        byte mtType = inPacket.decodeByte();
        byte itemType = inPacket.decodeByte();

        MapTransferType mapTransferType = MapTransferType.getByVal(mtType);
        switch (mapTransferType) {
            case DeleteListRecv: //Delete request that's received
                int targetFieldID = inPacket.decodeInt();
                HyperTPRock.removeFieldId(chr, targetFieldID);
                chr.write(WvsContext.mapTransferResult(MapTransferType.DeleteListSend, itemType, chr.getHyperRockFields()));
                break;

            case RegisterListRecv: //Register request that's received
                targetFieldID = chr.getFieldID();
                Field field = chr.getField();
                if (field == null || (field.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0) {
                    chr.chatMessage("You may not warp to that map.");
                    chr.dispose();
                    return;
                }
                HyperTPRock.addFieldId(chr, targetFieldID);
                chr.write(WvsContext.mapTransferResult(MapTransferType.RegisterListSend, itemType, chr.getHyperRockFields()));
                break;

        }
    }

    @Handler(op = InHeader.USER_FIELD_TRANSFER_REQUEST)
    public static void handleUserFieldTransferRequest(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0
                || (field.getFieldLimit() & FieldOption.MigrateLimit.getVal()) > 0
                || (field.getFieldLimit() & FieldOption.PortalScrollLimit.getVal()) > 0
                || !field.isChannelField()) {
            chr.chatMessage("You may not warp to that map.");
            chr.dispose();
            return;
        }
        int fieldID = inPacket.decodeInt();
        if (fieldID == 7860) {
            Field ardentmill = chr.getOrCreateFieldByCurrentInstanceType(GameConstants.ARDENTMILL);
            chr.warp(ardentmill);
        }
    }

    @Handler(op = InHeader.MAKE_ENTER_FIELD_PACKET_FOR_QUICK_MOVE)
    public static void handleMakeEnterFieldPacketForQuickMove(Char chr, InPacket inPacket) {
        int templateID = inPacket.decodeInt();
        if (chr == null) {
            return;
        }
        Field field = chr.getField();
        QuickMoveInfo qmi = GameConstants.getQuickMoveInfos().stream().filter(info -> info.getTemplateID() == templateID).findFirst().orElseGet(null);
        if (qmi == null) {
            chr.dispose();
            chr.getOffenseManager().addOffense(String.format("Attempted to use non-existing quick move NPC (%d).", templateID));
            return;
        }
        if (qmi.isNoInstances() && field.isChannelField()) {
            chr.dispose();
            chr.getOffenseManager().addOffense(String.format("Attempted to use quick move (%s) in illegal map (%d).", qmi.getMsg(), field.getId()));
            return;
        }
        Npc npc = NpcData.getNpcDeepCopyById(templateID);
        String script = npc.getScripts().get(0);
        if (script == null) {
            script = String.valueOf(npc.getTemplateId());
        }
        chr.getScriptManager().startScript(npc.getTemplateId(), templateID, script, ScriptType.Npc);

    }

    @Handler(op = InHeader.ENTER_TOWN_PORTAL_REQUEST)
    public static void handleEnterTownPortalRequest(Char chr, InPacket inPacket) {
        int chrId = inPacket.decodeInt(); // Char id
        boolean town = inPacket.decodeByte() != 0;

        Field field = chr.getField();
        TownPortal townPortal = field.getTownPortalByChrId(chrId);
        if (townPortal != null) {       // TODO Using teleports, as grabbing the TownPortalPoint portal id is not working
            if (town) {
                // townField -> fieldField
                Field fieldField = townPortal.getChannel().getField(townPortal.getFieldFieldId());

                chr.warp(fieldField); // Back to the original Door
                chr.write(FieldPacket.teleport(townPortal.getFieldPosition(), chr)); // Teleports player to the position of the TownPortal
            } else {
                // fieldField -> townField
                Field returnField = townPortal.getChannel().getField(townPortal.getTownFieldId()); // Initialise the Town Map,

                chr.warp(returnField); // warp Char
                chr.write(FieldPacket.teleport(townPortal.getTownPosition(), chr));
                if (returnField.getTownPortalByChrId(chrId) == null) { // So that every re-enter into the TownField doesn't spawn another TownPortal
                    returnField.broadcastPacket(WvsContext.townPortal(townPortal)); // create the TownPortal
                    returnField.addTownPortal(townPortal);
                }
            }
        } else {
            chr.dispose();
            log.warn("Character {" + chrId + "} tried entering a Town Portal in field {" + field.getId() + "} which does not exist."); // Potential Hacking Log
        }
    }


    @Handler(op = InHeader.USER_MIGRATE_TO_MONSTER_FARM)
    public static void handleUserMigrateMonsterFarmRequest(Char chr, InPacket inPacket) {
        chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Farm is currently disabled.")));
        chr.dispose();
        return;
    }

    @Handler(op = InHeader.USER_MIGRATE_AUCTION_REQUEST)
    public static void handleUserMigrateAuctionRequest(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.MigrateLimit.getVal()) > 0 || !chr.isInValidState() || chr.getInstance() != null) {
            chr.dispose();
            return;
        }
        if (field.isTown() && !chr.isDead()) {
            chr.enterNewStageField();
            chr.write(Stage.setAuctionField(chr));
        } else {
            chr.getOffenseManager().addOffense("Tried to go to the auction house without being in a town.");
            chr.dispose();
        }
    }

    @Handler(op = InHeader.DIMENSIONAL_MIRROR_WARP_REQUEST)
    public static void handleDimensionalMirrorWarpRequest(Char chr, InPacket inpacket) {
        chr.getClient().verifyTick(inpacket);
        int ID = inpacket.decodeInt();
        if (ID >= 500) {
            ID = ID - 500 + 13;
        }
        if (DimensionalMirrorType.values()[ID].getReqLv() > chr.getLevel()) {
            chr.chatMessage(Notice2, "You haven't reached the required level yet.");
            chr.dispose();
            return;

        }
        if (DimensionalMirrorType.values()[ID].getQuestID() != 0 && !chr.getQuestManager().hasQuestCompleted(DimensionalMirrorType.values()[ID].getQuestID())) {
            chr.chatMessage(Notice2, "You haven't finished the required quest yet");
            chr.dispose();
            return;
        }
        int toMap = DimensionalMirrorType.values()[ID].getWarpTo();
        Field toField = chr.getClient().getChannelInstance().getField(toMap);
        if (toField == null || !chr.getField().isTown()) {
            chr.chatMessage("Can only warp from a town.");
            chr.dispose();
            return;
        }
        chr.warp(toField);
    }

    @Handler(op = InHeader.AUCTION_LEAVE_REQUEST)
    public static void handleAuctionLeaveRequest(Char chr, InPacket inPacket) {
        chr.setOnline(true);
        chr.warp(chr.getField());
    }

    @Handler(op = InHeader.USER_TRANSFER_FREE_MARKET_REQUEST)
    public static void handleTransferFreeMarketRequest(Char chr, InPacket inPacket) {
        byte toChannelID = (byte) (inPacket.decodeByte() + 1);
        int fieldID = inPacket.decodeInt();
        if (chr.getWorld().getChannelById(toChannelID) != null && GameConstants.isFreeMarketField(fieldID)
                && GameConstants.isFreeMarketField(chr.getField().getId())) {
            Field toField = chr.getClient().getChannelInstance().getField(fieldID);
            if (toField == null) {
                chr.dispose();
                return;
            }
            int currentChannelID = chr.getClient().getChannel();
            if (currentChannelID != toChannelID) {
                chr.changeChannelAndWarp(toChannelID, fieldID);
            } else {
                chr.warp(toField);
            }
        }

        inPacket.decodeInt(); // tick
    }

}