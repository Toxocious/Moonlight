from net.swordie.ms.scripts import ScriptType
from net.swordie.ms.constants import AchievementConstant
from net.swordie.ms.client import Achievements

EhorntailIDs = [8810202, 8810203, 8810204, 8810205, 8810206, 8810207, 8810208, 8810209]
NhorntailIDs = [8810002, 8810003, 8810004, 8810005, 8810006, 8810007, 8810008, 8810009]
ChorntailIDs = [8810102, 8810103, 8810104, 8810105, 8810106, 8810107, 8810108, 8810109]
EhorntailMap = 240060300
NhorntailMap = 240060200
ChorntailMap = 240060201

if sm.getFieldID() == EhorntailMap:
    mobs = EhorntailIDs
    dropMob = 8810214

if sm.getFieldID() == NhorntailMap:
    mobs = NhorntailIDs
    dropMob = 8810018

if sm.getFieldID() == ChorntailMap:
    mobs = ChorntailIDs

for id in mobs:
    sm.spawnMob(id, 95, 260, False)
    dropMob = 8810118

count = 0

while count < 8:
    sm.waitForMobDeath(mobs)
    count = count + 1

sm.killMobs(True)
sm.spawnMob(dropMob)
sm.killMobs(True)
if not chr.getAccount().isExistAchievement(AchievementConstant.MOB_HORNTAIL) and sm.getFieldID() == NhorntailMap:
    Achievements.getInstance().getById(AchievementConstant.MOB_HORNTAIL).finishAchievement(chr);
elif not chr.getAccount().isExistAchievement(AchievementConstant.MOB_CHAOS_HORNTAIL) and sm.getFieldID() == ChorntailMap:
    Achievements.getInstance().getById(AchievementConstant.MOB_CHAOS_HORNTAIL).finishAchievement(chr);