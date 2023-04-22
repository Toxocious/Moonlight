# Hidden Street : Destroyed Temple of Time Entrance (927020000) | Used in Luminous' Intro
GUWARU = 2159354
sm.removeEscapeButton()
sm.spawnNpc(GUWARU, 128, 10)
sm.flipNpcByTemplateId(GUWARU, False)
sm.showNpcSpecialActionByTemplateId(GUWARU, "summon", 0)

sm.setSpeakerID(GUWARU)
sm.sendNext("Halt! This battlefield is for you and I.")

sm.moveCamera(False, 450, -200, 18)

sm.forcedInput(1)
sm.curNodeEventEnd(True)