#
# @author baking some swords now
# @npc Dame Appropiation - Legion Manager
# @quest [Legion] Defeat the Golden Wyvern
#

LEGION_QUEST = 18793

coins = int(sm.getQRValue(18797, "PT")) + 20
sm.sendSayOkay("'Awesome! Your #bLegion#k is pretty tough! Here is #b#i4310229:# #t4310229# x20#k as your reward.\r\nCheck in tomorrow for more training missions!\r\n\r\nI'll go ahead and update your #bWeekly Cumulative Legion Coin Ranking#k!\r\n#bThis Week's Cumulative Coins#k#e: " + coins + "#n");
sm.completeQuest(16012)
sm.setQRValue(18797, "PT", str(coins))

# Is this q0 and q2?
sm.setQRValue(LEGION_QUEST, "q0", "1")
sm.setQRValue(LEGION_QUEST, "q0Date", sm.getCurrentDateAsString())
sm.setQRValue(LEGION_QUEST, "q2", "1")
sm.setQRValue(LEGION_QUEST, "q2Date", sm.getCurrentDateAsString())
sm.gainItem(4310229, 20)