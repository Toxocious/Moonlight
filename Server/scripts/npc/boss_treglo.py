answer = sm.sendSay("Are you ready to face #bTreglow#k?#b\r\n#L0#Enter Treglow's Laboratory#l")

if answer == 0:
    if sm.getParty() is None:
        sm.sendSay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSay("Please have your party leader enter if you wish to face Treglow.")
    elif sm.checkParty():
        sm.warpInstanceIn(401052200, True)