# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: Second Floor Corridor
from net.swordie.ms.enums import TextEffectType

sm.unlockForIntro()
if "1" in sm.getQuestEx(22700, "coNight"):
    sm.setSpineObjectEffectPlay(False, "corridor", "night", True, False)
sm.createFieldTextEffect("#fn������� ExtraBold##fs26#Second Floor: Corridor\r\n#fs14#- Private School for the Gifted -", 100, 2500, 4, 0, 0, 1, 4, TextEffectType.KinesisIntro, 0, 0)