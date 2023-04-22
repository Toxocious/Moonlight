from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

sm.warpInstanceOut(450001105, 0)
# Ride Vehicle Check
if sm.getnOptionByCTS(CharacterTemporaryStat.RideVehicle) == 1932393: # Paper Boat
    sm.removeCTS(CharacterTemporaryStat.RideVehicle)