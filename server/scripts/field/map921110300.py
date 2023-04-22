SHADE_THIRD_JOB = 38074

if sm.hasQuest(38074):
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.flipSpeaker()
    sm.sendNext("This castle used to be so grand, and now it's all in ruins. If I remember correctly there's a way in there somewhere.")
    sm.completeQuestNoRewards(SHADE_THIRD_JOB)
