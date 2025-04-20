# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001130
# Hideout  :: HQ
JAY = 1531001

sm.setNpcOverrideBoxChat(JAY)
sm.sendNext("Data looks good so far. Nicely done.")

if sm.sendAskYesNo("Okay, that's enough for today. Have a drink and get some rest. We're still not clear on how much stress your powers put on your body."):
    sm.startQuest(parentID)
    sm.giveItem(2000040)
    
    sm.setIntroBoxChat(JAY)
    sm.sendNext("The drink I gave you went to your inventory. Just like equipping gear, you can double-click that drink to, y'know, drink it. Now, get some rest. I want to check your physical indexes afterwards.")

    sm.progressMessageFont(3, 20, 20, 0, "Collect Psychic Points to unleash your ultimate skill.")
    sm.avatarOriented("UI/tutorial.img/kinesis/3")
    sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_07", 100)
else:
    sm.sendSayOkay("What? I thought you liked this one. If you wanted something else, you should have told me.")