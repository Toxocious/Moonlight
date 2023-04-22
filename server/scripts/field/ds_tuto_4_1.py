J_AGENT = 2159344
CLAUDINE = 2159315
ELEX = 2159312
BELLE = 2159314

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 2000)
sm.sendDelay(1000)

sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendNext("(I hear something...)")

sm.setSpeakerID(J_AGENT)
sm.sendSay("...I was going to come back after I discovered the Energy Conducting Device. It was like the one at the Power Plant, but this one was connected to an egg. While I was examining it, that person broke out of the egg, and defeated all the Black Wings. It was crazy.")

sm.setSpeakerID(CLAUDINE)
sm.sendSay("You know, J... If it were anyone else telling me this, I would laugh in their face. But this... What were the Black Wings doing? And who is this person?")

sm.setPlayerAsSpeaker()
sm.sendSay("(Are they talking about me?)")

sm.setSpeakerID(J_AGENT)
sm.sendSay("And those skills... I've never seen skills like that. So powerful... I think our guest is out of juice, but we should be careful.")

sm.setSpeakerID(ELEX)
sm.sendSay("Maybe this is one of their experiments? Think about Vita... And nobody really knows what the Black Wings are doing deep in the mines, right?")

sm.setSpeakerID(BELLE)
sm.sendSay("That blasted madman Gelimer... We have to stop him!")

sm.setSpeakerID(J_AGENT)
sm.sendSay("...Hold on. I'll see if our new friend is awake yet.")

sm.forcedInput(2)
sm.sendDelay(2000)

sm.forcedInput(1)
sm.spawnNpc(J_AGENT, -600, -20)
sm.showNpcSpecialActionByTemplateId(J_AGENT, "summon", 0)
sm.sendDelay(30)

sm.forcedInput(0)
sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, J_AGENT)
sm.sendDelay(1000)

sm.sendNext("Ah, you're awake. How do you feel? Still tired?")

sm.setPlayerAsSpeaker()
sm.sendSay("Did...you save me?")

sm.setSpeakerID(J_AGENT)
sm.sendSay("Yeah. You were badly wounded... I couldn't just leave you with the Black Wings. Considering the circumstances, I think we're on the same side. We have plenty to talk about, so how about you take a walk with me?")

sm.setPlayerAsSpeaker()
sm.sendSay("(Interrogation...? Not sure yet... They're friendlier than those Black Wings, anyway.) Very well.")

sm.removeNpc(J_AGENT)
sm.warpInstanceIn(931050010, 0)