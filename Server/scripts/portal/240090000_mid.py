# 240090000

from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

KUPOS_RIDE = 1932165
KUPO = 2210002
TIME = 20

if sm.hasQuestCompleted(31339) or sm.hasQuest(31339):
    sm.warpInstanceIn(240090800, 0) # Kupo's Ride  The Road Up
    sm.rideVehicle(KUPOS_RIDE)
    sm.lockInGameUI(True, False)
    sm.createStopWatch(TIME)
    sm.setInstanceTime(TIME, 240091000, 2)
    sm.invokeAfterDelay(TIME*999, "lockInGameUI", False)
    sm.invokeAfterDelay(TIME*1000, "removeCTS", CharacterTemporaryStat.RideVehicle)
else:
    sm.setSpeakerID(KUPO)
    sm.sendSayOkay("Hey Hey Hey.. Where do you think you are going, buddy?\r\n"
                   "This path isn't meant for you. Sorry")