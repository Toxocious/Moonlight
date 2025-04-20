# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor

KINESIS = 1531000
JOON = 1531047

sm.setSpeakerID(JOON)
sm.flipSpeaker()
sm.removeEscapeButton()
sm.sendNext("Oh, hi! Need me to sign something? Sure, anything for the Student Council President.")
sm.sendSay("Hey, I heard Kinesis caught a bank robber. Did you see the news?")

sm.lockForIntro()
sm.sendDelay(1500)

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.OnOffLayer_On(1000, "2", 0, 0, 12, "Map/Effect2.img/kinesis/news3", 4, 0, -1, 0)
sm.sendDelay(1500)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("#b...Man, that picture came out great. I'll have to repost that.#k")

sm.sendDelay(1500)

sm.OnOffLayer_Off(1500, "2", 0)
sm.sendDelay(1500)

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.unlockForIntro()

sm.removeEscapeButton()

sm.setSpeakerID(JOON)
sm.flipSpeaker()
sm.sendNext("I got the inside story on their true identity, too. You ready for this?")
sm.sendSay("Kinesis is an alien from an exploded planet who crashed on Earth! They were adopted by humans who were killed by a criminal, so they vowed to spend their life fighting crime. While training they got struck by lightning, but then saved by a magic ring from outer space.")
sm.sendSay("I mean, it's really the only logical story once you narrow it down.")

sm.setPlayerAsSpeaker()
sm.sendSay("...Comic books are destroying society.")

sm.progressMessageFont(3, 20, 20, 0, "That's all of them. Time to get this list back to Yuna.")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(300)