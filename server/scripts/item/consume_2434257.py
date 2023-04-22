# 2434257 - Storage Room 4 Slot Coupon

sm.setSpeakerID(9062004)
if (sm.sendAskYesNo("#rYou may add up to " + str((96-chr.getAccount().getTrunk().getSlotCount())) +
                    " slots to your inventory, would you like to continue?")):
    sm.addStorageSlots(4)
    sm.consumeItem(2434257)

