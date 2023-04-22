CUTTER = 1096005

sm.setSpeakerID(CUTTER)
if sm.sendAskAccept("You're back! Great. I got the Ignition Device all hooked up, so we can get back to civilization. Nothing left to do here, right? Let's roll!"):
    sm.startQuest(parentID)
    sm.warp(912060200, 0)
else:
    sm.sendNext("You're not done here? What could you POSSIBLY want to do on a mostly-deserted island?")
    sm.dispose()