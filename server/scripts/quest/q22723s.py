# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor

KINESIS = 1531000
YOUNG = 1531046

sm.setSpeakerID(YOUNG)
sm.flipSpeaker()
sm.removeEscapeButton()
sm.sendNext("Hey there, #b#h0##k! Did you see this picture in the paper? My dumb friend is convinced it's you. We've been arguing all morning.")

sm.lockForIntro()
sm.sendDelay(1500)

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.OnOffLayer_On(1000, "1", 0, 0, 12, "Map/Effect2.img/kinesis/news2", 4, 0, -1, 0)
sm.sendDelay(1500)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("#b...Oh yeah, I remember that. Good one.#k")

sm.sendDelay(1500)

sm.OnOffLayer_Off(1500, "1", 0)
sm.sendDelay(1500)

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.unlockForIntro()

sm.removeEscapeButton()

sm.setSpeakerID(YOUNG)
sm.flipSpeaker()
sm.sendNext("What?")

sm.setPlayerAsSpeaker()
sm.sendSay("What? I didn't say anything.")

sm.setSpeakerID(YOUNG)
sm.flipSpeaker()
sm.sendSay("The really weird part is that they're saying this Kinesis person uses psyro... psino... mind powers to move things.")
sm.sendSay("That's just ridiculous, right? No one can do that, right #b#h0##k?")
sm.sendSay("Think about it. Here, I'll sign that thing for you.")

sm.progressMessageFont(3, 20, 20, 0, "Two down, one to go.")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(300)