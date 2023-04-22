ARKARIUM = 2159309
ARK_CHAT = 2159308

sm.completeQuestNoRewards(23203)
sm.deleteQuest(23203)

sm.spawnNpc(ARKARIUM, 500, 50)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "summon", 0)
sm.lockInGameUI(True)
sm.curNodeEventEnd(True)
sm.forcedInput(2)
sm.sendDelay(30)

sm.forcedInput(0)
sm.removeEscapeButton()
sm.setSpeakerID(ARK_CHAT)
sm.sendNext("You are rather powerful, aren't you? I think it's time we settled which of us is stronger. I've always wanted to test my magic against your Demon Fury. Of course, I know who will be victorious!")

sm.chatScript("Press the Control key rapidly to block Arkarium's attack and counterattack.")
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/guide1/0", 5010, 150, -300)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "alert", 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/arkyrimAttack", 0, 0, -7, 0, sm.getNpcObjectIdByTemplateId(ARKARIUM), False, 0)
sm.sendDelay(2010)

sm.playSound("demonSlayer/arkAttack0", 100)
sm.patternInputRequest("17#17#17#", 4, 2, 3000)

sm.fadeInOut(600, 1500, 600, 150)
sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/9", 2000)
sm.forcedAction(376, 0)
sm.showEffect("Skill/3112.img/skill/31121000/effect", 0, 389, 83, 0, 0, True, 0)
sm.playSound("demonSlayer/31121000", 100)
sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/9", 2000)
sm.sendDelay(900)

sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1000, ARKARIUM)
sm.playSound("demonSlayer/31121000", 100)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "teleportation", 0)
sm.sendDelay(570)

sm.removeNpc(ARKARIUM)
sm.spawnNpc(ARKARIUM, 620, 50)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "summon", 0)
sm.sendDelay(1000)

sm.removeEscapeButton()
sm.setSpeakerID(ARKARIUM)
sm.sendNext("You're stronger than I expected! How amusing!")

sm.showNpcSpecialActionByTemplateId(ARKARIUM, "resolve", 0)
sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg1/10", 2000, ARKARIUM)
sm.sendDelay(1500)

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/11", 2000)
sm.sendDelay(1500)

sm.showEffect("Skill/3112.img/skill/31121005/effect", 0, 389, 71, 1, 0, False, 1)
sm.showEffect("Skill/3112.img/skill/31121005/effect0", 0, 389, 71, -1, 0, False, 1)
sm.playSound("demonSlayer/31121005", 100)
sm.forcedAction(370, 0)
sm.sendDelay(1980)

sm.showEffect("Effect/Direction6.img/effect/tuto/gateOpen/0", 2100, 918, -195, 0, 0, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/gateLight/0", 2100, 926, -390, 0, 0, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/gateStair/0", 2100, 879, 30, 1, 0, True, 0)
sm.playSound("demonSlayer/openGate", 100)
sm.sendDelay(1950)

sm.startQuestNoCheck(23203)
sm.showBalloonMsgOnNpc("Effect/Direction6.img/effect/tuto/balloonMsg0/0", 2000, ARKARIUM)
sm.sendDelay(1200)

sm.setSpeakerID(ARK_CHAT)
sm.sendNext("Ah! It seems the Black Mage wishes to see you after all. It's a shame we cannot finish our little contest, but as always, I defer to my master. I believe I'll pay those so-called 'Heroes' a visit...")
sm.sendSay("As for you, #h0# I don't expect I'll see you again. Enjoy the oblivion granted to you from the Black Mage himself! Ha ha ha!")

sm.showNpcSpecialActionByTemplateId(ARKARIUM, "teleportation", 0)
sm.sendDelay(570)

sm.removeNpc(ARKARIUM)
sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg2/2", 2000)
sm.forcedInput(2)