# Hidden Street : Temple of Time Corridor 2 (927030010)  |  Used for Luminous&Shade tutorial (so far)
ARAN = 2159441

if chr.getJob() == 2005:
    VOID_PRIEST = 2159442
    VOID_PRIEST_2 = 2159443
    VOID_PRIEST_3 = 2159444

    sm.lockInGameUI(True, False)
    sm.hideUser(False)
    sm.sendDelay(1000)

    sm.removeEscapeButton()
    sm.setSpeakerID(ARAN)
    sm.sendNext("...Ugh, Phantom and Luminous are always at it. They're almost literally opposites.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("I think they're perfect for each other. One's a chatterbox and the other is boring. They're like a never-ending stand-up routine.")

    sm.setSpeakerID(ARAN)
    sm.sendSay("Hah, true that. You know they'd take you apart if they heard you say that. Oh, look, another round of monsters coming.")

    sm.moveCamera(False, 200, 1100, 66)

    sm.spawnNpc(VOID_PRIEST, 770, 50)
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST, "summon")
    sm.sendDelay(100)

    sm.spawnNpc(VOID_PRIEST_2, 970, 50)
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_2, "summon")
    sm.sendDelay(100)

    sm.spawnNpc(VOID_PRIEST_3, 1170, 50)
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_3, "summon")
    sm.sendDelay(300)

    sm.moveCamera(True, 200, 0, 0)

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("Here, I'll take care of them.")

    sm.forcedInput(1)
    sm.moveCamera(False, 180, 1100, 66)

    sm.forcedInput(0)
    sm.sendDelay(500)

    sm.forcedAction(412, 900)
    sm.showEffectOnPosition("Skill/512.img/skill/5121007/effect", 900, -40, -25)
    sm.showEffectOnPosition("Skill/512.img/skill/5121007/effect0", 810, -40, -25)
    sm.playSound("eunwolTuto/Use", 100)
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST, "hit")
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_2, "hit")
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_3, "hit")
    sm.showNpcEffectOnPosition("Skill/512.img/skill/5121007/hit/0", -5, -50, VOID_PRIEST)
    sm.showNpcEffectOnPosition("Skill/512.img/skill/5121007/hit/0", -5, -50, VOID_PRIEST_2)
    sm.showNpcEffectOnPosition("Skill/512.img/skill/5121007/hit/0", -5, -50, VOID_PRIEST_2)
    sm.playSound("eunwolTuto/Hit", 100)
    sm.showEffectOnPosition("Skill/512.img/skill/5121020/effect", 900, -40, -25)
    sm.showEffectOnPosition("Skill/512.img/skill/5121020/effect0", 810, -40, -25)
    sm.playSound("eunwolTuto/Use", 100)
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST, "hit")
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_2, "hit")
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_3, "hit")
    sm.showNpcEffectOnPosition("Skill/512.img/skill/5121020/hit/0", -5, -50, VOID_PRIEST)
    sm.showNpcEffectOnPosition("Skill/512.img/skill/5121020/hit/0", -5, -50, VOID_PRIEST_2)
    sm.showNpcEffectOnPosition("Skill/512.img/skill/5121020/hit/0", -5, -50, VOID_PRIEST_2)
    sm.playSound("eunwolTuto/Hit", 100)
    sm.sendDelay(600)

    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST, "die")
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_2, "die")
    sm.showNpcSpecialActionByTemplateId(VOID_PRIEST_3, "die")
    sm.sendDelay(2100)

    sm.removeNpc(VOID_PRIEST)
    sm.removeNpc(VOID_PRIEST_2)
    sm.removeNpc(VOID_PRIEST_3)
    sm.sendDelay(800)

    sm.forcedInput(2)
    sm.moveCamera(True, 200, 0, 0)

    sm.forcedInput(0)
    sm.sendDelay(500)

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("Are you gonna stay?")

    sm.setSpeakerID(ARAN)
    sm.sendSay("Yes, somebody's gotta watch this hall. I'm not ready for the Black Mage just yet.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("I'll go in first. See you later. ")

    sm.setSpeakerID(ARAN)
    sm.sendSay("Okay!")

    sm.forcedInput(2)
    sm.dispose()
elif chr.getJob() == 2004:
    # TODO: replace the invokeAfterDelay to sendDelay.
    WHITE_PRIEST = 2159360
    BLACK_PRIEST = 2159361

    sm.removeEscapeButton()

    sm.lockInGameUI(True)

    sm.setCameraOnNpc(ARAN)
    sm.teleportInField(1000, 0)

    sm.spawnNpc(WHITE_PRIEST, 1320, 69)
    sm.flipNpcByTemplateId(WHITE_PRIEST, False)
    sm.spawnNpc(BLACK_PRIEST, 1240, 69)
    sm.flipNpcByTemplateId(BLACK_PRIEST, False)

    sm.invokeAfterDelay(2000, "showBalloonMsg", "Effect/Direction8.img/effect/tuto/BalloonMsg0/5", 2000)
    sm.invokeAfterDelay(3500, "showNpcSpecialActionByTemplateId", ARAN, "attack")
    sm.invokeAfterDelay(5000, "showNpcSpecialActionByTemplateId", WHITE_PRIEST, "die")
    sm.invokeAfterDelay(5000, "showNpcSpecialActionByTemplateId", BLACK_PRIEST, "die")

    sm.invokeAfterDelay(6500, "hideNpcByTemplateId", WHITE_PRIEST, True)
    sm.invokeAfterDelay(6500, "hideNpcByTemplateId", BLACK_PRIEST, True)

    sm.invokeAfterDelay(6500, "forcedMove", False, 250)
    sm.setSpeakerID(ARAN)
    sm.invokeAfterDelay(7000, "sendNext", "I figured you'd have your hands full right now, but here you are goofing around!")

    sm.setPlayerAsSpeaker()
    sm.sendNext("Aran! You're wounded! Where are Mercedes and Freud?")

    sm.setSpeakerID(ARAN)
    sm.sendNext("Ah, it's nothing. Those two got a head start on me. "
                "They might even be fighting the Black Mage right now...")

    sm.setPlayerAsSpeaker()
    sm.sendNext("Can you go on?")

    sm.setSpeakerID(ARAN)
    sm.sendNext("Aww, are you worried about me? "
                "I'm fine! Just... get in there, okay? "
                "You don't want to let Mercedes and Freud hog all the glory. "
                "I'll stay here and fend off anybody trying to get in...")

    sm.setPlayerAsSpeaker()
    sm.sendNext("...Be careful")

    sm.forcedMove(False, 500)
    sm.invokeAfterDelay(4000, "showBalloonMsg", "Effect/Direction8.img/effect/tuto/BalloonMsg0/7", 2000)
    sm.invokeAfterDelay(4500, "showEffect", "Effect/Direction8.img/effect/tuto/BalloonMsg0/6", 2000, -300, -100, 0, 0, True, 0)
    sm.invokeAfterDelay(6900, "lockInGameUI", False)
    sm.invokeAfterDelay(7000, "warp", 927020060)
    sm.dispose()