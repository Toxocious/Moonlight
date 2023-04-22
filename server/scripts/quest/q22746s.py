# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setIntroBoxChat(NERO)
sm.sendNext("#face0#Okay, now it's my turn! I'll whip you into shape! Get ready for the hardest training of your LIFE!")
sm.sendSay("#face0#First, hunt #bEvil Eyes#k!")
sm.sendSay("#face1##bEvil Eyes#k might look all weird and creepy, but they're actually SUPER tasty!")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Kid, are you sending me to get you lunch? What kind of training is that?")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face0#NO. And I'm not a kid! I'm your trainer, and this is SERIOUS training! ")

sm.setIntroBoxChat(NERO)
if sm.sendAskYesNo("#face0#So! Go hunt #b50 Evil Eyes#k and collect #b10 Evil Eye Tails#k. Are you ready?"):
    sm.startQuest(parentID)
    sm.sendNext("#face0##bWhere Trees Dance#k has lots of #bEvil Eyes#k. Head #bright#k to hunt them. ")
else:
    sm.sendSayOkay("#face2#Rawr! If a man will not work, he shall not eat!")


