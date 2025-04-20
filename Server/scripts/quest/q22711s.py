# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001120
# Hideout  :: Training Room 2
JAY = 1531001

sm.startQuest(parentID)
sm.setIntroBoxChat(JAY)
sm.sendNext("#face9# Let me see... got it. Perfect. Now that you've suffered through the boring yet EXTREMELY IMPORTANT part, we can cut you loose. Start with those 5 Training Robot Bs.")

sm.spawnMob(2700300, 703, -150, False)
sm.spawnMob(2700300, 703, -150, False)
sm.spawnMob(2700300, 85, -179, False)
sm.spawnMob(2700300, 85, -179, False)
sm.spawnMob(2700300, 85, -179, False)
sm.playSound("Sound/SoundEff.img/blackHeaven/metaldrop")
sm.cameraSwitchNormal("pt_VP", 1000)
sm.sendNext("#face9#Pick up anything you want with the Z key. Don't worry, I've got plenty of parts to rebuild these with. When you're done, take the door on the left to come meet me.")
