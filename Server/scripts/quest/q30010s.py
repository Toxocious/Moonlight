# [Root Abyss] Defeat the Second Seal Guardian

ALICIA = 1064002 # npc Id
sm.setSpeakerID(ALICIA)
sm.sendNext("Oh I feel the dark energy getting weaker!")

response = sm.sendAskYesNo("Can you handle the seal guardian behind the teapot door?")

if response:
    sm.sendNext("You may want to prepare a bit. The energy behind that door feels strong.")
    sm.startQuest(parentID)
sm.dispose()
