# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001110
# Hideout  :: Training Room 1

from net.swordie.ms.enums import TextEffectType

KINESIS = 1531000
JAY = 1531001

sm.lockForIntro()
sm.blind(1, 255, 0, 0)
sm.forcedFlip(True)
sm.removeAdditionalEffect()
# UNK925 ["Ambience.img/snowwave"] [64 00 00 00] [3C 00 00 00] ("Ambience.img/snowwave", 100, 60)
sm.playSound("Sound/Ambience.img/flare_ready")

sm.setIntroBoxChat(JAY)
sm.sendNext("#face9#Testing, testing, 1, 2, 3. OK. I think you can hear me. You ready to show me your stuff, K?")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("I don't think so, Jay. Something is very wrong down here.")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#What? No. I double-checked everything. My gear is perfect and amazing.")

sm.blind(1, 255, 0, 0)
sm.sendDelay(300)

sm.blind(0, 0, 0, 250)
sm.sendDelay(300)

sm.createFieldTextEffect("#fn³ª´Æðíμñ ExtraBold##fs26#Kinesis's Hideout\r\n#fs14#- Training Room -", 100, 2500, 4, 0, 0, 1, 4, TextEffectType.KinesisIntro, 0, 0)
sm.sendDelay(4000)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("Then why does it say I'm starting at #bLevel 10#k?")
sm.sendSay("Jay, you and I both know I can KILL it at #bLevel 10#k. Dial it up to something fun, will you?")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#Come on, K. I told you, I still need basic data on your agility and psychokinetic abilities. Since, y'know, you fried the hard drives I used last time.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Not my fault if your tech can't keep up. Just make sure I don't get bored, alright?")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#Whatever, dude. Look, I just want some basic data for now. Move forward and follow the signs.")

sm.playSound("Sound/Field.img/flowervioleta/wink")

sm.cameraSwitchNormal("go_tuto2", 1000)
sm.sendDelay(1000)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("Hold it. We're still missing something.")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face10#Seriously? What now?")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face3#We need music, man! Put on something for me to rock the block with.")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#......")

# STOP_AMBIENT_SOUND(or just 927) ["Ambience.img/snowwave"]
sm.playSound("Sound/SoundEff.img/blackHeaven/switch_push")
sm.sendDelay(300)

sm.changeBGM("Bgm42/TheBeast2", 0, 0)
sm.setIntroBoxChat(KINESIS)
sm.sendNext("#face2#Perfect. Let's get started.")

sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 3000, 0, 1477, -68)
sm.sendDelay(300)

sm.removeOverlapScreen(1000)
sm.playSound("Sound/SoundEff.img/PvP/80001740_Use")
sm.OnOffLayer_On(500, "0", 800, 0, 12, "Map/Effect2.img/kinesis/chaKine", 5, 1, -1, 0)
sm.OnOffLayer_Move(300, "0", -800, 0)
sm.sendDelay(4500)

sm.playSound("Sound/SoundEff.img/12thMS/jump_m")
sm.OnOffLayer_Off(500, "0", 0)
sm.OnOffLayer_Move(300, "0", 800, 0)
sm.sendDelay(500)

sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)

sm.removeOverlapScreen(1000)
sm.unlockForIntro()

