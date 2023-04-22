# Hidden street - Secret Library (cygnus third job adv)
if sm.hasQuest(20882):
    for i in range(15):
        sm.spawnMob(9300470, -1, -29, False) # Black wing Henchman
    sm.waitForMobDeath(9300470)
    sm.spawnNpc(1104303, 100, -29)
    sm.dispose()