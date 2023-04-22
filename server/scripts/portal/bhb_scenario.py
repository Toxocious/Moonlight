# 350060000 Portal at Gelimer's Lab | Used to open an NPC dialogue that will warp the player inside the  Lotus  Boss

FirstBossMap = 350060160

sm.setSpeakerID(1540496) # Lotus Hologram
response = sm.sendAskYesNo("Are you sure you want to battle me?")

if response:
    if sm.checkParty():
        sm.warpInstanceIn(FirstBossMap, True)
