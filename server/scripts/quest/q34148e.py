# id 34148 ([Daily Quest] Deliver 30 Oblivion Inhibitor), field 450001013
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(34127, "count=1;order=icstfenujqodmakphrblg")
sm.setSpeakerID(3003107) # Jenna
sm.sendNext("I see that you brought the 30 #t4034934:# items that Rona from HQ requested.")
sm.sendSay("Let's see. One, two, three... Yep, 30 #t4034934:# items... This is for you: #i1712001:# #t1712001:# x1. We'll be able to continue our investigation thanks to you.")
sm.startQuest(16689)
sm.warp(450001112)
