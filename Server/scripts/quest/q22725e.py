# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001000
# Hideout  :: HQ
KINESIS = 1531000
JAY = 1531001

sm.setIntroBoxChat(JAY)
sm.sendNext("There you are. Wash your hands before you touch any of my stuff.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Give it a rest, you polish those things like twice a day. Tell me what you found.")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face3#Ok. This is really cool.")

sm.lockForIntro()
sm.changeBGM("Bgm43.img/Unknown Part Of City", 0, 0)
sm.blind(1, 200, 0, 1300)
sm.sendDelay(1600)

sm.setIntroBoxChat(JAY)
sm.sendNext("I've been tracing someone online known only as WM. Their messages have been popping up on message boards all over.")
sm.sendSay("It was always a big block of text, maybe some kind of code. A few cryptos tried to decode it, but most people ignored it.")
sm.sendSay("But then we noticed that the timestamps on the posts lined up with the weird stuff happening in the city. Whenever WM posts, monsters show up.")
sm.sendSay("That kicked off this weird cult following, with people saying WM was a prophet...")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Ridiculous. Do people online believe anything?")

sm.setIntroBoxChat(JAY)
sm.sendSay("HEY. Hey. Some of us are smart enough to recognize the real meaning behind all this.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Does that mean you cracked WM's code, Jay?")

sm.setIntroBoxChat(JAY)
sm.sendSay("What else? I needed something to keep me awake this morning.")
sm.sendSay("My colleagues couldn't crack it because WM is using a local TV broadcast as part of his hash. The code itself was arranged like packet transfers, so I just set up a comparison array script and ran it against all broadcasts for an hour or so. Simple.")
sm.sendSay("It's almost like WM wanted it decrypted.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Do you think the police know?")

sm.setIntroBoxChat(JAY)
sm.sendSay("Seriously? The police here probably don't know videos have comment sections. I hacked their database anyway, and there's nothing in their cyber division.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("So... what does the code say?")

sm.setIntroBoxChat(JAY)
sm.sendSay("It describes exactly where and when the monsters will appear. Now, the most recent one says that the next attack... #bwill be at your school#k.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face4#...Really.")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face0#That can't be a coincidence, K.")

sm.blind(0, 0, 0, 1300)
sm.sendDelay(1600)

sm.completeQuest(parentID)
sm.giveExp(700)
sm.unlockForIntro()