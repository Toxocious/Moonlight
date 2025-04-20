# Guon (2094000) | Spiegelmann's Guest House

from net.swordie.ms.constants import CustomConstants
from net.swordie.ms.enums import EventType

runsPerDay = 3

if sm.isPartyLeader():
    if sm.partyHasCoolDown(EventType.Pyramid_PQ, runsPerDay):
        sm.sendNext("One of your party member has a cooldown for this pq.")
        sm.dispose()

    sm.sendNext("Will you help me defend nett's pyramid?#b\r\n"
                "\r\n"
                "#L0#Enter Nett's Pyramid Party Quest.#l")
    if sm.checkParty() and sm.checkPartyLevelReq(CustomConstants.MIN_LEVEL_FOR_PQ):

        sm.warpInstanceIn(926010100, 0, True) # Pyramid PQ  First Map
        sm.addCoolDownInXDaysForParty(EventType.Pyramid_PQ, 1, 1)

else:
    sm.sendSayOkay("Please have your party leader talk to me.")
