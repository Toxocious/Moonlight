# id 32117 ([Ellinel Fairy Academy] Graduate Search), field 101072000
sm.setSpeakerID(1500001) # Headmistress Ivana
res = sm.sendAskAccept("Do you know Arwen or Rowen from Ellinia?  They are former Ellinel Fairy Academy graduates. They might know of some places we teachers do not.\r\n\r\n  #e#b(You will be moved to Ellinia if you accept.)#k")
sm.sendNext("Please meet Arwen the Fairy in Ellinia.")
sm.startQuest(parentID)
sm.warp(101000000)
