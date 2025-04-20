# Frozen Fairy Forest : Blooming Forest (910150003)  |  Script used to spawn in 10 slimes in the map

SLIME = 9300422

coordinates = [
[-2058, 1],
[-2348, 1],
[-2812, 1],
[-2810, 1],
[-3040, 1],

[-2589, -330],
[-2313, -330],
[-2450, -330],

[-2121, -612],
[-1948, -612],
]

sm.giveSkill(20021166, 1) # Give attacking skill
if sm.hasQuest(24001): # Danika's Request (Mercedes Intro Quest)
    for coordinate in coordinates:
        sm.spawnMob(SLIME, coordinate[0], coordinate[1], False, 5) # Spawn 10 slimes across the field
sm.dispose()
