# Classified Lab : Corridor ; Xenon Intro Escort Stage

ROO_D_AGGRESIVE = 2159381
CLAUDINE_ARM_SLING = 2159384

sm.lockInGameUI(True)
sm.removeEscapeButton()

sm.forcedInput(2)
sm.sendDelay(30)

sm.forcedInput(0)
sm.spawnNpc(CLAUDINE_ARM_SLING, -1601, 32)
sm.flipNpcByTemplateId(CLAUDINE_ARM_SLING, False)
sm.spawnNpc(ROO_D_AGGRESIVE, -1714, 32)
sm.moveNpcByTemplateId(ROO_D_AGGRESIVE, False, 15, 100)

sm.setSpeakerID(ROO_D_AGGRESIVE)
sm.sendSay("This corridor leads to the Silo, and outside. We're going to run into a lot of Guard Robots on the way.")
sm.setPlayerAsSpeaker()
sm.sendSay("I will handle them. Don't worry.")
sm.setSpeakerID(CLAUDINE_ARM_SLING)
sm.sendSay("I'm afraid I'm not going to be much use in a fight with this injured arm... Are you sure about this?")
sm.setPlayerAsSpeaker()
sm.sendSay("Let's give it a try.")

# TODO: spawn Mobs, six robots through map

sm.moveNpcByTemplateId(CLAUDINE_ARM_SLING, False, 2100, 100)
sm.moveNpcByTemplateId(ROO_D_AGGRESIVE, False, 2100, 100)

sm.lockInGameUI(False)

sm.invokeAfterDelay(60000, "removeNpc", CLAUDINE_ARM_SLING)
sm.invokeAfterDelay(60000, "removeNpc", ROO_D_AGGRESIVE)
