from net.swordie.ms.constants import CustomConstants
from net.swordie.ms.enums import EventType

# Jenn (9020005) | Spiegelmann's Guest House

pqItems = [

]

runsPerDay = 3

if sm.isPartyLeader():
    sm.sendNext("Can you help me escape?#b\r\n"
                "\r\n"
                "#L0#Enter the Escape Party Quest#l")
else:
    sm.sendSayOkay("Please have your party leader talk to me.")
    sm.dispose()

if sm.partyHasCoolDown(EventType.Escape_PQ, runsPerDay):
    sm.sendNext("One of your party member has a cooldown for this pq.")
    sm.dispose()

if sm.checkParty() and sm.checkPartyLevelReq(CustomConstants.MIN_LEVEL_FOR_PQ):
    # check for items
    for item in pqItems:
        if sm.hasItem(item):
            quantity = sm.getQuantityOfItem(item)
            sm.consumeItem(item, quantity)
    sm.warpInstanceIn(921160100, 0, True) # Escape! - PQ  first map
    sm.addCoolDownInXDaysForParty(EventType.Escape_PQ, 1, 1)
