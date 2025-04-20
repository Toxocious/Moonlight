# Portal for Hayato Tutorial cutsence when approaching Princess | Honnou-ji Temple Plaza (807100004)
# Author: Tiger

PRINCESS_NO = 9131005

sm.lockInGameUI(True)
sm.removeEscapeButton()
sm.forcedInput(0)

sm.setPlayerAsSpeaker()
sm.sendNext("Please step aside, madam, I have no quarrel with you.")

sm.setSpeakerID(PRINCESS_NO)
sm.flipDialogue()
sm.sendSay("How dare you speak to me in such a familiar tongue. I am Kichou, daughter of the Saitou Dousan, lawful wife of the Demon King!")

sm.setPlayerAsSpeaker()
sm.sendSay("The tales of your beauty are not exaggerations, Lady No, but you must stand aside. Your husband is my sworn enemy.")

sm.setSpeakerID(PRINCESS_NO)
sm.flipDialogue()
sm.sendSay("Cocky little brat! You will be me for death before I am done!")

sm.showNpcSpecialActionByTemplateId(PRINCESS_NO, "step")
sm.sendDelay(1000)

sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/14", 2000, PRINCESS_NO)
sm.sendDelay(2000)

sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/15", 2000, PRINCESS_NO)
sm.sendDelay(2000)

sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/16", 2000, PRINCESS_NO)
sm.sendDelay(2000)

sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/17", 2000)
sm.sendDelay(2000)

sm.forcedInput(1)
sm.sendDelay(1000)

sm.forcedInput(7)
sm.sendDelay(200)

sm.warp(807040000)
sm.dispose() # needed here or script will never stop for the next player
