# Guon (2094000) | Spiegelmann's Guest House

from net.swordie.ms.constants import CustomConstants
from net.swordie.ms.constants import GameConstants
from net.swordie.ms.enums import EventType

pqItems = [
4001117, # Old Metal Key
4001120, # Rookie Pirate Mark
4001121, # Rising Pirate Mark
4001122, # Veteran Pirate Mark
]
runsPerDay = 3

if sm.isPartyLeader():
    sm.sendNext("Are you willing to help me in the fight against Davy John?#b\r\n"
                "\r\n"
                "#L0#Enter the Lord Pirate Party Quest#l")
    if sm.partyHasCoolDown(EventType.Pirate_PQ, runsPerDay):
        sm.sendNext("One of your party member has a cooldown for this pq.")
        sm.dispose()
    if sm.checkParty() and sm.checkPartyLevelReq(CustomConstants.MIN_LEVEL_FOR_PQ):

        # check for items
        for item in pqItems:
            if sm.hasItem(item):
                quantity = sm.getQuantityOfItem(item)
                sm.consumeItem(item, quantity)

        # for each party member, create a LORD_PIRATE_QUEST with qrValue = "0"
        for partyMember in sm.getParty().getMembers():
            sm.createQuestWithQRValue(partyMember.getChr(), GameConstants.LORD_PIRATE_QUEST, "0", False)

        sm.addCoolDownInXDaysForParty(EventType.Pirate_PQ, 1, 1)
        sm.warpInstanceIn(925100000, 0, True) # Lord Pirate PQ  First Map

else:
    sm.sendSayOkay("Please have your party leader talk to me.")
