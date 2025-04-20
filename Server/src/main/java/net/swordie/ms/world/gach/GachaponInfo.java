package net.swordie.ms.world.gach;

import java.util.ArrayList;
import java.util.List;

public class GachaponInfo {
    private List<String> messages = new ArrayList<>();
    private List<GachItem> items = new ArrayList<>();

    public GachaponInfo(List<String> messages, List<GachItem> items) {
        this.messages = messages;
        this.items = items;
    }

    public List<GachItem> getRewards() {
        return items;
    }

    public List<Integer> getHotItems() {
        List<Integer> hotItems = new ArrayList<>();
        for (GachItem i : items)
            if (i.isHotTime()) hotItems.add(i.getItemID());
        return hotItems;
    }

    public List<String> getMessages() {
        return messages;
    }

    public static class GachItem {
        private int itemID;
        private boolean hotTime;

        // TODO: handle quantity
        public GachItem(final int itemID, final boolean hotTime) {
            this.itemID = itemID;
            this.hotTime = hotTime;
        }

        public GachItem(final int itemID) {
            this.itemID = itemID;
            this.hotTime = false;
        }

        public int getItemID() {
            return itemID;
        }

        public boolean isHotTime() {
            return hotTime;
        }
    }
}
