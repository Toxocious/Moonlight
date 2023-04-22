# 141060000
from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

sm.warp(141010000, 0) # Middle of Strait : Ice Station 1

# Ride Vehicle Check
if sm.getnOptionByCTS(CharacterTemporaryStat.RideVehicle) == 1930000: # Riena Skiff used in Riena Strait
    sm.removeCTS(CharacterTemporaryStat.RideVehicle)

