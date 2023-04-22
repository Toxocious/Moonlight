
# Normal Lotus Reward / Leave

BLACK_HEAVEN_CORE_ENTRANCE = 350060300
BLACK_HEAVEN_CORE_LAST_HARD = 350060600

absoLabCoinID = 4310156


if sm.hasMobsInField(chr.getFieldID()) or (sm.getFieldID() != 350060600 and sm.getFieldID() != 350060900):
	response = sm.sendAskYesNo("Do you wish to leave the battlefield and abandon your allies?")
	if response:
		sm.warpInstanceOut(BLACK_HEAVEN_CORE_ENTRANCE)
		sm.dispose()

if not sm.canHold(absoLabCoinID):
	sm.sendSayOkay("Please make room in your inventory to receive your #bAbsoLab Coins.")
	sm.dispose()

if not sm.hasMobsInField(chr.getFieldID()):
	sm.doLog()
	if chr.getParty().getMembers().size() == 1:
		sm.giveItem(absoLabCoinID, 25)
		sm.warpInstanceOut(BLACK_HEAVEN_CORE_ENTRANCE)
	if chr.getParty().getMembers().size() == 2:
		sm.giveItem(absoLabCoinID, 22)
		sm.warpInstanceOut(BLACK_HEAVEN_CORE_ENTRANCE)
	if chr.getParty().getMembers().size() == 3:
		sm.giveItem(absoLabCoinID, 20)
		sm.warpInstanceOut(BLACK_HEAVEN_CORE_ENTRANCE)
	if chr.getParty().getMembers().size() == 4:
		sm.giveItem(absoLabCoinID, 20)
		sm.warpInstanceOut(BLACK_HEAVEN_CORE_ENTRANCE)
	if chr.getParty().getMembers().size() == 5:
		sm.giveItem(absoLabCoinID, 19)
		sm.warpInstanceOut(BLACK_HEAVEN_CORE_ENTRANCE)
	if chr.getParty().getMembers().size() == 6:
		sm.giveItem(absoLabCoinID, 18)
		sm.warpInstanceOut(BLACK_HEAVEN_CORE_ENTRANCE)

