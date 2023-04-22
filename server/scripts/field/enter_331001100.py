# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001100
# Main Street :: City Center

from net.swordie.ms.enums import TextEffectType

sm.lockForIntro()
sm.removeAdditionalEffect()
sm.hideUser(True)
sm.blind(1, -1, 0, 0, 0, 0)
#sm.playURLVideoByScript("http://nxcache.nexon.net/maplestory/video/yt/Kinesis1.html")

sm.zoomCamera(0, 1000, 0, 4830, -620)

sm.setObjectEffectPlay("city", "daylight", True, False)
sm.playSound("Sound/SoundEff.img/kinesis/signal")
sm.sendDelay(500)

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.zoomCamera(10000, 1000, 10000, 4450, -620)

sm.setObjectEffectPlay("city", "day2night", False, False)
sm.setObjectEffectAddPlay("city", "night", True)
sm.sendDelay(5000)

sm.OnOffLayer_On(3000, "title", 0, 0, 40, "Map/Effect2.img/kinesis/title", 4, 0, -1, 0)
sm.sendDelay(4000)

sm.OnOffLayer_Off(2000, "title", 0)
sm.sendDelay(2000)

sm.createFieldTextEffect("#fn³ª´Æðíμñ ExtraBold##fs26#2019, Seoul", 100, 2500, 4, 0, 0, 1, 4, TextEffectType.KinesisIntro, 0, 0)
sm.sendDelay(3000)

sm.zoomCamera(8000, 1000, 8000, 200, -300)

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.hideUser(False)
sm.unlockForIntro()

sm.warp(331001110, 0)