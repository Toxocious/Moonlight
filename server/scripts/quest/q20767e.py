# (Lv60) Advanced Knight

advancedKnightMedal = 1142068

if sm.canHold(advancedKnightMedal):
    sm.setSpeakerID(1101000)
    sm.sendNext("You are training well! However, you have a lot to learn still. Take this to remember the cause and what it means to be a Knight of Cygnus"
                "\r\n\r\n1x #v"+ str(advancedKnightMedal) +"##z"+ str(advancedKnightMedal) +"#")
    sm.giveItem(advancedKnightMedal)
    sm.completeQuestNoRewards(parentID)
    sm.dispose()
else:
    sm.dispose()
