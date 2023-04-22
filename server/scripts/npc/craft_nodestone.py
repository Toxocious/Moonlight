from net.swordie.ms.constants import GameConstants
from net.swordie.ms.constants import ItemConstants

req_shards = GameConstants.NODE_STONE_CRAFT_REQ
current_shards = chr.getNodeShards()
ARCHELLE = 1540945

sm.setSpeakerID(ARCHELLE)

quantity = sm.sendAskNumber("Do you want to create some Nodestones?#b\r\n\r\nNode shards required to craft a Nodestone: " + str(req_shards)
                    + "\r\nYour current node shards: " + str(current_shards) + "\r\n\r\n#kHow many would you like to craft?", 1, 0, current_shards // req_shards)
req_shards *= quantity
if quantity > 0:
    if req_shards > current_shards:
        sm.sendSayOkay("It seems you do not have enough node shards. I need #b" + str(req_shards) + "#k to craft "
                        "a Nodestone, but you only have #b" + str(current_shards) + "#k.")
    elif not sm.canHold(ItemConstants.NODESTONE, quantity):
        sm.sendSayOkay("Please make some more space in your USE inventory.")
    else:
        chr.addNodeShards(-req_shards)
        sm.sendSayOkay("You used #b " + str(req_shards) + "#k to successfully craft #b" + str(quantity) + " #kNodestones.")
        sm.giveItem(ItemConstants.NODESTONE, quantity)
