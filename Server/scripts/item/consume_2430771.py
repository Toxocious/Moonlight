from net.swordie.ms.enums import InvType

sm.setSpeakerID(9062004)
if sm.getExpandableSlots(InvType.ETC) <= 0:
    sm.sendSayOkay("You have already maxed your slots.")
    sm.dispose()
if (sm.sendAskYesNo("#rYou may add up to " + str(sm.getExpandableSlots(InvType.ETC)) +
                    " slots to your inventory, would you like to continue?")):
    sm.addInventorySlotsByInvType(8, 4)
    sm.consumeItem()