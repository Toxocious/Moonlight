#   [Job Adv] (5th job)   5th Job : Call of the erdas

sm.setSpeakerID(2140001)
sm.sendNext("You have come far in the pursuit of rare and incredible power. But in your journeys, did you ever find yourself lost, or unsure of how to proceed?")
if sm.sendAskYesNo("We have meditated on this problem for ages, and at last we may have a solution. Not just for finding power, but for surpassing your limits. If you are interested, come find me in the Temple of Time. \r\n\r\n\r\n #b(Accepting this quest will take you to the Temple of Time for your #e5th Job Advancement#n.)"):
    sm.startQuest(parentID)
    sm.addQRValue(parentID, "1")
    sm.warp(270010111, 0)
sm.dispose()
