from net.swordie.ms.connection.packet import DemianFieldPacket
from net.swordie.ms.connection.packet import FieldPacket
from net.swordie.ms.constants import BossConstants
from net.swordie.ms.life.mob.boss.demian import Demian
from net.swordie.ms.life.mob.boss.demian.sword import DemianFlyingSword

field = chr.getField()
instance = chr.getInstance()
init = instance.initialised
if not init:
    instance.initialised = True
    mob = sm.spawnMob(BossConstants.DEMIAN_NORMAL_TEMPLATE_ID, 895, 16, False, BossConstants.DEMIAN_HP)   # spawn Demian
    sm.addEvent(Demian.stigmaIncinerateObjectTimer(field))  # start Pillar

    sword = DemianFlyingSword.createDemianFlyingSword(chr, mob)
    field.spawnLife(sword, None)  # create
    sword.startPath()
    sword.target()

    for iChr in instance.getChars():
        iSM = iChr.getScriptManager()
        iChr.write(FieldPacket.giveSpecialSkillBar(BossConstants.BRAND_OF_SACRIFICE))
        iChr.write(DemianFieldPacket.corruptionChange(False, 0))  # show corruption window
        iSM.addEvent(Demian.increaseStigmaPassiveTimer(iChr))  # start stigma timer on corruption window


while sm.hasMobsInField():
    sm.waitForMobDeath()
sm.warp(350160240)
