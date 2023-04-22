# id 3 (pt01), field 450003720
if not sm.hasQuestCompleted(34302):
    sm.startQuest(34302)
    if sm.hasQuest(34302):
        sm.completeQuest(34302)
        sm.setPlayerAsSpeaker()
        sm.sendNext("(I can't pass through the fog...)")
        sm.sendNext("(I guess I should turn back for now...)")
