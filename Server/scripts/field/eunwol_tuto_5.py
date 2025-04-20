# 927030040
seals = [
    [600, -510],
    [-600, -510],
    [150, -720],
    [-150, -720],
    [0, -280],
]

sm.lockInGameUI(True, False)
sm.hideUser(False)
sm.sendDelay(1000)

sm.playSound("eunwolTuto/seal", 100)
sm.avatarOriented("Effect/Direction15.img/effect/tuto/seal/front")
sm.avatarOriented("Effect/Direction15.img/effect/tuto/seal/back")

sm.playSound("eunwolTuto/particle", 100)
for seal in seals:
    sm.moveParticleEff("eunwol_seal", 0, -345, seal[0], seal[1], 1500, 50, 2, 5)
sm.sendDelay(1500)

for seal in seals:
    sm.showEffect("Effect/Direction15.img/effect/tuto/seal/stone", 0, seal[0], seal[1] + 200, 0, 0, True, 0)
sm.sendDelay(600)

sm.hideUser(True)
sm.sendDelay(1000)

sm.showBalloonMsg("Effect/Direction15.img/effect/story/BalloonMsg0/3", 0)
sm.startQuest(38900)
sm.createQuestWithQRValue(38900, "1")
sm.sendDelay(2000)

sm.blind(1, 255, 0, 0)
sm.warp(940200000, 0)