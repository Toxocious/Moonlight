# 141060000
from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

if sm.hasQuest(32170):
    sm.warpInstanceIn(141010400, 0, False) # Nora's Cove

    # Ride Vehicle Check
    if sm.getnOptionByCTS(CharacterTemporaryStat.RideVehicle) == 1930000: # Riena Skiff used in Riena Strait
        sm.removeCTS(CharacterTemporaryStat.RideVehicle)