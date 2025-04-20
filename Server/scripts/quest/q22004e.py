# 22004 |   Fixing the Fence (Evan intro)
sm.setSpeakerID(1013103)
sm.sendNext("Ah, did you bring all the Thick Branches? That's my kid! What shall i give you as reward?... Let's see... Oh, right! \r\n #fUI/UIWindow2.img/QuestIcon/4/0# \r\n #v3010097# Strong Wooden Chair \r\n #fUI/UIWindow2.img/QuestIcon/8/0# 210 exp")
if sm.canHold(3010097):
    sm.giveItem(3010097)
    sm.giveExp(210)
    sm.completeQuest(parentID)
    sm.dispose()
else:
    sm.sendNext("Please make room in your Set-up Inventory")
    sm.dispose()