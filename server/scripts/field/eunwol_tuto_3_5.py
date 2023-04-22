# 927030030
seals = [
[600, -510],
[-600, -510],
[150, -720],
[-150, -720],
[0, -280],
]

sm.lockInGameUI(True, False)
sm.hideUser(False)
sm.forcedInput(0)
sm.sendDelay(1000)

for seal in seals:
    sm.playSound("eunwolTuto/seal", 100)
    sm.avatarOriented("Effect/Direction15.img/effect/tuto/seal/front")
    sm.avatarOriented("Effect/Direction15.img/effect/tuto/seal/back")
    sm.sendDelay(300)

    sm.playSound("eunwolTuto/particle", 100)
    sm.moveParticleEff("eunwol_seal", 0, -345, seal[0], seal[1], 1500, 50, 2, 5)
    sm.sendDelay(1500)

    sm.playSound("eunwolTuto/seal_stone", 100)
    sm.showEffect("Effect/Direction15.img/effect/tuto/seal/stone", 0, seal[0], seal[1] + 200, 0, 0, True, 0)
    if seal[0] == 0:
        sm.sendDelay(600)
    else:
        sm.sendDelay(1000)

sm.setQRValue(38907, "3")
sm.warp(927030050, 0)