answer = sm.sendSay("Are you ready to face #bVictor#k?#b\r\n#L0#Enter Victor's Workshop#l")

if answer == 0:
    if sm.getParty() is None:
        sm.sendSay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSay("Please have your party leader enter if you wish to face Victor.")
    elif sm.checkParty():
        sm.warpInstanceIn(401051200, True)