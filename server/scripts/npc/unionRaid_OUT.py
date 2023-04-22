# Npc   9010108     Dame Appropriation
# Field 921172200   Legion Raid: Legion Raid Exit

if sm.getUnionCoin() > 0:
	coins = int(sm.getQRValue(18797, "PT")) + sm.getUnionCoin()
	sm.sendSayOk("You already collected #b" + str(sm.getUnionCoin()) + " #k of #b#i4310229:##t4310229##k? That's impressive!\r\nI'll go ahead and update your #bWeekly Coin Rank#k!\r\n\r\n#bThis Week's Coin Total#k#e: " + str(coins) + "#n\r\n\r\nThen I'll send you back to where you came from. Bye!")
	sm.giveItem(4310229, sm.getUnionCoin())
	sm.addUnionCoin(coins)
else:
	sm.sendNext("Uh, it doesn't look like you've earned any Legion Coins. If you're having a tough time with raiding, just come back later. Your Legion members will continue the raid even after you've left.")
	sm.sendSayOkay("I'll send you back to where you were. See you later.")
sm.warp(int(sm.getQRValue(16014, "map")))