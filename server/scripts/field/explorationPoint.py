# Victoria Island Explorer Medal  |  Used for that Explore Medal Quest
if sm.getFieldID() == 105000000 and sm.hasQuest(30004):
    sm.setPlayerAsSpeaker()
    sm.sendNext("Welp, this thing is ancient, but seems to be working. Guess I should head back.")
    sm.warp(910700200, 0) # Root Abyss Quest Line Map
    sm.completeQuest(30004)

elif sm.getFieldID() == 104000000:
    sm.showEffect("Map/Effect.img/maplemap/enter/104000000")

elif sm.getFieldID() == 220080000 and sm.hasQuest(1662):
    sm.chatScript("Enter papulatus.")