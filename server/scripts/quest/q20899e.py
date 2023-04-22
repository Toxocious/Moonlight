# [Skill] (Lv.200) Cygnus Constellation

cygnusConstellationMedal = 1142597

if sm.canHold(cygnusConstellationMedal):
    sm.setSpeakerID(1101000)
    sm.sendNext("You have exceeded all our expectations, please take this as a symbol of your Heroism\r\n\r\n1x #v"+ str(cygnusConstellationMedal) +"##z"+ str(cygnusConstellationMedal) +"#")
    sm.completeQuestNoRewards(parentID)
    sm.giveItem(cygnusConstellationMedal)
sm.dispose()
