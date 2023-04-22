# Alicia's Soul | Tower of Oz (992000000)

from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import EventType

#######################################
    # THIS IS TEMPORARY UNTIL OZ IS CODED #
    #######################################

# Mode, Required Level, Map ID, Death Count, Event Type

destinations = [
    ["Normal", 120, 992050000, 20, EventType.Dorothy],
]

runsPerDay = 1

if sm.getFieldID() == 992000000:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    sm.sendAskYesNo

    dialog = "Do you want to head to the '#bUndersea 50F#k' to fight \r\n#bDorothy#k?\r\n"

    for i in range(len(destinations)):
        dialog += "#L%d##bGo to the Undersea 50F (%s Mode) #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][0], destinations[i][1])

    dialog += "#L99#Never mind."
    response = sm.sendSay(dialog)

    if not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to face #bDorothy#k.")

    if sm.partyHasCoolDown(destinations[response][4], runsPerDay):
        sm.sendNext("You or one of your party member has already attempted facing \r\n#bDorothy#k within the past 24 Hours.")
        sm.dispose()

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")

    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][1], sm.getParty()):
            sm.setPartyDeathCount(destinations[response][3])
            sm.warpInstanceIn(destinations[response][2], True)
            sm.setInstanceTime(BossConstants.DOROTHY_TIME)
            sm.addCoolDownInXDaysForParty(destinations[response][4], 1, 1)
        else:
            sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])
