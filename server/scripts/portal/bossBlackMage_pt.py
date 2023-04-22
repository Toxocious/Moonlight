# Damien entry NPC

# mode, req level, map, death count
destinations = [
    ["Normal", 245, 450013100, 10],
]

def is_party_eligible(reqlevel, party):
    # TODO: check prequest
    for member in party.getMembers():
        if member.getLevel() < reqlevel:
            return False

    return True

sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()

dialog = "Do you want to head to the #rTemple of Darkness#k to fight the Black Mage??\r\n"

for i in range(len(destinations)):
    dialog += "#L%d#Go to the Temple of Darkness (%s Mode). (Lv. %d+)#l\r\n" % (i, destinations[i][0], destinations[i][1])

dialog += "#L99#Never mind."
response = sm.sendSay(dialog)

if sm.getParty() is None:
    sm.sendSayOkay("Please create a party before going in.")

elif not sm.isPartyLeader():
    sm.sendSayOkay("Please have your party leader talk to me if you wish to face the Black Mage.")

elif sm.checkParty() and response != 99:
    if is_party_eligible(destinations[response][1], sm.getParty()):
        sm.warpInstanceIn(destinations[response][2], True)
        sm.setDeathCount(destinations[response][3])

    else:
        sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level %d." % destinations[response][1])