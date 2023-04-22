package net.swordie.ms.client.trunk;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 4/7/2018.
 */
public class TrunkUpdate implements TrunkDlg {

    private TrunkType successType;
    private Trunk trunk;

    public TrunkUpdate(TrunkType successType, Trunk trunk) {
        this.successType = successType;
        this.trunk = trunk;
    }

    @Override
    public TrunkType getType() {
        return successType;
    }

    @Override
    public void encode(OutPacket outPacket) {
        trunk.encodeItems(outPacket);
    }
}
