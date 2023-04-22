# [Silent Crusade] Nihal Desert Dispatch

STARLING = 9120221

sm.setSpeakerID(STARLING)
sm.sendNext("I've heard reports of a Mystic Gate near Nihal Desert. Meet #bLora#k at the #rRoyal Cactus Desert#k. He'll fill you in.")

response = sm.sendAskYesNo("Please go now.")

if response:
    sm.sendNext("Good luck, young Crusader!")
    sm.startQuest(parentID)