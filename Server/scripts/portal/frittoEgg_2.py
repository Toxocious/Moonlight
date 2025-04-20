if parentID % 2 == 0:
    portal = 16
else:
    portal = 15
if sm.getRandomIntBelow(2) == 0:
    sm.teleportToPortal(portal)
else:
    sm.giveNX(20000)
    sm.warpInstanceOut(993000601, 0) # Hidden Street : Secluded Forest