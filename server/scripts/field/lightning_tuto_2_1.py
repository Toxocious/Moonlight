# Hidden Street : Temple of Time Corridor (927020010) | Luminous Intro
VOID_PRIEST = 9300529
VOID_PRIEST_2 = 9300530

coordinates = [
    [1200, 69],
    [1100, 69],
    [1000, 69],
    [900, 69],
    [800, 69],
    [700, 69],
    [600, 69],
    [500, 69],
    [400, 69],
    [300, 69],
    [200, 69],
    [100, 69],
    [1150, 69],
    [950, 69],
    [850, 69],
]

coordinates2 = [
    [750, 69],
    [650, 69],
    [550, 69],
    [450, 69],
    [350, 69],
    [250, 69],
    [150, 69],
]

sm.sendDelay(700)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/3", 0, 0, -120, -2, -2, False, 0)
sm.sendDelay(2000)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/4", 0, 0, -120, -2, -2, False, 0)
sm.sendDelay(2000)

sm.giveSkill(20041226)

for coord in coordinates:
    sm.spawnMob(VOID_PRIEST, coord[0], coord[1], False)
for coord in coordinates2:
    sm.spawnMob(VOID_PRIEST_2, coord[0], coord[1], False)

sm.lockInGameUI(False)
sm.showFieldEffect("lightning/screenMsg/0")