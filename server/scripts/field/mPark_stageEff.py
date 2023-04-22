# Monster Park Maps

from net.swordie.ms.constants import GameConstants
from net.swordie.ms.constants import WzConstants
from net.swordie.ms.enums import WeatherEffNoticeType

stage = ((sm.getFieldID() % 1000) / 100) + 1
if stage == 6:
    sm.showEffect(WzConstants.EFFECT_MONSTER_PARK_FINAL_STAGE)
else:
    sm.showEffect("" + WzConstants.EFFECT_MONSTER_PARK_STAGE_NUMBER + "" + str(stage))
    sm.showEffect(WzConstants.EFFECT_MONSTER_PARK_STAGE)
sm.chatScript("All monsters in the field must be eliminated before you can move to the next stage")

while sm.hasMobsInField():
    mob = sm.waitForMobDeath()
    #Exp based on mobTemplateId
    exp = sm.getMPExpByMobId(mob.getTemplateId())

    #Stores Exp from killing mobs
    sm.setQRValue(GameConstants.MONSTER_PARK_EXP_QUEST, str(int(sm.getQRValue(GameConstants.MONSTER_PARK_EXP_QUEST)) + exp))

    #displays the EXP message
    sm.showWeatherNoticeToField("EXP reward "+ sm.formatNumber(sm.getQRValue(GameConstants.MONSTER_PARK_EXP_QUEST)) +" earned!", WeatherEffNoticeType.MonsterPark_ExpMsg)
sm.showEffectToField(WzConstants.EFFECT_CLEAR)