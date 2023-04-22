# id 863000900 (Tynerum : Gollux: Head), field 863000900
sm.startQuest(17536)
sm.startQuest(17537)
sm.lockInGameUI(True, True)
sm.setSpeakerType(3)
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(9010000) # Maple Administrator
res = sm.sendAskYesNo("Would you like to skip the cutscene?")
sm.lockInGameUI(False, True)
sm.warp(863010100)
