if parentID % 2 == 0:
    portal = 20
else:
    portal = 19
if sm.getRandomIntBelow(2) == 0:
    sm.teleportToPortal(portal)
else:
    sm.giveNX(40000)
    sm.warpInstanceOut(993000601, 0) # Hidden Street : Secluded Forest