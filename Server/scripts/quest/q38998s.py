# 410000002
MOONBEAM = 3002100
CATERPILLAR = 9300877

sm.spawnMob(CATERPILLAR, -245, -410, False)
sm.spawnMob(CATERPILLAR, -245, -410, False)
sm.removeEscapeButton()
sm.setSpeakerID(MOONBEAM)
sm.sendNext("Do you see that caterpillar over there? Waaaaay over there. It's gonna eat up the whole Fox Tree if we leave it there. Now then, what should we do?")
sm.sendSay("I hope you're not thinking of climbing all the way up there! Use #b[Fox Trot]#k to zip up there in a jiffy! Go get'em, go on!")
sm.chatScript("Use [Fox Trot] to get to the bugs on the trees!")
sm.startQuest(parentID)


