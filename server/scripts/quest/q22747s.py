# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setNpcOverrideBoxChat(NERO)
sm.sendNext("#face0#Now it's time to hunt #bCurse Eyes#k! ")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("And I suppose you want #bCurse Eye#k tails too? ")

sm.setNpcOverrideBoxChat(NERO)
sm.sendSay("#face0#What? No! No way, ick! Curse Eyes are sour and salty. Just whack a bunch of them. ")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("Ah-HA! So I AM grocery shopping for you!")

sm.setNpcOverrideBoxChat(NERO)
if sm.sendAskYesNo("#face1#Don't get smart with me! This is SERIOUS training for SERIOUS people! So get SERIOUS and hunt down #b50#k #bCurse Eyes#k. SERIOUSLY!"):
    sm.startQuest(parentID)
    sm.sendNext("#face0#Curse Eyes are found #bWhere the Soil Sleeps#k.")
else:
    sm.sendSayOkay("#face2#You really need to train more, like right now! Think this through!")


