# Manual Labor

# Constants
LIMBERT = 1106002
EGG = 4033196

sm.setSpeakerID(LIMBERT)
selection1 = sm.sendNext("Where's the eggs? I told you to get eggs. If you broke them... Wait a second, what happened to you?\r\n #b\r\n#L0# Uh, well, you know how you told me not to mess with Bigby? Well... I kinda... He got out.#l")


if selection1 == 0:
    sm.sendNext("What?!! I swear to every deity I can think of, you will starve to death if that dog is not in my yard by dinnertime.")
else:
    sm.dispose()

sm.completeQuestNoRewards(20033)
sm.consumeItem(EGG, 10)
sm.giveItem(2001500, 30)
sm.giveItem(2001503, 30)
sm.addLevel(2)
sm.warpInstanceIn(913070004, 0)
sm.dispose()      