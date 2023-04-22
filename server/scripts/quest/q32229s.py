# id 32229 (Explorer Thief - Shadower), field 610050000
sm.setSpeakerID(1052001) # Dark Lord
sm.sendNext("You chose the path of a Shadower. So, how much do you know about the Explorer's abilities?")
res = sm.sendAskAccept("Do you want to learn more about the new Explorer basics? I've got time.\r\n#r(Click Yes to move to the tutorial.)#k")
sm.startQuest(parentID)
sm.warp(910370111)
