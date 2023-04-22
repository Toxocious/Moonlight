
from net.swordie.ms.constants import CustomConstants



if sm.isPartyLeader():
    sm.sendNext("Do you wish to participate in the Halloween Party Quest?#b\r\n"
                "\r\n"
                "#L0#Enter Halloween Party Quest.#l")
    if sm.checkParty() and sm.checkPartyLevelReq(CustomConstants.MIN_LEVEL_FOR_PQ):

        sm.warpInstanceIn(922900400, 0, True) # HGPQ 1st Map

else:
    sm.sendSayOkay("Please have your party leader talk to me.")
