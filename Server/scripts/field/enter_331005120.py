# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331005120
# Unnamed
JAY = 1531001
YUNA = 1531002

sm.lockForIntro()
sm.removeAdditionalEffect()
sm.hideUser(True)
sm.blind(1, 255, 0, 0)
sm.zoomCamera(0, 1000, 0, 645, -260)

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.setIntroBoxChat(JAY)
sm.sendNext("I've located Kinesis.")

sm.setIntroBoxChat(YUNA)
sm.sendSay("#face3#Really?")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face1#Yes. He's in the sinkhole.")


sm.setIntroBoxChat(YUNA)
sm.sendSay("#face4#...What? That's not possible. The sinkhole destroyed everything!")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face0#I don't think so. It's not destroyed. It's missing.")


sm.setIntroBoxChat(YUNA)
sm.sendSay("#face4#That makes even LESS sense! What are you jerks hiding from me?")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face1#Yuna, we... Look, we're not just playing at being heroes here. Something is happening to world. This sinkhole thing isn't just happening here.")


sm.setIntroBoxChat(YUNA)
sm.sendSay("#face3#It... it's not? What does that even mean?")


sm.setIntroBoxChat(JAY)
sm.sendSay(".#face1#It means that parts of the Earth are disappearing all over. Our world is being... I don't know, ABSORBED by another.")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face1#It all seems like random events right now, but if my projections are correct... and they ARE, Earth is going to vanish into parts unknown in the near future.")

sm.blind(1, 255, 0, 250)
sm.sendDelay(250)

sm.OnOffLayer_On(1000, "cube", 0, 0, 12, "Map/Effect2.img/kinesis/cube", 4, 0, -1, 0)
sm.sendDelay(1500)

sm.setIntroBoxChat(JAY)
sm.sendNext("#face1#And in the midst of all this, Kinesis got their powers.")
sm.sendSay("#face1#Think about that. Forces begin destroying our world at the same time Kinesis gets control over the fundamental forces of the universe.")

sm.setIntroBoxChat(YUNA)
sm.sendSay("So... all this secrecy and heroics... it wasn't just to stroke Kinesis's ego?")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face1#Well... probably a little. But the main goal was gathering data. Data on what's tearing the planet apart... and if it's the same power that Kinesis controls.")

sm.OnOffLayer_Off(300, "cube", 0)
sm.blind(1, 255, 0, 0)
sm.sendDelay(300)

sm.blind(0, 0, 250)
sm.sendDelay(300)


sm.setIntroBoxChat(YUNA)
sm.sendNext("#face2#I had no idea... #b#h0##k never said a word about this.")


sm.setIntroBoxChat(JAY)
sm.sendSay("Well, K hates it when people worry.")


sm.setIntroBoxChat(YUNA)
sm.sendSay("#face2##b#h0##k... Wherever you are, please come back safe.")

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.hideUser(False)
sm.unlockForIntro()
sm.warp(101020400, 0)