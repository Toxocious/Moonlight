# id 34151 ([Weekly Quest] Diligent Research Reward), field 450001000
sm.setSpeakerID(3003104) # Rona
sm.sendNext("Hello, #b#h0##k.\r\nEver since we started our investigation here, a lot of heroes have been helping us out.")
sm.sendSay("Thanks to their help, we got a lot of information on the Vanishing Journey. To express our gratitude, we will give you an extra reward when you help us with the investigation progress.")
sm.sendSay("If you help us with our research at least #btwice a week#k, we'll give you #i2436078:# x3 as a #bspecial reward#k.")
sm.createQuestWithQRValue(parentID, "first=1")
res = sm.sendAskYesNo("Will you help us with our research this week?\r\n(Once you accept, you'll receive a special reward if you complete #e#b2 or more#k#n #e#b[Daily Quest] Vanishing Journey Research#k#n quests by #e#rmidnight on Sunday#k#n.)")
sm.startQuest(parentID)
sm.createQuestWithQRValue(parentID, "startDate=19/06/18;first=1")
sm.createQuestWithQRValue(parentID, "dowS=2;startDate=19/06/18;first=1")
sm.sendNext("We look forward to your efforts, #b#h0##k.\r\nPlease be aware that the quest record will be reset after #e#rmidnight on Sunday#k#n.")
