# [Stone Colossus] Wanderin' the Colossus

CHINO = 2210004
TIME = 20

sm.setSpeakerID(CHINO)
sm.sendNext("Okay, are you set to go? We're going to take the elevator up the side of the Stone Colossus, and it's going to be a long trip. "
            "Make sure you're ready.")

response = sm.sendAskYesNo("Are you ready?")

if response:
    sm.warpInstanceIn(240091600)
    sm.startQuest(parentID)
    sm.setInstanceTime(TIME, 240092000, 0)
else:
    sm.sendSayOkay("What are you waiting for?")