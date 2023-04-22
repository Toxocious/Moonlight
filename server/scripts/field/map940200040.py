# There's something weird going on here, think it's just v176 vs v206 differences, but quest with no script is teleporting us here directly but it's skipping a step compared to in v206
# you're supposed to turn in Lost Moonbeam at the Fox Tree to her little pet, and then that will teleport you here. We're getting teleported here when we accept from Silver
MOB = 9300810

sm.spawnMob(MOB, -230, 25, False)
sm.spawnMob(MOB, 80, 25, False)
sm.spawnMob(MOB, 490, 25, False)
sm.spawnMob(MOB, 800, 25, False)
sm.spawnMob(MOB, -200, -215, False)
sm.spawnMob(MOB, 200, -215, False)
sm.spawnMob(MOB, 600, -215, False)
sm.spawnMob(MOB, -200, -455, False)
sm.spawnMob(MOB, 200, -455, False)
sm.spawnMob(MOB, 600, -455, False)

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("There really are tigers around here? Moonbeam... I gotta get rid of all the #r#o9300810##k monsters first.")
sm.startQuestNoCheck(38021)
