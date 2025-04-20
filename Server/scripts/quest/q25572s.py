# Created by MechAviv
# Quest ID :: 25572
# Grendel's Deal

sm.setSpeakerID(1032001)
sm.sendNext("Where did you learn Dark magic? Not just anyone can pick it up.")


sm.setSpeakerID(1032001)
sm.setPlayerAsSpeaker()
sm.sendSay("It is no business of yours. Now, if you value your life, you'll tell me all you know of the darkness.")


sm.setSpeakerID(1032001)
sm.sendSay("I could spend all year teaching you about Dark magic, but why should I? Especially after what you did to Lolo...")


sm.setSpeakerID(1032001)
sm.setPlayerAsSpeaker()
sm.sendSay("You should thank me for ridding you of that pest!")


sm.setSpeakerID(1032001)
sm.sendSay("Power of dark consumed you more than expected. I may be old, I'm no pushover.")


sm.setSpeakerID(1032001)
sm.setPlayerAsSpeaker()
sm.sendSay("Ah, power of darkness is still trying to come out. I don't mean all those belligerent words that are coming out. Please understand. Whew... what should I do?")


sm.setSpeakerID(1032001)
sm.sendSay("Harumph! At this rate, we're liable to blow each other to smithereens. Why don't we discuss this like civilized people? I'm sure we can make a deal of some sort.")


sm.setSpeakerID(1032001)
sm.setPlayerAsSpeaker()
sm.sendSay("Tell me what I must do.")


sm.setSpeakerID(1032001)
sm.sendSay("Give me just a second. Let's see here...")


sm.setSpeakerID(1032001)
sm.sendSay("The monsters around Ellinia have been causing a ruckus lately. If you take care of them for me, then I'll find what you're looking for.")


sm.setSpeakerID(1032001)
sm.removeEscapeButton()
sm.sendSay("(I've never felt such darkness before. Is this my karma for dabbling in Dark magic when I was a kid? Don't worry, Lolo. I'll save you...)")

sm.startQuest(25572)
sm.completeQuest(25572)
sm.giveExp(1001)