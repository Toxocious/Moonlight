from net.swordie.ms.enums import InvType

sm.setSpeakerID(9062004)
if sm.getSlotsLeftToAddByInvType(2) <= 0:
    sm.sendSayOkay("You have already maxed your slots.")
    sm.dispose()
if (sm.sendAskYesNo("#rYou may add up to " + str(sm.getSlotsLeftToAddByInvType(2)) +
                    " slots to your inventory, would you like to continue?")):
    sm.addInventorySlotsByInvType(sm.getSlotsLeftToAddByInvType(2), 2)
    sm.consumeItem()
    sm.dispose()