ARKARIUM = 2159309

sm.completeQuestNoRewards(23204)
sm.deleteQuest(23204)
sm.lockInGameUI(True)
sm.forcedInput(2)
sm.sendDelay(10)

sm.forcedInput(0)

sm.removeEscapeButton()
sm.setSpeakerID(ARKARIUM)
sm.sendNext("Oh, look, it's #h0#? How was your trip? I hope it was worth disobeying your orders. And how was your family? Are they looking well? Heh heh heh...")

sm.setPlayerAsSpeaker()
sm.sendSay("...I don't have time for you, #r#p2159309##k. Move, or I will MAKE you move.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Tsk, tsk... Leaving without approval, disobeying orders... And that rebellious look... No, I don't think I'll allow you to see the Black Mage.")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/14", 2000)
sm.forcedAction(332, 0)
sm.playSound("demonSlayer/31111003", 100)
sm.showEffect("Skill/3111.img/skill/31111003/effect", 0, 0, 0)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "teleportation", 0)
sm.sendDelay(570)

sm.removeNpc(ARKARIUM)
sm.sendDelay(1200)

sm.spawnNpc(ARKARIUM, 180, 50)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "summon", 0)
sm.sendDelay(360)

sm.setSpeakerID(ARKARIUM)
sm.sendNext("Really? This is treason, you know! Are you really so weak that losing your family drives you to this? Pathetic!")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/15", 2000)
sm.forcedInput(1)
sm.dispose()




