JAY = 1531001

sm.setSpeakerID(JAY)
sm.sendNext("I knew you could handle that, K.")
sm.sendSay("By the way, you look like garbage. It's a new look for you.")
sm.sendSay("I bet your power level rose since I saw you last. I can't WAIT to chart you.")

if sm.sendAskYesNo("You wanna go ahead and update your data? Say yes.\r\n\r\n#b(Accept for 2nd Job Advancement.)#k\r\n\r\n#b#i1142864# #t1142864#\r\n#b#i1353201# #t1353201#"):
    sm.giveItem(1353201)
    sm.giveItem(1142864)
    sm.setJob(14210)
    sm.addSP(4, True)
    sm.completeQuest(parentID)
    sm.giveExp(8000)
    sm.sendSayOkay("Boom. Upgrade complete. Why not try out the goods?")
else:
    sm.sendSayOkay("Fine, I'll be here all day. My guild is raiding tonight.")