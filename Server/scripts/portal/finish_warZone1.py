# Used in Magnus questline - Occupied Reclamation HQ
if sm.hasMobsInField():
    sm.chat("Please eliminate all mobs.")
else:
    sm.warp(401000000, 1) # To Heliseum Reclamation HQ
sm.dispose()
