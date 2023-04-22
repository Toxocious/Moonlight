if parentID % 2 == 0:
    portal = 18
else:
    portal = 17
if sm.getRandomIntBelow(2) == 0:
    sm.teleportToPortal(portal)
else:
    sm.giveNX(30000)
    sm.warpInstanceOut(993000601, 0) # Hidden Street : Secluded Forest