# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
THREE_MOON = 1531004

sm.setIntroBoxChat(THREE_MOON)
sm.sendNext("So, our meal has been secured. Was the struggle great?")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("Nero, can you translate for me? I'm not following Wolfshirt here at all.")


sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("Breaking the shell of an egg is a simple matter. It requires only the use of force in the right place.")
sm.sendSay("But you, you hold a far greater power, contained within its own egg.")
sm.sendSay("You must keep this power contained, lest it break free of its own shell, and shatter the shell of the world itself.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("...What does that even mean?")


sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("Perhaps one day, you will know.")

sm.completeQuest(parentID)
# take 10 Bird Egg
sm.giveExp(5400)