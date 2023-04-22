# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor

KINESIS = 1531000
JIN = 1531061

sm.setSpeakerID(JIN)
sm.flipSpeaker()
sm.removeEscapeButton()
sm.sendNext("#b#h0##k, did you see the news? Kinesis rescued a kid.")

sm.lockForIntro()
sm.sendDelay(1500)

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.OnOffLayer_On(1000, "0", 0, 0, 12, "Map/Effect2.img/kinesis/news1", 4, 0, -1, 0)
sm.sendDelay(1500)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("#b...Yeah, I like this feeling.#k")

sm.sendDelay(1500)

sm.OnOffLayer_Off(1500, "0", 0)
sm.sendDelay(1500)

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.unlockForIntro()
sm.setSpeakerID(JIN)
sm.flipSpeaker()
sm.removeEscapeButton()
sm.sendNext("What?")

sm.setPlayerAsSpeaker()
sm.sendSay("Hm? Oh, yeah. Here, I need you to sign this.")

sm.progressMessageFont(3, 20, 20, 0, "That's one. Two to go.")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(300)