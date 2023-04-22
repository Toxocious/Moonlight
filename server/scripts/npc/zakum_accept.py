from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import EventType

# Mode, Required Level, Map ID, Death Count, Event Type, Cooldown

destinations = [
    ["Easy", 50, 280030200, 5, EventType.EasyZakum, 21600000],
    ["Normal", 75, 280030100, 5, EventType.NormalZakum, 21600000],
    ["Chaos", 120, 280030000, 5, EventType.ChaosZakum, 43200000],
]

runsPerDay = 1

if sm.getFieldID() == 211042400:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    sm.setSpeakerID(2030008)

    dialog = "Do you want to head to '#bZakum's Altar#k' to fight \r\n#bZakum#k?\r\n"

    for i in range(len(destinations)):
        dialog += "#L%d##bGo to Zakum's Altar (%s Mode) #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][0], destinations[i][1])

    dialog += "#L99#Never mind."
    response = sm.sendSay(dialog)

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
        sm.dispose()

    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to face #bZakum#k.")
        sm.dispose()

    elif sm.partyHasCoolDown(destinations[response][4], runsPerDay):
        timeUntilReset = sm.getTimeUntilEventReset(destinations[response][4])
        sm.sendNext("You or one of your party member has already attempted facing #bZakum#k recently.\r\n\r\n You have #e#r" + timeUntilReset + "#n#k left on your cooldown.")
        sm.dispose()

    elif not sm.hasItem(4001017):
        sm.sendSayOkay("You do not possess a #b#v 4001017 # #z 4001017 ##k.")
        sm.dispose()


    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][1], sm.getParty()):
          #  sm.addCooldownTimeForParty(destinations[response][4], destinations[response][5])
            sm.warpInstanceIn(destinations[response][2], True)
            sm.setPartyDeathCount(destinations[response][3])
            sm.setInstanceTime(BossConstants.ZAKUM_TIME)
        else:
            sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])