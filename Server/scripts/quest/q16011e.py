#
# @author once a bakery never a bakery
# @npc Dame Appropiation - Legion Manager
# @quest [Legion] Whip The Whelps
#

coins = int(sm.getQRValue(18797, "PT")) + 10
sm.sendSayOkay("'Awesome! Your #bLegion#k is pretty tough! Here is #b#i4310229:# #t4310229# x10#k as your reward."
               "\r\nCheck in tomorrow for more training missions!\r\n\r\nI'll go ahead and update your #bWeekly "
               "Cumulative Legion Coin Ranking#k!\r\n#bThis Week's Cumulative Coins#k#e: " + str(coins) + "#n")
sm.completeQuestNoRewards(16011)
sm.setQRValue(18797, "PT", str(coins))
sm.setQRValue(18793, "q1", "1") # LegionQuest
sm.setQRValue(18793, "q1Date", sm.getCurrentDateAsString()) # LegionQuest
sm.gainItem(4310229, 10)