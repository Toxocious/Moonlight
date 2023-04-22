# Created by MechAviv
# Map ID :: 940001010
# Hidden Street : East Pantheon

sm.sendDelay(2000)


sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg1/1", 0, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.moveCamera(False, 600, -1200, 29)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(1553)

sm.spawnMob(9300545, -1600, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300546, -1500, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300545, -1400, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300546, -1300, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300545, -1200, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300546, -1100, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300545, -1000, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300546, -900, 29, False)
sm.sendDelay(150)

sm.spawnMob(9300546, -800, 29, False)
sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(0)


# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 32 00 00 00 FF 00 00 00 00
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.showFieldEffect("lightning/screenMsg/0", 0)
while sm.hasMobsInField():
    sm.waitForMobDeath()
sm.warp(940001050, 0)