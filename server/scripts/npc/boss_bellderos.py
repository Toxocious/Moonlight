answer = sm.sendSay("Where would you like to go to?#b \r\n#L0#Velderoth's Sitting Room#l\r\n#L1#Continue to Magnus#l\r\n#L2#Forge a Dragon King Relic\r\n#L3#Nevermind#l")

# sm.chat("Response was " + str(response) + "\r\rAnswer was " + str(answer))
if answer == 0:
    if sm.getParty() is None:
        sm.sendSay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSay("Please have your party leader enter if you wish to face Velderoth.")
    elif sm.checkParty():
        sm.warpInstanceIn(401053100, True)
elif answer == 1:
    sm.warp(401060000)
elif answer == 2:
    if not sm.hasItem(4033403):
        sm.sendSayOkay("You do not possess a #v4033403# #b#z4033403##k.")
        sm.dispose()
    elif not sm.hasItem(4033404):
        sm.sendSayOkay("You do not possess a #v4033404# #b#z4033404##k.")
        sm.dispose()
    elif not sm.hasItem(4033405):
        sm.sendSayOkay("You do not possess a #v4033405# #b#z4033405##k.")
        sm.dispose()
    elif not sm.canHold(4033406):
        sm.sendSayOkay("Please make room in your inventory first.")
        sm.dispose()
    else:
        sm.consumeItem(4033403)
        sm.consumeItem(4033404)
        sm.consumeItem(4033405)
        sm.giveItem(4033406)
        sm.chat("You have gained a Dragon King Relic")
        sm.dispose()
