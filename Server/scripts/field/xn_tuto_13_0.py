# Edelstein: Secret Square ; Xenon Intro Last Step
ROO_D_PEACEFUL = 2159380
CLAUDINE_ARM_SLING = 2159384
BELLE_FIGHT = 2159385
BRIGHTON_FIGHT = 2159386
CHECKY_FIGHT = 2159387
ELEX_FIGHT = 2159388

sm.lockInGameUI(True)
sm.removeEscapeButton()

sm.setSpeakerID(BELLE_FIGHT)
sm.sendNext("Claudine! We were worried sick.")
sm.setSpeakerID(CLAUDINE_ARM_SLING)
sm.sendSay("Thanks Belle.")
sm.setSpeakerID(ELEX_FIGHT)
sm.sendSay("But... what's with Commander Glowstick over here? Looks friendly enough, I guess...")
sm.setSpeakerID(BRIGHTON_FIGHT)
sm.sendSay("I bet it's one of Gelimer's agents. They were going after you at first, Claudine. We can't trust anything that came out of that lab now...")
sm.setSpeakerID(CLAUDINE_ARM_SLING)
sm.sendSay("These... robots saved my life. I heard the little one talking about memories being wiped. I think Gelimer was controlling them...")
sm.setSpeakerID(CHECKY_FIGHT)
sm.sendSay("You KNOW Gelimer had control. You just saw it! We can't risk the safety of Secret Plaza because you're feeling charitable. That could be what Gelimer wants.")

sm.showBalloonMsg("Effect/Direction12.img/effect/story/BalloonMsg0/1", 1200)
sm.sendDelay(1200)
sm.setSpeakerID(ROO_D_PEACEFUL)
sm.sendNext("We can remove any control devices...")
sm.showBalloonMsg("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, CLAUDINE_ARM_SLING)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, BELLE_FIGHT)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, BRIGHTON_FIGHT)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, CHECKY_FIGHT)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, ELEX_FIGHT)
sm.sendDelay(1200)

sm.setSpeakerID(ELEX_FIGHT)
sm.sendNext("Then remove it immediately. It's dangerous.")
sm.setSpeakerID(ROO_D_PEACEFUL)
sm.sendSay("It's not that simple. If I remove this, #h # may be weakened to the point of near-death. The risks are too great to move ahead without careful consideration.")
sm.setPlayerAsSpeaker()
sm.sendSay("I'm willing to take the risk. Roo-D, do it if you can.")
sm.setSpeakerID(ROO_D_PEACEFUL)
sm.sendSay("Really? Are you sure?")
sm.setPlayerAsSpeaker()
sm.sendSay("If Gelimer were to take control, I would be a danger to everyone around me. I can't let that happen. I will never be controlled again.")
sm.setSpeakerID(ROO_D_PEACEFUL)
sm.sendSay("Okay, I'll remove it...")
sm.sendSay("You might feel a little dizzy at first... but I'm almost done.")

sm.jobAdvance(3600)
sm.giveItem(1142575)
sm.giveAndEquip(1353001)
sm.removeSkill(30021238)
sm.giveSkill(36000004, 1)
sm.addSP(-1)
sm.warp(310010000, 1)

sm.lockInGameUI(False)
