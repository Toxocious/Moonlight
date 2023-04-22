from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import EventType

# Mode, Required Level, Map ID, Death Count, Event Type, Cooldown

destinations = [
    ["Normal", 100, 105200400, 5, EventType.Vellum, 64800000],
    ["Chaos", 220, 105200800, 5, EventType.CVellum, 64800000],
]

runsPerDay = 1

if sm.getFieldID() == 105200000:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    sm.setSpeakerID(1064002)

    dialog = "Do you want to head to the '#bWest Garden#k' to fight \r\n#bVellum#k?\r\n"

    for i in range(len(destinations)):
        dialog += "#L%d##bGo to the West Garden (%s Mode) #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][0], destinations[i][1])

    dialog += "#L99#Never mind."
    response = sm.sendSay(dialog)

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
        sm.dispose()

    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to face #bVellum#k.")
        sm.dispose()

    elif sm.partyHasCoolDown(destinations[response][4], runsPerDay):
        timeUntilReset = sm.getTimeUntilEventReset(destinations[response][4])
        sm.sendNext("You or one of your party member has already attempted facing #bVellum#k within the past 18 Hours.\r\n\r\nYou have " + timeUntilReset + " left on your cooldown.")
        sm.dispose()

    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][1], sm.getParty()):
            sm.setDeathCount(destinations[response][3])
            sm.warpInstanceIn(destinations[response][2], True)
            sm.setInstanceTime(BossConstants.ROOTABYSS_TIME)
            sm.addCoolDownInXDaysForParty(destinations[response][4], 1, 1)
        else:
            sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])
