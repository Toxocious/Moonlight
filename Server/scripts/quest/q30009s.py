# [Root Abyss] Defeat the First Seal Guardian

ALICIA = 1064002 # npc Id
sm.setSpeakerID(ALICIA)
sm.sendNext("I don't know what you did, but there have been more people through here than the Free Market")

sm.setPlayerAsSpeaker()
sm.sendNext("The Maple Alliance is going to help me get you out of here.")

sm.sendNext("Unfortunately, that means I have to take out all these Seal Guardians. Do you have any ideas to help?")

sm.setSpeakerID(ALICIA)
sm.sendNext("I've been trapped in here since they arrived. I don't know anything, but I can feel their power.")

response = sm.sendAskYesNo("The door with the clock on it seems to be the least threatening. Maybe that should be your first stop.")

if response:
    sm.sendNext("I know you're strong, but I don't think you can do this alone. Make sure to find an #rally that will help you#k!")
    sm.startQuest(parentID)
sm.dispose()
