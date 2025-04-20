MOONBEAM = 3002000

sm.setPlayerAsSpeaker()
sm.sendNext("The town seems restless, different from other days. Did something happen?")

sm.setSpeakerID(MOONBEAM)
sm.sendSay("...Dunno.")

sm.setPlayerAsSpeaker()
sm.sendSay("(Something must be up. Moonbeam isn't herself either.)")
sm.sendSay("Okay.")

sm.setSpeakerID(MOONBEAM)
sm.sendSay("...I'm sorry.")

sm.setPlayerAsSpeaker()
sm.sendSay("Why?")

sm.setSpeakerID(MOONBEAM)
sm.sendSay("Just... everything. I'm sorry. I'm really, really sorry...")

sm.startQuest(parentID)

sm.setPlayerAsSpeaker()
sm.sendSay("Moonbeam! Why is she crying all of a sudden? I should look into it myself.")


