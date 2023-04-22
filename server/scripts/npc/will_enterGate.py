# Will entry NPC (will_enterGate)

# mode, req level, map, death count
destinations = [
    # actual map is -50, with some direction stuff
    ["Normal", 210, 450008150, 10],
    ["Hard", 235, 450008450, 10],
]
TIME = 30 * 60 # 30 minutes

def is_party_eligible(reqlevel, party):
    # TODO: check prequest
    for member in party.getMembers():
        if member.getLevel() < reqlevel:
            return False

    return True

sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()

dialog = "Are you ready to head to the #bDiffraction Hall#k to fight Will?\r\n"

for i in range(len(destinations)):
    dialog += "#L%d#Go to the #bDiffraction Hall (%s Mode).#k (Lv. %d or above)#l\r\n" % (i, destinations[i][0], destinations[i][1])

dialog += "#L99#Never mind."
response = sm.sendSay(dialog)

if sm.getParty() is None:
    sm.sendSayOkay("Please create a party before going in.")

elif not sm.isPartyLeader():
    sm.sendSayOkay("Please have your party leader talk to me if you wish to face Damien.")

elif sm.checkParty() and response != 99:
    if is_party_eligible(destinations[response][1], sm.getParty()):
        sm.warpInstanceIn(destinations[response][2], True)
        sm.setDeathCount(destinations[response][3])
        sm.setInstanceTime(TIME)

    else:
        sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level %d." % destinations[response][1])