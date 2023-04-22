KINESIS = 1531000
JAY = 1531000
YUNA = 1531002
NERO = 1531003

sm.lockForIntro()
sm.removeAdditionalEffect()
sm.blind(1, 255, 0, 0)

sm.spawnNpc(YUNA, 760, -272)
sm.showNpcSpecialActionByTemplateId(YUNA, "summon")

sm.spawnNpc(NERO, 600, -272)
sm.showNpcSpecialActionByTemplateId(NERO, "summon")

sm.teleportInField(647, -272)
sm.forcedFlip(False)
sm.zoomCamera(0, 2000, 0, 700, -200)

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("Did you cry, Yuna?")

sm.showNpcSpecialActionByTemplateId(YUNA, "angry", -1)
sm.setIntroBoxChat(YUNA)
sm.sendSay("#face0#No, you dummy! I didn't cry.")

sm.stopNpcSpecialActionByTemplateId(YUNA)
sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#You can hug me if you like. We can make it a touching reunion.")

sm.setIntroBoxChat(YUNA)
sm.sendSay("#face0#You jerk! You have no idea...")
sm.sendSay("#face4#Ahh, nevermind. So... What's with the clothes? You look like a wizard from some video game.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face3#It's a long story. What do you think? I can pull it off, right?")

sm.sendDelay(500)
sm.zoomCamera(500, 2000, 500, 800, -200)
sm.sendDelay(sm.getAnswerVal())


sm.setIntroBoxChat(JAY)
sm.sendSay("I knew you'd be fine. ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Wow, Jay. Can't even pretend to be happy to see me?")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face2#I mean, I knew something was up because your signal kept coming from the sinkhole, but your vitals were all over the place. Strange, but I'm used to that with you. So, what happened? And who's the kid?")

sm.setIntroBoxChat(YUNA)
sm.sendSay("#face1#Oh yeah, who IS that kid?")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Jay, you're a sharp cookie. I bet you already figured out what happened to me. As for the kid, this is Nero the cat.")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face0#Ahem! I am #bNella Medionel Roonia#k. In our ancient tongue, it means half-child of the sacred moonlight. See, I'm from a real big-deal magician family...")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("You can call her Nero.")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face2#Mrrrow!")

sm.setIntroBoxChat(JAY)
sm.sendSay(" #face1#Oo-kaaay...")

sm.setIntroBoxChat(YUNA)
sm.sendSay(" #face4#Wait, so... that kid is the black cat? The black cat is this kid?")

sm.setIntroBoxChat(NERO)
sm.sendSay(" #face2#Correct! ")

sm.setIntroBoxChat(YUNA)
sm.sendSay(" #face4#")

sm.setIntroBoxChat(JAY)
sm.sendSay(" #face6#")

sm.setIntroBoxChat(KINESIS)
sm.sendSay(" #face2#Okay, so... There's a lot to go over, so let's continue this over some food. Preferably something that's not Cold Eye Tail Spicy Soup.")

sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)

sm.removeOverlapScreen(1000)
sm.unlockForIntro()
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(3000)
sm.removeNpc(YUNA)
sm.removeNpc(NERO)