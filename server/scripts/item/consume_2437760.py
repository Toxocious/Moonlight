# id 2437760 (Arcane Symbol x5 Selector Coupon), field 450001000
sm.setSpeakerID(9010000) # Maple Administrator
res = sm.sendNext("Please select the #bArcane Symbol#k you'd like!\r\n\r\n#L5# #b#i1712001:# #t1712001:# x5#k#l")
if res == 0:
    if sm.sendAskYesNo("Do you want to claim the #b#i1712001:# #t1712001:# 1#k?"):
        if sm.canHold(1712001, 5):
            sm.consumeItem(parentID)
            sm.giveItem(1712001, 5)
            sm.sendNext("\r\nI gave you the #b#t1712001:# 5#k!\r\n\r\n\r\n#fUI/UIWindow2.img/QuestIcon/4/0##b#e\r\n#i1712001:# #t1712001:# 5#n#k")
        else:
            sm.sendNext("Please make some space in your EQUIP tab.")