sm.setSpeakerID(9062004)
if chr.getAccount().getTrunk().getSlotCount() >= 96:
    sm.sendSayOkay("You have already maxed your slots.")
    sm.dispose()
if (sm.sendAskYesNo("#rYou may add up to " + str((96-chr.getAccount().getTrunk().getSlotCount())) +
                    " slots to your inventory, would you like to continue?")):
    sm.addStorageSlots(8)
    sm.consumeItem(2432346)