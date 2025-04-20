# Portal to Cross world party quest
sm.setSpeakerID(9076003)
response = sm.sendNext("What Party quest would you like to do? \r\n#L0#Moon Bunny's rice cake#l \r\n #L1#First Time together#l")

if response == 0:
    sm.warp(933000000, 0)
elif response == 1:
    sm.warp(933010000, 0)