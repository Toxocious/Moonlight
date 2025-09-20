package net.swordie.ms.enums;

/**
 * Created on 1/2/2018.
 */
public enum ChatType {
    Normal(0),
    Whisper(1),
    GroupParty(2),
    GroupFriend(3),
    GroupGuild(4),
    GroupAlliance(5),
    GameDesc(6), //Gray text
    Tip(7), //Yellow text
    Notice(8), //LightYellow
    Notice2(9), //Blue text
    AdminChat(10), //Background white, text black
    SystemNotice(11),//Background white, text black
    SpeakerChannel(12), //Background lightblue, text blue
    SpeakerWorld(13), //Bg pink - text magenta (Like smega)
    SpeakerWorldGuildSkill(14),//Bg pink - text magenta (Like smega x2)
    ItemSpeaker(15), // Bg yellow - text black
    ItemSpeakerItem(18),// Bg yellow - text black
    SpeakerBridge(19),
    SpeakerWorldExPreview(20),
    Mob(21),//Yellow text
    Expedition(22),//Yellow text
    ItemMessage(23), // BG yellow - text black
    MiracleTime(24), //Text blue
    LotteryItemSpeaker(25),
    LotteryItemSpeakerWorld(26),
    AvatarMegaphone(27),
    PickupSpeakerWorld(28),
    WorldName(29), //ligthyellow
    BossArenaNotice(30), //Text purple
    Claim(31),
    AfreecaTv(32),
    // non kmst from here
    GachaReward(31),
    GachaRed(32),
    GachaRed2(33), // same as GachaRed(32)
    DarkBlue2(34), // same as ItemSpeakerItem(16)
    ItemNoItemSmegaDarkText(35),
    WhiteOnGreen(36),
    CakeSpeaker(37),
    PieSpeaker(38),
    BlackOnWhite(39),
    ;
    private short val;

    ChatType(short val) {
        this.val = val;
    }

    ChatType(int i) {
        this((short) i);
    }

    public short getVal() {
        return val;
    }
}
