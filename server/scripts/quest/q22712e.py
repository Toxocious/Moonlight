# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001130
# Hideout  :: HQ
from net.swordie.ms.enums import UIType
KINESIS = 1531000
JAY = 1531001

sm.setNpcOverrideBoxChat(JAY)
sm.sendNext("Actually, wait. Let me get a preliminary physical reading right now.")
if (sm.getAP() > 0):
    sm.sendSay("#face3#...That's weird. Hey, K. Looks like you have an ability you can improve.")
    sm.sendSay("#face2#You can increase your #gbasic abilities#k and #gskill levels#k by leveling up. Don't wait to train if you want to get stronger.")
    sm.sendSay("#face2#Since your brain controls all your powers, I would focus on improving your #gINT#k. You'll never be as smart as me, but you can use the Auto-Assign function to do it smartly.")

    sm.setNpcOverrideBoxChat(KINESIS)
    sm.sendSay("Very funny, Jay. Sounds like you forgot about the last time I whupped you at chess.\r\n\r\n#b(Use all your AP to increase your stats before speaking to Jay again.)#k")

    sm.progressMessageFont(3, 20, 20, 0, "Level up to increase your stats and skill levels.")
    sm.openUI(UIType.UI_STAT)
    sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_03", 100)
elif sm.getSP() > 0:
    sm.sendSay("#face3#...That's weird. Hey, K. Looks like you have an ability you can improve.")
    sm.sendSay("#face3#What are you doing, K?\r\nYou forgot to fix your 1st Job #bSkill Levels#k. Are you finally slipping?")
    sm.sendSay("#face2#You know you can assign skills to any hotkey you want, right?")

    sm.setNpcOverrideBoxChat(KINESIS)
    sm.sendSay("#face2#I know, I know. Just wanted to make sure you're paying attention. Heh.\r\n\r\n#b(Use all of your SP to increase your Skill Levels and then speak to Jay again.)#k")

    sm.progressMessageFont(3, 20, 20, 0, "Level up to increase your stats and skill levels.")
    sm.openUI(UIType.UI_STAT)
    sm.openUIWithOption(UIType.UI_SKILL, 142001001)
    sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_03", 100)
    sm.avatarOriented("UI/tutorial.img/kinesis/5")
#elif sm.getBeginnerSP() > 0:
#    sm.sendSay("#face3#Well-l-l-l... You increased your 1st Job stuff, but forgot your #bBeginner Skills#k. Can't ignore the fundamentals, K.")
#    sm.openUIWithOption(UIType.UI_SKILL, 140000291)
else:
    sm.consumeItem(2000040)
    sm.giveExp(600)
    sm.completeQuest(parentID)
    sm.completeQuest(22715)
    sm.sendSayOkay("Geez, you rebounded from your training super fast. I don't understand how you're in such good shape.")