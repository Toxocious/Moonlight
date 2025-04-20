
sm.removeEscapeButton()

sm.setSpeakerID(1102113)
sm.sendNext("(*chirp, chirp*)")

sm.setPlayerAsSpeaker()
sm.sendSay("Look! It's a bird! Is it talking to me?")

sm.setSpeakerID(1102113)
sm.sendSay("*chirp, chirp, chirp*")

sm.setPlayerAsSpeaker()
sm.sendSay("OMIGOODNESS! I can understand birds! I must be some sort of superhero. It... wants me to follow it. I'm sure Kizan won't mind.")

sm.createQuestWithQRValue(parentID, "gardenIn")# must be sent this qr value
sm.warp(130030104, 0)