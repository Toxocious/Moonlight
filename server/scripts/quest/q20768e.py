# (Lv100) Chief Knight

chiefKnightMedal = 1142069

if sm.canHold(chiefKnightMedal):
    sm.setSpeakerID(1101000)
    sm.sendNext("You are training well! However, you have a lot to learn still. Take this to remember the cause and what it means to be a Knight of Cygnus"
                "\r\n\r\n1x #v"+ str(chiefKnightMedal) +"##z"+ str(chiefKnightMedal) +"#")
    sm.giveItem(chiefKnightMedal)
    sm.completeQuestNoRewards(parentID)
    sm.dispose()
else:
    sm.dispose()
