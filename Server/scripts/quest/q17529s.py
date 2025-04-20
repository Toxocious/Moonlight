# id 17529 ([Gollux] The Smart Way), field 863010000
sm.setSpeakerID(9390120) # Heart Tree Guardian
sm.sendNext("As I told you before, you can subdue Gollux by damaging its head.")
sm.sendSay("The three major weak points will make that battle easier. The shoulders and abdomen seem to be the core of its strength.")
res = sm.sendAskYesNo("I'll give you a mission. Damage all three of Gollux's main power sources, then attack its head. Do you want to give it a try?")
sm.startQuest(parentID)
sm.createQuestWithQRValue(17533, "ing=1")
sm.createQuestWithQRValue(17533, "ing=1;1=0")
sm.sendNext("Good. Attack all of Gollux's weak spots, then return to me.")
