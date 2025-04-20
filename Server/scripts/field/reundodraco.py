# Used to remove the Red Draco Morph Buff In Time Lane : Temple of Time
from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

if sm.getnOptionByCTS(CharacterTemporaryStat.Morph) == 16:
    sm.removeCTS(CharacterTemporaryStat.Morph)
sm.dispose()
