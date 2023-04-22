package net.swordie.ms.world.gach.result;

public enum GachaponDlgType {// or TicketEffect but nvm
    TOWN(0),
    // 1 is same as town dlg but can't click on the start.
    NEBULITE(2),
    CHAIR(3),
    MOUNT(4),
    SPECIAL(5);// or you can call it powergach
    private final int type;

    private GachaponDlgType(final int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
