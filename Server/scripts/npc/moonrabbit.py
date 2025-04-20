# Guon (2094000) | Spiegelmann's Guest House

from net.swordie.ms.constants import CustomConstants
from net.swordie.ms.enums import EventType

pqItems = [
    4001101,
]
runsPerDay = 300

if sm.isPartyLeader():
    sm.sendNext("Are you willing to help me to defend moon bunny?#b\r\n"
                "\r\n"
                "#L0#Enter the Moon Bunny Party Quest#l")
    if sm.partyHasCoolDown(EventType.MoonBunny, runsPerDay):
        sm.sendNext("One of your party member has a cooldown for this pq.")
        sm.dispose()
    if sm.checkParty() and sm.checkPartyLevelReq(CustomConstants.MIN_LEVEL_FOR_PQ):

        # check for items
        for item in pqItems:
            if sm.hasItem(item):
                quantity = sm.getQuantityOfItem(item)
                sm.consumeItem(item, quantity)


        sm.addCoolDownInXDaysForParty(EventType.MoonBunny, 1, 1)
        sm.warpInstanceIn(910010000, 0, True) # Moon Bunny PQ  First Map

else:
    sm.sendSayOkay("Please have your party leader talk to me.")
