# Dimensional pass  |  (2430115)

from net.swordie.ms.enums import WeatherEffNoticeType

DIMENSIONAL_PASS_COUNT = "DimensionalPassCount"

#increment pass count
if field.hasProperty(DIMENSIONAL_PASS_COUNT):
    field.setProperty(DIMENSIONAL_PASS_COUNT, field.getProperty(DIMENSIONAL_PASS_COUNT) + 1)
else:
    field.setProperty(DIMENSIONAL_PASS_COUNT, 1);

#Display how many passes the party has collected
sm.invokeForParty("chatScript","Your party has collected " + str(field.getProperty(DIMENSIONAL_PASS_COUNT)) + " passes in total." )

#Check if 20 passes is reached
if field.hasProperty(DIMENSIONAL_PASS_COUNT):
    if field.getProperty(DIMENSIONAL_PASS_COUNT) == 20:
        #show message that stage is finished
        sm.invokeForParty("showWeatherNotice", "All of the passes have been gathered. Proceed to the next stage by going through the gate.", WeatherEffNoticeType.LudibriumPQ, 10000)
        sm.invokeForParty("showFieldEffect", "quest/party/clear")
        sm.showObjectFieldEffect("gate")