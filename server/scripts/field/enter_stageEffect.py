from net.swordie.ms.constants import WzConstants

sm.showFieldEffect(""+ WzConstants.EFFECT_MONSTER_PARK_STAGE)
sm.showFieldEffect(""+ WzConstants.EFFECT_MONSTER_PARK_STAGE_NUMBER +"1")

while sm.hasMobsInField():
    sm.waitForMobDeath()

sm.showFieldEffect(""+ WzConstants.EFFECT_CLEAR)