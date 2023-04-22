package net.swordie.ms.world.gach;

import net.swordie.ms.util.Randomizer;
import net.swordie.ms.world.gach.result.GachaponDlgType;

import java.util.*;

public class GachaponConstants {
    private static final Map<GachaponDlgType, GachaponInfo> gachaponInfo = new HashMap<>();
    private static List<GachaponInfo.GachItem> equipments;
    private static List<GachaponInfo.GachItem> nebulites;

    static {
        init();
    }

    public static Map<GachaponDlgType, GachaponInfo> getGachaponInfo() {
        return gachaponInfo;
    }

    public static void init() {
        initNebulite();
        initEquipment();
        List<GachaponInfo.GachItem> items;
        // Town Gachapon
        String[] messages = new String[]{"Swordie Town Gachapon", "Text 1", "Text 2"};
        items = new ArrayList<>(equipments);
        items.addAll(nebulites);
        gachaponInfo.put(GachaponDlgType.TOWN, new GachaponInfo(Arrays.asList(messages), items));

        // Nebulite Gachapon
        messages = new String[]{"Swordie Nebulite Gachapon", "Text 1", "Text 2"};
        items = new ArrayList<>(nebulites);
        gachaponInfo.put(GachaponDlgType.NEBULITE, new GachaponInfo(Arrays.asList(messages), items));

        // Chair Gachapon
        messages = new String[]{"Swordie Chair Gachapon", "Text 1", "Text 2"};
        items = new ArrayList<>();
        gachaponInfo.put(GachaponDlgType.CHAIR, new GachaponInfo(Arrays.asList(messages), items));

        // Mount Gachapon
        messages = new String[]{"Swordie Mount Gachapon", "Text 1", "Text 2"};
        items = new ArrayList<>();
        gachaponInfo.put(GachaponDlgType.MOUNT, new GachaponInfo(Arrays.asList(messages), items));

        // Special Gachapon
        messages = new String[]{"Swordie Special Gachapon", "Text 1", "Text 2"};
        items = new ArrayList<>();
        gachaponInfo.put(GachaponDlgType.SPECIAL, new GachaponInfo(Arrays.asList(messages), items));
    }

    private static void initEquipment() {
        equipments = new ArrayList<>();
        // Empress Set
        for (int i = 1152108; i <= 1152113; i++) equipments.add(new GachaponInfo.GachItem(i));// Shoulder
        for (int i = 1003172; i <= 1003176; i++) equipments.add(new GachaponInfo.GachItem(i));// Helmet
        for (int i = 1102275; i <= 1102279; i++) equipments.add(new GachaponInfo.GachItem(i));// Cape
        for (int i = 1082295; i <= 1082299; i++) equipments.add(new GachaponInfo.GachItem(i));// Gloves
        for (int i = 1052314; i <= 1052318; i++) equipments.add(new GachaponInfo.GachItem(i));// Suit
        for (int i = 1072485; i <= 1072489; i++) equipments.add(new GachaponInfo.GachItem(i));// Shoes
        equipments.add(new GachaponInfo.GachItem(1302152));// Lionheart Cutlass
        equipments.add(new GachaponInfo.GachItem(1312065));// Lionheart Champion Axe
        equipments.add(new GachaponInfo.GachItem(1322096));// Lionheart Battle Hammer
        equipments.add(new GachaponInfo.GachItem(1402095));// Lionheart Battle Scimitar
        equipments.add(new GachaponInfo.GachItem(1432086));// Lionheart Fuscina

        // Other
        equipments.add(new GachaponInfo.GachItem(3063171));
        equipments.add(new GachaponInfo.GachItem(2430307));
        equipments.add(new GachaponInfo.GachItem(1302229));
        equipments.add(new GachaponInfo.GachItem(1003176));
        equipments.add(new GachaponInfo.GachItem(1252018));
        equipments.add(new GachaponInfo.GachItem(1072489));
        equipments.add(new GachaponInfo.GachItem(3015094));
        equipments.add(new GachaponInfo.GachItem(1032110));
        equipments.add(new GachaponInfo.GachItem(1132160));
        // Hot Items
        equipments.add(new GachaponInfo.GachItem(3063231, true));
        equipments.add(new GachaponInfo.GachItem(1052315, true));
        equipments.add(new GachaponInfo.GachItem(1362022, true));
    }

    private static void initNebulite() {
        nebulites = new ArrayList<>();
        nebulites.add(new GachaponInfo.GachItem(3063231, true));
        nebulites.add(new GachaponInfo.GachItem(3063231, true));
        nebulites.add(new GachaponInfo.GachItem(3063231, true));
        for (int i = 3060000; i <= 3064490; i++) {// alot of null nebulites.. need to handle it in another way
            //nebulites.add(new GachaponInfo.GachItem(i));
        }
    }

    public static int getRandomItem(GachaponDlgType dlg) {
        GachaponInfo gachapon = gachaponInfo.get(dlg);
        if (gachapon == null) {
            return -1;
        }
        List<GachaponInfo.GachItem> rewards = gachapon.getRewards();
        if (rewards == null || rewards.size() <= 0) {
            return -1;
        }
        return rewards.get(Randomizer.nextInt(rewards.size())).getItemID();
    }

    public static GachaponDlgType getDlgByTicket(final int ticketID) {
        switch (ticketID) {
            case 5220000:
                return GachaponDlgType.TOWN;
            case 5220098:
                return GachaponDlgType.NEBULITE;
            case 5220097:
                return GachaponDlgType.CHAIR;
            case 5220099:
                return GachaponDlgType.MOUNT;
            case 5220100:
                return GachaponDlgType.SPECIAL;
            case 5451000:// Remote Gachapon Ticket TODO: handle remote gach
                return null;
        }
        return null;
    }
}
