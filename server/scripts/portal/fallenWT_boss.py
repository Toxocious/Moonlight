from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import EventType

# Mode, Required Level, Map ID, Death Count, Event Type, Cooldown

destinations = [
	["Legendary", 255, 350160100, 20, EventType.Damien, 604800000],
]

runsPerDay = 1

def is_party_eligible(reqlevel, party):
	for member in party.getMembers():
		if member.getLevel() < reqlevel:
			return False

	return True

sm.setSpeakerID(1530621)

dialog = "Do you want to head to the '#bDark World Tree Summit#k' to fight \r\n#bDamien#k?\r\n"

for i in range(len(destinations)):
	dialog += "#L%d##bGo to the Dark World Tree Summit (%s Mode) #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][0], destinations[i][1])

dialog += "#L99#Never mind."
response = sm.sendSay(dialog)

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
        sm.dispose()

    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to face #bDamien#k.")
        sm.dispose()

    elif sm.partyHasCoolDown(destinations[response][4], runsPerDay):
        timeUntilReset = sm.getTimeUntilEventReset(destinations[response][4])
        sm.sendNext("You or one of your party member has already attempted facing \r\n#bDamien#k within the past 7 Days.\r\n You have " + timeUntilReset + " left on your cooldown.")
        sm.dispose()

elif sm.checkParty() and response != 99:
	if is_party_eligible(destinations[response][1], sm.getParty()):
		sm.setPartyDeathCount(destinations[response][3])
		sm.warpInstanceIn(destinations[response][2], True)
		sm.setInstanceTime(BossConstants.DAMIEN_TIME)
        sm.addCooldownTimeForParty(destinations[response][4], destinations[response][5])
	else:
		sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level #b%d#k." % destinations[response][1])
