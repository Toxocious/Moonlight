# [Root Abyss] Defeat the Third Seal Guardian

ALICIA = 1064002 # npc Id
sm.setSpeakerID(ALICIA)
sm.sendNext("I believe it's working. I can really feel the darkness weaken.")

response = sm.sendAskYesNo("Please take care of the seal guardian behind the crown door?")

if response:
    sm.sendNext("The door with the crown is radiating evil! Better not let your guard down")
    sm.startQuest(parentID)
sm.dispose()
