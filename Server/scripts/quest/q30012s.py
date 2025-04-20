# [Root Abyss] Defeat the Final Seal Guardian

ALICIA = 1064002 # npc Id
sm.setSpeakerID(ALICIA)
sm.sendNext("We're almost there! Only the final seal guardian remains.")

response = sm.sendAskYesNo("Can you handle the seal guardian behind the dragon door?")

if response:
    sm.sendNext("Be prepared. The energy behind the dragon door feels enormous.")
    sm.startQuest(parentID)
sm.dispose()
