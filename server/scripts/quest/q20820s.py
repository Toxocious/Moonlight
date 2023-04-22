# Start of The City of Ereve
KIMU = 1102004
sm.setSpeakerID(KIMU)
sm.removeEscapeButton()

sm.sendNext("Welcome to Ereve! This is the safest and most peaceful place in all of Maple World. "
"Empress Cygnus keeps it nice all the time!\r\n"
"You're #b#h ##k, right? Here to join the #p1064023# Knights. I'm your guide, #p" + str(KIMU) + "#. All the Noblesses in town come to me first!")

sm.sendSay("You need to get over to the Knight's Orientation right away. They're getting started already. Follow me, okay?")

sm.completeQuestNoRewards(parentID)
sm.warp(130030100) # Knight Orientation Area
