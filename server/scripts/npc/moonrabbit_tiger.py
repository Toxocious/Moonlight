# Guon (2094000) | Spiegelmann's Guest House

riceCakeId = 4001101
amountRequired = 10
moonBunnyId = 9300061

if sm.isPartyLeader():
    selection = sm.sendNext("#b#L0# I wanna finish the pq.\r\n"
                            + "#L1# I wanna leave.")

    if selection == 0:
        if not sm.hasItem(4001101, 10):
            sm.sendNext("Bring me 10 rice cakes to finish the pq.")
            sm.dispose()
        if not sm.hasMobById(moonBunnyId):
            sm.sendNext("I needed you to keep him alive!")
            sm.dispose()
        if sm.checkParty():
            sm.consumeItem(riceCakeId, 10)
            sm.stopEvents()
            sm.warpInstanceOut(910010300, 0) # Moon Bunny PQ  Exit
    elif selection == 1:
        sm.warpInstanceOut(910002000, 0)
else:
    sm.sendSayOkay("Please have your party leader talk to me.")
