from net.swordie.ms.enums import EventType
from net.swordie.ms.constants import BossConstants

# Mode, Required Level, Map ID, Death Count, Event Type, Cooldown

destinations = [
    [250, 610030200, 25, EventType.CWKPQ, 604800000],
]

runsPerDay = 1

if sm.getFieldID() == 610030020:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    dialog = "Do you want participate in the '#bCrimson Wood Keep Party Quest#k'?\r\n"

    for i in range(len(destinations)):
        dialog += "#L%d##bGo to the Crimson Wood Keep #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][1])

    dialog += "#L99#Never mind."
    response = sm.sendSay(dialog)

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
        sm.dispose()

    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to participate in the #bCrimson Wood Keep Party Quest#k.")
        sm.dispose()

    elif sm.partyHasCoolDown(destinations[response][3], runsPerDay):
        timeUntilReset = sm.getTimeUntilEventReset(destinations[response][3])
        sm.sendNext("You or one of your party member has already attempted the \r\n#bCrimson Wood Keep Party Quest#k within the past 7 Days.\r\n You have " + timeUntilReset + " left on your cooldown.")
        sm.dispose()

    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][0], sm.getParty()):
            sm.setPartyDeathCount(destinations[response][2])
            sm.warpInstanceIn(destinations[response][1], True)
            sm.setInstanceTime(BossConstants.CWKPQ_TIME_ROOM_1)
            sm.addCooldownTimeForParty(destinations[response][3], destinations[response][4])
        else:
            sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])