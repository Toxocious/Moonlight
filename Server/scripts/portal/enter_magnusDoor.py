from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import EventType

# Mode, Required Level, Map ID, Death Count, Event Type, Cooldown

destinations = [
    ["Normal", 160, 401060200, 20, EventType.NMagnus, 64800000],
    ["Hard", 220, 401060100, 20, EventType.HMagnus, 64800000],
]

runsPerDay = 1

if sm.getFieldID() == 401060000:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    sm.setSpeakerID(3001032)

    dialog = "Do you want to head to '#bTyrant's Throne Room#k' to fight \r\n#bMagnus#k?\r\n"

    for i in range(len(destinations)):
        dialog += "#L%d##bGo to Tyrant's Throne Room (%s Mode) #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][0], destinations[i][1])

    dialog += "#L99#Never mind."
    response = sm.sendSay(dialog)

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
        sm.dispose()

    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to face #bMagnus#k.")
        sm.dispose()

    elif sm.partyHasCoolDown(destinations[response][4], runsPerDay):
        timeUntilReset = sm.getTimeUntilEventReset(destinations[response][4])
        sm.sendNext("You or one of your party member has already attempted facing \r\n#bMagnus#k within the past 18 Hours.\r\n You have " + timeUntilReset + " left on your cooldown.")
        sm.dispose()

    elif not sm.hasItem(4033406):
        sm.sendSayOkay("You do not possess a #b#v 4033406 # #z 4033406 ##k.")
        sm.dispose()


    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][1], sm.getParty()):
            sm.setPartyDeathCount(destinations[response][3])
            sm.warpInstanceIn(destinations[response][2], True)
            sm.setInstanceTime(BossConstants.MAGNUS_TIME)
            sm.addCooldownTimeForParty(destinations[response][4], destinations[response][5])
            sm.consumeItem(4033406)
        else:
            sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])