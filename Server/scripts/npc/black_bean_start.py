from time import sleep

# Mode, Required Level, Map ID, Death Count

destinations = [
    ["Easy", 10, 555001400, 5],
]

if sm.getFieldID() == 120040000:
    def is_party_eligible(reqlevel, party):
        for member in party.getMembers():
            if member.getLevel() < reqlevel:
                return False

        return True

    sm.sendAskYesNo

    dialog = "Do you want to head to '#bShady Beach#k' to fight \r\n#bBlack Bean#k?\r\n"

    for i in range(len(destinations)):
        dialog += "#L%d##bGo to Shady Beach (%s Mode) #r(Lv. %d+)#b#l\r\n" % (i, destinations[i][0], destinations[i][1])

    dialog += "#L99#Never mind."
    response = sm.sendSay(dialog)

    if not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader talk to me if you wish to face #bBlack Bean#k.")

    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")

    elif sm.checkParty() and response != 99:
        if is_party_eligible(destinations[response][1], sm.getParty()):
            sm.setDeathCount(destinations[response][3])
            sm.warpInstanceIn(destinations[response][2], True)
            sm.setInstanceTime(20*60)
            sleep(1)
            sm.spawnMob(9420620, -500, 116, False, 200000000)