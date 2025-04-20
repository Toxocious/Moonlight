# id 32115 ([Ellinel Fairy Academy] Clue Number Two), field 101072500
sm.setSpeakerID(1500023) # Hidey Hole
if sm.sendAskAccept("There's something weird over here. Should we check it out?"):
    sm.setParam(2)
    sm.sendNext("#i4033829# \r\n\r\nThere's so much clothing up here... Some of them look weird.")
    sm.sendSay("#i1052196##i1050168##i1052495#\r\n\r\nI knew it! These are stage costumes! I'd better get this back to Cootie.")
    if sm.canHold(4033829):
        sm.startQuest(parentID)
        sm.giveItem(4033829)
    else:
        sm.sendNext("You can't hold the Fairy Stage Costumes because you don't have sufficient inventory space.")
