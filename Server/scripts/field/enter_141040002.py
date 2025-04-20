# Riena Strait : Barbara's Memories (141040002)  |  Riena Strait Questline ending cutscene

BARBARA_20Y_AGO = 1514003
YOUNG_PUTAN = 1514004
YOUNG_ALVESH = 1514005
YOUNG_DACHI = 1514006

sm.lockInGameUI(True)
sm.hideUser(True)
sm.removeEscapeButton()

sm.setSpeakerID(BARBARA_20Y_AGO)
sm.sendNext("Are you guys... hungry?")

sm.setSpeakerID(YOUNG_ALVESH)
sm.sendSay("Woof, woof!")

sm.setSpeakerID(YOUNG_PUTAN)
sm.sendSay("Quak~ Quaaak~!!")

sm.setSpeakerID(YOUNG_DACHI)
sm.sendSay("Ongong!")

sm.setSpeakerID(BARBARA_20Y_AGO)
sm.sendSay("What are you saying? Speak English you brats, whew...")

sm.sendSay("You small ones... They are opening up their mouths to be fed. It is their desire to live.")

sm.setSpeakerID(YOUNG_ALVESH)
sm.sendSay("Woof, woof!")

sm.setSpeakerID(YOUNG_PUTAN)
sm.sendSay("Eep!")

sm.setSpeakerID(YOUNG_DACHI)
sm.sendSay("Ongong?")

sm.setSpeakerID(BARBARA_20Y_AGO)
sm.sendSay("Ok, ok. I'll find you food. Let's see.. Is there anywhere I can catch fish?")

sm.warp(141040003, 0)
