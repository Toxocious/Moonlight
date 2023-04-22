# 2433026 - Storage Room 4 Slot Coupon
from net.swordie.ms.constants import GameConstants

inc = 4
trunk = chr.getAccount().getTrunk()
slots = trunk.getSlotCount()

if slots >= GameConstants.MAX_TRUNK_SIZE:
    sm.sendSayOkay("You already have the max amount of storage space!")
else:
    if slots + inc > GameConstants.MAX_TRUNK_SIZE:
        inc = GameConstants.MAX_TRUNK_SIZE - slots
    if sm.sendAskYesNo("Do you want to increase your storage space? It will open up #b" + str(inc) + "#k more slots"):
        trunk.addSlotCount(inc)
        sm.consumeItem()

