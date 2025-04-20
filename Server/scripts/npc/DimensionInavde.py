# [Piston]  |  [9020009]
# Hidden Street : Stormfront

from net.swordie.ms.enums import EventType

mapId = 940021000
runsADay = 5

if sm.getFieldID() == 940020000:
    selection = sm.sendSayOkay("Some creeps from Maple World have invaded Grandis. We need to find a way to drive them back.\r\n"
                               "#L0##bEnter Dimensional Invasion " + str(sm.getEventAmountDone(EventType.DIPQ)) + "/" + str(runsADay) + " Attempted today #l\r\n"
                               "#L1##bTrade in Dimension Glove fragments\r\n")

    if selection == 0:
        if chr.getLevel() < 120:
            sm.sendSayOkay("You must be level #b120#k to enter the Dimensional Invasion Party Quest.")
            sm.dispose()

        if sm.getEventAmountDone(EventType.DIPQ) >= runsADay:
            sm.sendSayOkay("You are currently on cooldown for the Dimensional Invasion Party Quest")
            sm.dispose()

        if not sm.getParty() is not None:
            sm.sendSayOkay("Please create a party before entering.")
            sm.dispose()

        if not sm.canHold(2431127):
            sm.sendSayOkay("Please make sure you have room in your inventory to receive your rewards at the end of the Party Quest.")

        else:
            sm.addCoolDownInXays(EventType.DIPQ, 1, 1)
            sm.warpInstanceIn(mapId, True)
            sm.setInstanceTime(60*60)

    if selection == 1:
        selection2 = sm.sendSayOkay("Please select the glove you would like.\r\n"
                                    "#L0##bDimension Glove\r\n"
                                    "#L1##bHigh Quality Dimension Glove\r\n")

        if selection == 0:
            sm.sendAskYesNo("Would you like to trade your #bDimension Glove fragments#k for a #bDimension Glove#k.")

            if not sm.hasItem(4033605):
                sm.sendSayOkay("You do not possess a #v4033605##zv4033605#")
                sm.dispose()
            if not sm.hasItem(4033604):
                sm.sendSayOkay("You do not possess a #v4033604##zv4033604#")
                sm.dispose()
            if not sm.hasItem(4033603):
                sm.sendSayOkay("You do not possess a #v4033603##zv4033603#")
                sm.dispose()
            if not sm.hasItem(4033602):
                sm.sendSayOkay("You do not possess a #v4033602##zv4033602#")
                sm.dispose()
            if not sm.canHold(1082488):
                sm.sendSayOkay("Please make room in your EQUIP inventory.")
                sm.dispose()

            else:
                sm.consumeItem(4033605)
                sm.consumeItem(4033604)
                sm.consumeItem(4033603)
                sm.consumeItem(4033602)
                sm.giveItem(1082488)


        if selection == 1:
            sm.sendAskYesNo("Would you like to trade your #bHigh Quality Dimension#k Glove fragments for a #bHigh Quality Dimension Glove#k.")

            if not sm.hasItem(4033606):
                sm.sendSayOkay("You do not possess a #v4033606##zv4033606#")
                sm.dispose()
            if not sm.hasItem(4033607):
                sm.sendSayOkay("You do not possess a #v4033607##zv4033607#")
                sm.dispose()
            if not sm.hasItem(4033608):
                sm.sendSayOkay("You do not possess a #v4033608##zv4033608#")
                sm.dispose()
            if not sm.hasItem(4033609):
                sm.sendSayOkay("You do not possess a #v4033609##zv4033609#")
                sm.dispose()
            if not sm.canHold(1082488):
                sm.sendSayOkay("Please make room in your EQUIP inventory.")
                sm.dispose()

            else:
                sm.consumeItem(4033606)
                sm.consumeItem(4033607)
                sm.consumeItem(4033608)
                sm.consumeItem(4033609)
                sm.giveItem(1082489)
