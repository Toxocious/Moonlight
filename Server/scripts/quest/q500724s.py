# id 500724 ([Hayato and Kanna Revamp] Hunt 888 Monsters Near Your Level Daily), field 993017200
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9010010) # Cassandra
res = sm.sendAskYesNo("Hello, #b#h0##k! Would you like to participate in the events today as well?")
sm.sendNext("This is day 6 of 8 in your participation.")
sm.sendSay("I hope you enjoy hunting in Maple World and getting rewards.")
sm.startQuest(parentID)
sm.warp(450002007)
