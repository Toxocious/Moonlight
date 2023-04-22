package net.swordie.ms.life.npc;

import net.swordie.ms.util.Util;

/**
 * Created on 2/19/2018.
 */
public enum NpcMessageType {
    SayOk(0, false, false, ResponseType.Response),
    SayNext(0, false, true, ResponseType.Response),
    SayPrev(0, true, false, ResponseType.Response),
    Say(0, true, true, ResponseType.Response),
    SayUnk(1, ResponseType.Response),
    SayImage(2, ResponseType.Response),
    AskYesNo(3, ResponseType.Response),
    AskText(4, ResponseType.Text),
    AskNumber(5, ResponseType.Answer),
    AskMenu(6, ResponseType.Answer),
    InitialQuiz(7, ResponseType.Answer),
    InitialSpeedQuiz(8, ResponseType.Answer),
    ICQuiz(9, ResponseType.Answer),
    AskAvatar(10, ResponseType.Answer),
    AskAndroid(11, ResponseType.Answer),
    AskMannequin(12, ResponseType.Answer),
    AskPet(13, ResponseType.Answer),
    AskPetAll(14, ResponseType.Answer),
    AskActionPetEvolution(15, ResponseType.Answer),
    AskAccept2(17, ResponseType.Response),
    AskAccept(18, ResponseType.Response),
    AskBoxtext(19, ResponseType.Answer),
    AskSlideMenu(20, ResponseType.Answer),
    AskIngameDirection(21, ResponseType.Response),
    PlayMovieClip(22, ResponseType.Response),
    AskCenter(23, ResponseType.Answer),
    // 24
    AskAvatar2(25, ResponseType.Answer),
    AskSelectMenu(26, ResponseType.Answer),
    AskAngelicBuster(27, ResponseType.Answer),
    SayIllustration(28, ResponseType.Answer),
    SayDualIllustration(29, ResponseType.Answer),
    AskYesNoIllustration(30, ResponseType.Answer),
    AskAcceptIllustration(31, ResponseType.Answer),
    AskMenuIllustration(32, ResponseType.Answer),
    AskYesNoDualIllustration(33, ResponseType.Answer),
    AskAcceptDualIllustration(34, ResponseType.Answer),
    AskMenuDualIllustration(35, ResponseType.Answer),
    // 36
    AskAvatarZero(37, ResponseType.Answer),
    AskAvatar3(38, ResponseType.Answer),
    AskAvatar3Zero(39, ResponseType.Answer), // not sure
    AskWeaponBox(41, ResponseType.Answer),
    AskBoxTextBgImg(42, ResponseType.Answer),
    AskUserSurvey(43, ResponseType.Answer),
    Monologue(44, ResponseType.Response),
    AskMixHair(45, ResponseType.Answer),
    AskMixHairExZero(46, ResponseType.Answer),
    OnAskCustomMixHair(47, ResponseType.Answer),
    OnAskCustomMixHairAndProb(48, ResponseType.Answer),
    OnAskMixHairNew(49, ResponseType.Answer),
    OnAskMixHairNewExZero(50, ResponseType.Answer),
    // 51
    OnAskScreenShinningStarMsg(52, ResponseType.Answer),
    // 53
    // 54
    OnAskNumberUseKeyPad(55, ResponseType.Answer),
    OnSpinOffGuitarRhythmGame(56, ResponseType.Answer),
    OnGhostParkEnter(57, ResponseType.Answer),
    OnGhostParkEnter2(58, ResponseType.Answer), // new v212
    //65
    //66
    //68
    //69
    //70

    None(-1, ResponseType.Answer),
    ;

    private byte val;
    private boolean prevPossible, nextPossible;
    private int delay;
    private ResponseType responseType;

    NpcMessageType(int val, ResponseType responseType) {
        this.val = (byte) val;
        prevPossible = false;
        nextPossible = false;
        this.responseType = responseType;
    }

    NpcMessageType(int val, boolean prev, boolean next, ResponseType responseType) {
        this.val = (byte) val;
        prevPossible = prev;
        nextPossible = next;
        this.responseType = responseType;
    }

    public static NpcMessageType getByVal(byte val) {
        return Util.findWithPred(values(), v -> v.getVal() == val);
    }

    public byte getVal() {
        return val;
    }

    public boolean isPrevPossible() {
        return prevPossible;
    }

    public boolean isNextPossible() {
        return nextPossible;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }


    public ResponseType getResponseType() {
        return responseType;
    }

    public enum ResponseType {
        Response, Answer, Text
    }
}
