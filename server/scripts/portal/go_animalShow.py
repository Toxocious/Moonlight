# 223030200
response = sm.sendAskYesNo("Would you like to battle scarlion and targa?")

currentMap = sm.getFieldID()
if response:
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter the portal.")
    elif sm.checkParty():
        sm.warpInstanceIn(223030210, True)
sm.dispose()
