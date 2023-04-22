# Inside the Sangri-la  | Explorer tutorial
if not sm.hasMobsInField():
    sm.spawnMob(9300815, -152, 150, False) # Spawn Mano
else:
    sm.killMobs()
    sm.spawnMob(9300815, -152, 150, False) # Spawn Mano
sm.dispose()
