from net.swordie.ms.enums import EventType

# Mode, Required Level, Map ID, Death Count, Event Type

destinations = [
    ["Normal", 235, 861000100, 5, EventType.AlienPQ],
]

runsADay = 5

if sm.getFieldID() == 861000000:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    sm.sendAskYesNo

    dialog = "Do you want to participate in the Alien Party Quest?\r\n"

    for i in range(len(destinations)):
        dialog += "#b#L0#Enter Alien Party Quest  -  " + str(sm.getEventAmountDone(EventType.getByVal(50))) + "/" + str(runsADay) + " Attempted today\r\n"

    response = sm.sendSay(dialog)

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
        sm.dispose()

    if not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to participate in the Alien Party Quest.")
        sm.dispose()

    if sm.partyHasCoolDown(destinations[response][4], runsADay):
        sm.sendNext("You or one of your party member has already attempted the Alien Party Quest within the past 24 Hours.")
        sm.dispose()

    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][1], sm.getParty()):
            sm.setPartyDeathCount(destinations[response][3])
            sm.warpInstanceIn(destinations[response][2], True, -384, -41)
            sm.setInstanceTime(5*60)
            sm.addCoolDownInXDaysForParty(destinations[response][4], 1, 1)
        else:
            sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])
else:
    if sm.sendAskYesNo("Are you sure you want to leave the battlefield?"):
        sm.warpInstanceOut(861000000)