from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

KUPOS_RIDE = 1932165
KUPO = 2210002
TIME = 20

sm.warpInstanceIn(240090801, 0) # Kupo's Ride  The Road Down
sm.rideVehicle(KUPOS_RIDE)
sm.lockInGameUI(True, False)
sm.createStopWatch(TIME)
sm.invokeAfterDelay(TIME*999, "lockInGameUI", False)
sm.invokeAfterDelay(TIME*1000, "removeCTS", CharacterTemporaryStat.RideVehicle)
sm.setInstanceTime(TIME, 240090000, 4)
