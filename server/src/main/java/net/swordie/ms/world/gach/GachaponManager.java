package net.swordie.ms.world.gach;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.GachaponDlg;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.gach.result.GachaponDlgType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GachaponManager {
    private Map<GachaponDlgType, List<Tuple<Item, Short>>> rewards = new HashMap<>();

    public GachaponManager() {
        rewards.put(GachaponDlgType.TOWN, new ArrayList<>());
        rewards.put(GachaponDlgType.NEBULITE, new ArrayList<>());
        rewards.put(GachaponDlgType.CHAIR, new ArrayList<>());
        rewards.put(GachaponDlgType.MOUNT, new ArrayList<>());
        rewards.put(GachaponDlgType.SPECIAL, new ArrayList<>());
    }

    public List<Tuple<Item, Short>> getRewards(GachaponDlgType dialog) {
        return rewards.get(dialog);
    }

    public void addItem(GachaponDlgType dialog, Item item, short quantity) {
        List<Tuple<Item, Short>> rewarded = rewards.get(dialog);
        if (rewarded == null) {
            return;
        }
        rewarded.add(new Tuple<>(item, quantity));
        rewards.put(dialog, rewarded);
    }

    public OutPacket encode(GachaponDlgType dlg) {
        GachaponInfo info = GachaponConstants.getGachaponInfo().get(dlg);
        if (info == null) {
            GachaponConstants.init();
        }
        return GachaponDlg.openGach(dlg, info.getMessages(), getRewards(dlg), info.getHotItems());
    }
}
