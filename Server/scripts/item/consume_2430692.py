# Consumes Nebulite Box.
from net.swordie.ms.constants.ItemConstants import getRandomNebulite
from net.swordie.ms.enums import InvType

if sm.hasItem(2430692) and sm.getEmptyInventorySlots(InvType.INSTALL):
    sm.consumeItem(2430692)
    sm.giveItem(getRandomNebulite())
else:
    sm.dispose()

