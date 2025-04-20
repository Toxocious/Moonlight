# Hidden Street : Secret Library (701210131)

from net.swordie.ms.world.field.fieldeffect import GreyFieldType

NINE_TAILED_FOX = 9310579
KINDLY_WOODCUTTER = 9310584

sm.spawnNpc(KINDLY_WOODCUTTER, -650, 330)

sm.lockInGameUI(True, False)
sm.removeEscapeButton()

sm.sendDelay(1000)

sm.forcedMove(True, 300)
sm.sendDelay(3000)

sm.moveCamera(300, -550, 330)

sm.setBoxChat()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(Ah, that must be the Woodcutter in love with Nine-Tailed Fox. I should go talk to him.)")

sm.setSpeakerID(KINDLY_WOODCUTTER)
sm.setBoxChat()
sm.sendNext("Chop, chop, chop, chop. Thinking deep thoughts. Chop, chop, chop, chop.")

sm.moveCameraBack(700)

sm.forcedMove(True, 850)
sm.sendDelay(5000)

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Hi, there!")

sm.setSpeakerID(KINDLY_WOODCUTTER)
sm.flipNpcByTemplateId(KINDLY_WOODCUTTER, False)
sm.setBoxChat()
sm.sendNext("Hello random stranger deep in the forest. Are you lost?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Not at all. I'm looking for you! Someone wanted me to deliver this letter.")

sm.sendNext("It was #p"+ str(NINE_TAILED_FOX) +"#. She really misses you.")

sm.setSpeakerID(KINDLY_WOODCUTTER)
sm.setBoxChat()
sm.sendNext("#p"+ str(NINE_TAILED_FOX) +"#?! Gulp. She sent me a letter?!")

sm.showFieldEffect("Effect/EffectCN.img/shaolin/letter")
sm.sendDelay(4000)

sm.flipNpcByTemplateId(KINDLY_WOODCUTTER, False)
sm.showNpcSpecialActionByTemplateId(KINDLY_WOODCUTTER, "special", 120000)
sm.setFieldColour(GreyFieldType.Field, 100, 100, 100, 4000)
sm.setCameraOnNpc(KINDLY_WOODCUTTER)
sm.zoomCamera(4000, 2500, -480, 330)

sm.sendNext("Does she know where I am? Is she here?! Oh please oh please oh no no no no no!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Aren't you two lovers? Why do you sound so scared?")

sm.setSpeakerID(KINDLY_WOODCUTTER)
sm.setBoxChat()
sm.sendNext("LOVERS?! A human and a... a... a demon?! Are you serious?! I'm HIDING deep in the forest because she wants to eat my soul!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("That's not what #p"+ str(NINE_TAILED_FOX) +"# told me...")

sm.setSpeakerID(KINDLY_WOODCUTTER)
sm.setBoxChat()
sm.sendNext("I was terrified when she kept showing up. "
            "#bI told her I would do anything, absolutely anything,#k if she would just spare my life. "
            "But now she's found me, and there's nowhere left to run.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(This could be tricky. #p"+ str(NINE_TAILED_FOX) +"# may be a demon, but nobody enjoys being scorned.)")

sm.sendNext("Look, I'll handle it. She won't hunt you down. I'll... I'll think of something.")

sm.setSpeakerID(KINDLY_WOODCUTTER)
sm.setBoxChat()
sm.sendNext("Oh thank you. Thank you thank you thank you!")
sm.sendDelay(1000)

sm.forcedMove(False, 1000)
sm.sendDelay(4000)

sm.warpInstanceOut(701210130, 2)
sm.completeQuest(62015)
sm.giveExp(1010226)
sm.createQuestWithQRValue(62015, "1")
sm.lockInGameUI(False)