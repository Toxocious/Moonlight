CUTTER = 1096005

sm.removeEscapeButton()
sm.setSpeakerID(CUTTER)
sm.sendNext("All right! Let's go!")

sm.startQuest(2572)
sm.removeNpc(1096012)
sm.playSound("cannonshooter/fire", 100)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/flying/0", 7000, 0, 0, -2, -2, False, 0)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/flying1/0", 7000, 0, 0, -2, -2, False, 0)
sm.sendDelay(800)

sm.warp(912060300, 0)