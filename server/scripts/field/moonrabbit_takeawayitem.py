import time
from time import sleep

xPos = -190
yPos = -190
moonBunnyId = 9300061
riceCakeId = 4001101
timeForDrop = 10


sleep(3)
currentTime = int(time.time()*1000.0)
sm.setFieldProperty("lastHit", currentTime)
sm.spawnMob(moonBunnyId, -190, -190, False)
while sm.hasMobById(moonBunnyId):
    currentTime = int(time.time()*1000.0)
    lastHitTime = sm.getFieldProperty("lastHit")
    timeSinceHit = (currentTime - lastHitTime) / 1000
    if(timeSinceHit > timeForDrop):
        sm.dropItem(riceCakeId, xPos, yPos)
        sm.setFieldProperty("lastHit", currentTime)
    sleep(1)
sm.killMobs()
