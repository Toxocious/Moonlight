# 3rd job advancement portal for explorers
from net.swordie.ms.constants import JobConstants

mapID = 0
job = chr.getJob()
if JobConstants.isAdventurerWarrior(job):
    mapID = 910540100
elif JobConstants.isAdventurerMage(job):
    mapID = 910540200
elif JobConstants.isAdventurerArcher(job):
    mapID = 910540300
elif JobConstants.isAdventurerThief(job):
    mapID = 910540400
elif JobConstants.isAdventurerPirate(job):
    mapID = 910540500
if mapID != 0:
    sm.warp(mapID, 0)
sm.dispose()
