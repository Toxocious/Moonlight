# id 450001250 (Cave of Repose : Damp Falls), field 450001250
if not sm.hasQuestCompleted(34120):
    sm.completeQuestNoCheck(34120)
    sm.startQuest(34162)
    sm.setSpeakerID(3003143) # Pile of Clothes
    sm.setParam(2)
    sm.sendNext("#b(You picked up the #t1712001:# that Kao behind.)#k")
    sm.giveAndEquip(1712001)
    sm.consumeItem(1712000)
    sm.startQuest(28857)
    sm.sendNext("ueet")