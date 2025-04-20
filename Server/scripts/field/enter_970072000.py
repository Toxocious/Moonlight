sm.spawnMob(8881000, 836, 86, False, 60000000000000)

while sm.hasMobsInField():
    sm.waitForMobDeath()
sm.setInstanceTime(60, 970072200)
for char in field.getChars():
    sm.giveItem(4310199, 8)
    sm.giveItem(4009349, 1)