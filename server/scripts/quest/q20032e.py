# Manual Labor

# Constants
LIMBERT = 1106002
ITEM1 = 4033194
ITEM2 = 4033195

sm.setSpeakerID(LIMBERT)
sm.sendNext("Are you done cleaning yet? I suppose it looks moderately presentable in here. What are you looking at? Go organize the shelves!")
    
sm.sendNext("What?! Who told you you could touch that?!")

sm.completeQuestNoRewards(20032)
sm.giveItem(2001500, 20)
sm.giveItem(2001503, 20)
sm.addLevel(2)
sm.warpInstanceIn(913070003, 0)
sm.dispose()      