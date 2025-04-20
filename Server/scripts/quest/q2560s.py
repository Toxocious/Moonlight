MONKEY = 1096003

sm.setSpeakerID(MONKEY)
sm.sendNext("Ooook! Ook! Ook!")

sm.setPlayerAsSpeaker()
sm.sendSay("Well, that hit the spot, but... I still don't understand what happened. Where's the ship? Hey, do you know what happened to me?")


sm.setSpeakerID(MONKEY)
if sm.sendAskAccept("Oook! (The monkey nods. Does he really know what's going on? Couldn't hurt to ask.)"):
    sm.startQuest(parentID)
else:
    sm.sendNext("Ook! Ook! (The monkey looks very dissatisfied.)")
    sm.dispose()