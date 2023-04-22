# id 2432732 (Special Metamorph Potion), field 811000008
sm.setSpeakerID(9130103) # Ayame
sm.setParam(5)
sm.setParam(1)
res = sm.sendNext("Who do you want to be? #b\r\n#L0#Hayato #l\r\n#L1#Kanna #l\r\n#L2#Ayame #l")
sm.startQuest(58900)
sm.startQuest(58915)
sm.setParam(5)
sm.warp(811000099)
