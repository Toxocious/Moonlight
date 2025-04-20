# Russian Roulette Exit Portal

DIZZY_NPC = 9000155 # some roulette-looking npc

sm.setSpeakerID(DIZZY_NPC)
if sm.sendAskYesNo("Are you sure you want to leave? You won't be able to return!"):
    sm.warpNoReturn(sm.getPreviousFieldID())