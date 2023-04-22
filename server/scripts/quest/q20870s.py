# WA - End of Knight-in-Training - Start
sm.setSpeakerID(1101002)
if not sm.getFieldID() == 130000000:
    response = sm.sendAskYesNo("#h #, you have done surprisingly well. Do you wish to take the #b Knighthood Exam#k? If you pass, you will become a full-fledged knight. #b\r\n(Note, if you accept, you will be ported to Ereve. Talk to your instructor there.)")
    if response:
        sm.warp(130000000)
        sm.dispose()
    else:
        sm.sendSayOkay("Okay, maybe next time.")
if sm.sendAskAccept("#h #? Your level is so much higher! It seems that you finished many quests... what about taking a test for knights? It should be good time to end your trainee life."):
    sm.sendSayOkay("To take the test of knight, Come to Ereve. The captains will test your ability and will appoint you as the knight of the clan if you pass it.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
