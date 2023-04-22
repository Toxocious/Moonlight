# Hidden street | Abandoned Tower <Stage 2> / Abandoned Tower <Stage 3>

import random
from net.swordie.ms.enums import WeatherEffNoticeType

STAGE_2_CLEARED = "Stage2Cleared"
STAGE_3_ORDER = "Stage3Order"
stage3OrderArray = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
STAGE_4_CLEARED = "Stage4Cleared"

#Stage 2
if sm.getFieldID() == 922010400:
    if chr.getParty().getOrCreateFieldById(922010401).getMobs().size() == 0 and chr.getParty().getOrCreateFieldById(922010402).getMobs().size() == 0 and chr.getParty().getOrCreateFieldById(922010403).getMobs().size() == 0 and chr.getParty().getOrCreateFieldById(922010404).getMobs().size() == 0 and chr.getParty().getOrCreateFieldById(922010405).getMobs().size() == 0:
        #Do this so that the effect won't get spammed
        if not field.hasProperty(STAGE_2_CLEARED):
            sm.invokeForParty("showFieldEffect", "quest/party/clear", 10000)
            sm.showObjectFieldEffect("gate")
            field.setProperty(STAGE_2_CLEARED, True)
    else:
        sm.showWeatherNotice("Defeat all the Dark Eyes and Shadow Eyes hiding in the darkness!", WeatherEffNoticeType.LudibriumPQ, 5000)

#Stage 3
elif sm.getFieldID() == 922010600:
    #Create puzzle order
    field.setProperty(STAGE_3_ORDER, stage3OrderArray)
    i = 0
    while i < len(stage3OrderArray):
        x = random.randint(1, 3)
        stage3OrderArray[i] = x
        i+=1
    field.setProperty(STAGE_3_ORDER, stage3OrderArray)
    sm.showWeatherNotice("Figure out the hidden code for the boxes and get to the top.", WeatherEffNoticeType.LudibriumPQ, 5000)

#Stage 4
elif sm.getFieldID() == 922010700:
    while sm.hasMobsInField():
        sm.waitForMobDeath()
    sm.invokeForParty("showFieldEffect", "quest/party/clear", 10000)
    field.setProperty(STAGE_4_CLEARED, True)
    sm.showObjectFieldEffect("gate")

#Stage 5
elif sm.getFieldID() == 922010800:
    sm.showWeatherNotice("Get on the box that corresponds to the answer to the question!", WeatherEffNoticeType.LudibriumPQ, 5000)

#Stage 6:
elif sm.getFieldID() == 922010900:
    sm.spawnMob(9300012, 979, 184, False)
