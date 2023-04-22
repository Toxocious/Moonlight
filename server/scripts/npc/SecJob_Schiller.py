sm.setSpeakerID(parentID)
sm.sendNext("Oh my, What's this? I gave specific instructions to make sure no one else used the airport at this time. But I say, are you a member of the Resistance?")
sm.setPlayerAsSpeaker()
sm.sendNext("#b(You are surprised Schiller doesn't immediately recognize you. You certainly remember him.)")
sm.setSpeakerID(parentID)
sm.sendNext("Come ot think of it. You do look familiar... Where have I seen you before?")
sm.setPlayerAsSpeaker()
sm.sendNext("I couldn't fight you the last time we met, but i plan to fix that today.")
sm.setSpeakerID(parentID)
sm.sendNext("You! I remember now! You stole that one test subject! Do you have any idea how much I suffered because of that? I was demoted... five times! Now I'm stuck doing menial jobs like this. Time for you to pay.. oh yea.")
if sm.hasQuest(23162):
    sm.giveItem(4034787)
elif sm.hasQuest(23025):
    sm.giveItem(4032739)
elif sm.hasQuest(23024):
    sm.giveItem(4032738)
elif sm.hasQuest(23023):
    sm.giveItem(4032737)
sm.warpInstanceOut(310000000)
sm.removeNpc(parentID)