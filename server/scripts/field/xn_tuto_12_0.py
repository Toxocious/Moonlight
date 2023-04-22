# Classified Lab : Silo ; Xenon Intro Rescued by Resistance

BERYL_FORCEMOVE = 2159378
BERYL = 2159379
ROO_D_AGGRESIVE = 2159381
CLAUDINE_ARM_SLING = 2159384
BELLE_FIGHT = 2159385
BRIGHTON_FIGHT = 2159386
CHECKY_FIGHT = 2159387
ELEX_FIGHT = 2159388

# TODO: make this nicer once figure out how to stop forceMoves, it might be spawn order
sm.removeEscapeButton()

sm.spawnNpc(ROO_D_AGGRESIVE, -1044, 43)
sm.forcedInput(0)
sm.spawnNpc(CLAUDINE_ARM_SLING, -980, 43)  # Magnetizes to ROO_D
sm.spawnNpc(BERYL, -767, 43)
sm.spawnNpc(ELEX_FIGHT, -670, 43)  # -614
sm.spawnNpc(BRIGHTON_FIGHT, -580, 43)  # Magnetizes to Elex
sm.spawnNpc(BELLE_FIGHT, -476, 43)
sm.spawnNpc(CHECKY_FIGHT, -370, 43)


# ROO_D_AGGRESIVE is moving to the left upon spawning
# CLAUDINE_ARM_SLING is offset to the right and facing left
# BERYL is offset to the right
# ELEX_FIGHT is offset to the right
# BRIGHTON_FIGHT is offset to the right and facing right
# BELLE_FIGHT is offset to the right
# CHECKY_FIGHT is offset to the right and facing right

sm.moveNpcByTemplateId(ROO_D_AGGRESIVE, False, 15, 2000000000)
sm.flipNpcByTemplateId(ROO_D_AGGRESIVE, False)
sm.flipNpcByTemplateId(CLAUDINE_ARM_SLING, False)
sm.flipNpcByTemplateId(BRIGHTON_FIGHT, True)
sm.flipNpcByTemplateId(CHECKY_FIGHT, True)

sm.lockInGameUI(True)

sm.forcedInput(0)
sm.sendDelay(300)
sm.forcedInput(2)
sm.sendDelay(300)
sm.forcedInput(0)

sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/17", 2400, 0, -200, BELLE_FIGHT)
sm.sendDelay(2400)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/18", 2400, 0, -200, BELLE_FIGHT)
sm.sendDelay(2400)

sm.setSpeakerID(CLAUDINE_ARM_SLING)
sm.sendNext("The cavalry is here!")
sm.setSpeakerID(CHECKY_FIGHT)
sm.sendSay("Save the hugs for later, Claudine. We've gotta get you out of here first!")


sm.showNpcEffectOnPosition("Effect/Direction12.img/effect/tuto/smogStart", 0, 0, CHECKY_FIGHT)
sm.sendDelay(1050)
sm.showEffect("Effect/Direction12.img/effect/tuto/smog", 3900, 450, 0)
sm.sendDelay(1500)

sm.removeNpc(ROO_D_AGGRESIVE)
sm.removeNpc(CLAUDINE_ARM_SLING)
sm.removeNpc(ELEX_FIGHT)
sm.removeNpc(BRIGHTON_FIGHT)
sm.removeNpc(BELLE_FIGHT)
sm.removeNpc(CHECKY_FIGHT)
sm.hideUser(True)
sm.sendDelay(2220)

sm.showEffect("Effect/Direction12.img/effect/tuto/smogEnd", 0, 450, 0)
sm.sendDelay(420)

# something?
sm.sendDelay(600)
# something?
sm.sendDelay(600)

sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg0/1", 1200, BERYL)
sm.sendDelay(1200)
sm.removeNpc(BERYL)

sm.lockInGameUI(False)
sm.warpInstanceIn(931060070, 0)
