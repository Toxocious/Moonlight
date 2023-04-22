# id 17530 ([Gollux] The Rewarding Way), field 863010000
sm.setSpeakerID(9390120) # Heart Tree Guardian
sm.sendNext("A display of strength may prove effective. Do you feel up to the task?")
res = sm.sendAskYesNo("It will be more dangerous, but if you can defeat Gollux's head with only 2 of the weak points broken, the rewards could be greater.")
sm.startQuest(parentID)
sm.createQuestWithQRValue(17533, "ing=1;1=1")
sm.createQuestWithQRValue(17533, "ing=1;1=0")
sm.sendNext("A brave choice. Defeat Gollux with only 2 of the weak points broken, then return to me.")
