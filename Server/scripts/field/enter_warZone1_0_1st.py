# Occupied reclamation HQ  |  Used for Magnus prequest
from net.swordie.ms.constants import WzConstants

sm.chatScript("Defeat all Specters inside Heliseum Reclamation HQ.")
sm.spawnMob(2400122, -599, 182, False) #Specter miner
sm.spawnMob(2400122, -449, 93, False) #Specter miner
sm.spawnMob(2400122, 96, 182, False) #Specter miner
sm.spawnMob(2400122, 650, 182, False) #Specter miner
sm.spawnMob(2400122, 1232, 182, False) #Specter miner
sm.spawnMob(2400121, -201, 182, False) #Specter tamer
sm.spawnMob(2400121, 70, 93, False) #Specter tamer
sm.spawnMob(2400121, -655, 182, False) #Specter tamer
sm.spawnMob(2400121, -1228, 182, False) #Specter tamer
sm.spawnMob(2400121, 244, 93, False) #Specter tamer
sm.spawnMob(2400121, 522, 182, False) #Specter tamer
sm.spawnMob(2400121, 1162, 182, False) #Specter tamer
sm.spawnMob(2400121, -1309, 182, False) #Specter tamer
sm.spawnMob(2400120, -1309, 182, False) #Specter battle hound
sm.spawnMob(2400120, -290, 182, False) #Specter battle hound
sm.spawnMob(2400120, -239, 93, False) #Specter battle hound
sm.spawnMob(2400120, -866, 182, False) #Specter battle hound
sm.spawnMob(2400120, 91, 182, False) #Specter battle hound
sm.spawnMob(2400120, 1237, 182, False) #Specter battle hound
sm.spawnMob(2400120, -1581, 182, False) #Specter battle hound
sm.spawnMob(2400120, -1107, 182, False) #Specter battle hound
while sm.hasMobsInField():
    sm.waitForMobDeath()
sm.showEffectToField(WzConstants.EFFECT_DOJO_CLEAR)
