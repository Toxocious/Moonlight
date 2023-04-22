# Guon (2094000) | Spiegelmann's Guest House

from net.swordie.ms.constants import GameConstants

if sm.isPartyLeader():
    sm.sendNext("Will you help me cook my meal?#b\r\n"
                "\r\n"
                "#L0#Enter Chef Tangyoon's Party Quest#l")
    if sm.checkParty():

        # for each party member, create a LORD_PIRATE_QUEST with qrValue = "0"
        for partyMember in sm.getParty().getMembers():
            sm.createQuestWithQRValue(partyMember.getChr(), GameConstants.LORD_PIRATE_QUEST, "0", False)

        sm.warpInstanceIn(912080100, 0, True)

else:
    sm.sendSayOkay("Please have your party leader talk to me.")
