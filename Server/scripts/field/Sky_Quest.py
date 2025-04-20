if sm.getFieldID() == 240030102 and sm.hasQuest(3757):
    sm.setPlayerAsSpeaker()
    sm.sendNext("Was this the place that the Dragon Rider was last spotted?")
    sm.sendSay("You should find a clue if you enter that#b portal on the lower right side of the map#k. Go on ahead.")