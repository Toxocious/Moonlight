if parentID % 2 == 0:
    portal = 14
else:
    portal = 13
if sm.getRandomIntBelow(2) == 0:
    sm.teleportToPortal(portal)
else:
    sm.giveNX(10000)
    sm.warpInstanceOut(993000601, 0) # Hidden Street : Secluded Forest