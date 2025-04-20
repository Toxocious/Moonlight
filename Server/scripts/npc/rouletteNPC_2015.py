import random

halloweenItems  = [3018231,3018238,3018239,3018240,3018241,3018242,3018243,3018246,1702423,1112142,1112253,1052644,1004002,1702469,1702470,1702471,1702473,1702458,1702472,1702478,1702477,1702529,1702390,1012501,1102774,1102576,1102588,1004482,1052864,1051376,1032228,1072871,1702180,1102211,1102273,1004873,1050441,1073183,1102974,1051509,1702726,1053094,1053093,1702714,1004841,1702710,1115032,1115121,1115079,1115168]
randomHalloweenItem = random.choice(halloweenItems)

sm.setSpeakerType(2)
sm.setParam(548)
sm.setColor(2)
sm.setInnerOverrideSpeakerTemplateID(9010063) # Machine Wheel
sm.sendNext("Would you like to spin the Celestial Wheel of mystery?\r\nYou're going to need 10 #i4310244#")
if sm.hasItem(4310244, 10):
    if sm.canHold(randomHalloweenItem):
        sm.sendNext("It looks like you have 10 #bGhost coins#k! Spin the wheel?")
        sm.sendNext("You rolled a #i" + str(randomHalloweenItem) + "#!")
        sm.consumeItem(4310244, 10)
        sm.giveItem(randomHalloweenItem)
    else:
        sm.sendNext("Please check if you have enough available inventory space, silly!")

else:
    sm.sendNext("It looks like you don't have 10 #bGhost Coins#k!")