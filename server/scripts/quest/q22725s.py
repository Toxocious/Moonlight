# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor
KINESIS = 1531000
JAY = 1531001

sm.setNpcOverrideBoxChat(JAY)
sm.sendNext("#face9#Kinesis, you need to get back here as soon as you can. I've discovered something very interesting.")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("What is it? The last time you said that, it was a new torrent site for your Japanese cartoons.")

sm.setNpcOverrideBoxChat(JAY)
sm.sendSay("#face9#It's called ANIME, you plebeian. And no, this is about the monsters you fought the other day.")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#face4#Talk to me, Jay. What did you find?")

sm.setNpcOverrideBoxChat(JAY)
sm.sendSay("#face9#Dude, just come back here. I'm trying to be all dramatic.")
sm.sendSay("#face9#And pick up a tub of ice cream on the way. I'm out.")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#b(Return to the Hideout and talk to Jay.)#k")
sm.setQuestEx(22700, "q21end", "0")
sm.startQuest(parentID)