GUARD1 = 2159340
GUARD2 = 2159341
J_AGENT = 2159342

sm.lockInGameUI(True)
sm.completeQuestNoRewards(23207)
sm.deleteQuest(23207)

sm.spawnNpc(GUARD1, 175, 0)
sm.showNpcSpecialActionByTemplateId(GUARD1, "summon", 0)
sm.spawnNpc(GUARD2, 300, 0)
sm.showNpcSpecialActionByTemplateId(GUARD2, "summon", 0)
sm.spawnNpc(J_AGENT, 600, 0)
sm.showNpcSpecialActionByTemplateId(J_AGENT, "summon", 0)
sm.showNpcSpecialActionByTemplateId(GUARD1, "panic", 0)
sm.showNpcSpecialActionByTemplateId(GUARD2, "panic", 0)

sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, J_AGENT)
sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, GUARD1)
sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, GUARD2)
sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg2/0", 1500)# for player ofc
sm.sendDelay(1500)

sm.forcedInput(0)
sm.removeEscapeButton()
sm.setSpeakerID(GUARD1)
sm.sendNext("W-what is that?")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg2/1", 2000)
sm.sendDelay(900)

sm.setPlayerAsSpeaker()
sm.sendNext("(What's going on? My Fury is...nearly gone! And what is this thing? Did it take my power...?)")

sm.setSpeakerID(GUARD2)
sm.sendSay("T-this can't be happening...!")

sm.setPlayerAsSpeaker()
sm.sendSay("What did you do to me? This energy...is it the Black Mage's energy?")

sm.setSpeakerID(GUARD1)
sm.sendSay("Need to capture that person to avoid interrogation...")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/16", 2000)
sm.sendDelay(1500)

sm.fadeInOut(600, 1500, 600, 150)
sm.forcedAction(372, 0)
sm.showEffect("Skill/3112.img/skill/31121006/effect", 0, 0, 0, 0, 0, False, 0)# should make method for skill effect
sm.playSound("demonSlayer/31121006", 100)
sm.reservedEffect("Effect/Direction6.img/DemonTutorial/Scene3")
sm.sendDelay(900)

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/17", 2000)
sm.sendDelay(900)

sm.playSound("demonSlayer/31121006h", 100)
sm.startQuestNoCheck(23207)
sm.showNpcSpecialActionByTemplateId(GUARD1, "die", 0)
sm.showNpcSpecialActionByTemplateId(GUARD2, "die", 0)
sm.sendDelay(990)

sm.removeNpc(GUARD1)
sm.removeNpc(GUARD2)
sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg0/13", 2000)

sm.setSpeakerID(J_AGENT)
sm.sendNext("(Who is that? I've never seen such a powerful skill...)")

sm.sendDelay(1500)

sm.setPlayerAsSpeaker()
sm.sendNext("(Ugh... I wasted too much power fighting them. Where am I? If nothing else, I know I need to get out of here.)")

sm.forcedInput(2)
sm.sendDelay(990)

sm.forcedInput(0)
sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/12", 2000)



sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 2000, J_AGENT)
sm.sendDelay(1200)

sm.moveNpcByTemplateId(J_AGENT, True, 150, 100)

sm.sendNext("(No... I'm...losing consciousness. If they find me now...!)")

sm.setSpeakerID(J_AGENT)
sm.sendSay("Wait, calm down. I'm not your enemy. Who are you? And how did you end up in a place like this?")

sm.setPlayerAsSpeaker()
sm.sendSay("(He doesn't feel evil...)\r\nStay back!")

sm.setSpeakerID(J_AGENT)
sm.sendSay("C'mon... Look at you. You need help, and you need it now. Do you realized what they were doing? That machine next to you is an Energy Conducting Device... The Black Wings were draining your power.")

sm.setPlayerAsSpeaker()
sm.sendSay("(An Energy Conducting Device? This machine? And who are the Black Wings? None of this makes any sense...)")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg0/13", 2000)
sm.sendDelay(1500)

sm.sendNext("Who are you? And...*cough* How do you know about these things?")

sm.setSpeakerID(J_AGENT)
sm.sendSay("I'm J, an agent in the Resistance. We're working against the Black Wings. I don't know who you are, but I wouldn't take advantage of you in your state. Let me help you.")

sm.setPlayerAsSpeaker()
sm.sendSay("No... I have...no energy...")

sm.forcedAction(379, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/fallMale", 0, 0, 0, 0, 0, False, 0)
sm.sendDelay(600)

sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/13", 2000, J_AGENT)
sm.sendDelay(1500)

sm.removeNpc(J_AGENT)
sm.warpInstanceIn(931050030, 0)