# id 32120 ([Ellinel Fairy Academy] Dr. Betty's Measures), field 101000000
sm.setSpeakerID(1032104) # Betty
sm.sendNext("I have studied the magical forests around the academy a great deal. It's difficult to navigate, but I created a tool that can help you at least identify which directions sounds are coming from. \r\n\r\n#i4033830##b#t4033830##k")
if sm.sendAskAccept("I'm not sure how helpful it will be, but it's better than nothing. Now, I've got to go before my lab explodes. \r\n\r\n#b(You will move to Ellinel Fairy Academy if you accept.)#k"):
    if sm.canHold(4033830):
        sm.giveItem(4033830)
        sm.startQuest(parentID)
        sm.warp(101071300)
    else:
        sm.sendNext("You don't have enough inventory space to hold this Directional Sonar.")