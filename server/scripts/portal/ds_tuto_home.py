if sm.hasQuest(23201) or sm.hasQuestCompleted(23201):
    sm.dispose()
elif not sm.hasQuest(23200) and not sm.hasQuestCompleted(23200):
    sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon1")
    sm.startQuestNoCheck(23200)
elif sm.hasQuest(23200):
    sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0")
sm.dispose();