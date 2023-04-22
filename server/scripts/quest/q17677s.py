# id 17677 ([Commerci Republic] Back to Lith Harbor), field 865000000
sm.setSpeakerID(9390256) # Leon Daniella
res = sm.sendAskYesNo("We're all ready to set sail. Did you get everything?")
sm.sendNext("All right, time to set sail! Our destination: Lith Harbor!")
sm.startQuest(parentID)
sm.warp(104000000)
