# [Stone Colossus] Kupo's Ride

from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

KUPO = 2210002
KUPOS_RIDE = 1932165
TIME = 20

sm.setSpeakerID(KUPO)
response = sm.sendAskYesNo("Okay. We're all fueled up! Ready to head out now?")

if response:
    sm.startQuest(parentID)
    sm.warpInstanceIn(240090800, 0) # Kupo's Ride  The Road Up
    sm.rideVehicle(KUPOS_RIDE)
    sm.lockInGameUI(True, False)
    sm.setInstanceTime(TIME, 240091000, 2)
    sm.invokeAfterDelay(TIME * 1000, "removeCTS", CharacterTemporaryStat.RideVehicle)
    sm.invokeAfterDelay(TIME * 1000, "lockInGameUI", False)
    sm.sendNext("Yeehaa! Let's go!")
else:
    sm.sendSayOkay("What do you mean, no?")