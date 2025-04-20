from net.swordie.ms.constants import BossConstants
from net.swordie.ms.constants import GameConstants
from net.swordie.ms.enums import EventType

# Mode, Required Level, Map ID, Death Count, Event Type, Cooldown, Hour String

destinations = [
    ["Easy", 130, 240060000, 5, EventType.EHorntail, 21600000, 6],
    ["Normal", 130, 240060002, 5, EventType.Horntail, 43200000, 12],
    ["Chaos", 130, 240060001, 5, EventType.CHorntail, 43200000, 12],
]

runsPerDay = 1

if sm.getFieldID() == 240050400:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    dialog = "Do you want to head to '#bHorntaial's Cave#k' to fight \r\n#bHorntail#k?\r\n"

    for i in range(len(destinations)):
        dialog += "#L%d##bGo to the Horntail's Cave (%s Mode) #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][0], destinations[i][1])

    dialog += "#L99#Never mind."
    response = sm.sendSay(dialog)

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
        sm.dispose()

    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to face #bHorntail#k.")
        sm.dispose()

    elif sm.partyHasCoolDown(destinations[response][4], runsPerDay):
        timeUntilReset = sm.getTimeUntilEventReset(destinations[response][4])
        sm.sendNext("You or one of your party member has already attempted facing \r\n#bHorntail#k within the past " + str(destinations[response][6]) + " Hours.\r\n You have " + timeUntilReset + " left on your cooldown.")
        sm.dispose()

    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][1], sm.getParty()):
            sm.setPartyDeathCount(destinations[response][3])
            sm.warpInstanceIn(destinations[response][2], True)
            sm.setInstanceTime(BossConstants.HORNTAIL_TIME)
            sm.addCooldownTimeForParty(destinations[response][4], destinations[response][5])
            sm.createQuestWithQRValue(GameConstants.EASY_HORNTAIL_QUEST, "1")
            sm.setPartyQRValue(GameConstants.EASY_HORNTAIL_QUEST, "1")
        else:
            sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])
