# id 913051007 (Ereve : Conference Room of the Alliance), field 913051007
sm.lockInGameUI(True, True)
sm.showFieldEffect("twilightPerion/text2", 0)
sm.sendDelay(2500)
sm.setSpeakerType(3)
sm.setSpeakerID(1105001) # Athena Pierce
sm.setParam(1)
sm.sendNext("It's impossible to know what the Black Mage's dream master is up to, but we can't let the people suffer any longer.")
sm.setSpeakerID(1105009) # Evan
sm.sendSay("How is the Alliance planning to remedy this?")
sm.setSpeakerID(1105001) # Athena Pierce
sm.sendSay("The Silent Crusade has already attempted a remedy... But it won't be enough.")
sm.sendSay("I want to ask all of you to help me calm the people of Perion before this gets out of control. It's top priority for the Alliance right now.")
sm.lockInGameUI(False, True)
sm.completeQuestNoCheck(31900)
sm.warp(913050010)
