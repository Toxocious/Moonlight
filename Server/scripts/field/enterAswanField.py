from net.swordie.ms.enums import  AzwanDifficulty
from net.swordie.ms.enums import  AzwanModes

mode = AzwanModes.getByVal((chr.getFieldID() / 1000) % 10)
difficulty = AzwanDifficulty.getByVal(chr.getFieldID() % 10)

#chr.chatMessage(str(mode))
#chr.chatMessage(str(difficulty))

if mode == AzwanModes.Attack:
    sm.waitForMobDeath()