# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setNpcOverrideBoxChat(NERO)
sm.sendNext("#face0#Okay, I've got one more for you. You're going after #bCold Eyes#k this time! Hunt #b50 Cold Eyes#k and bring back #b10#k tails.")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#face2#I guess #bCold Eye#k tails are pretty good, then?")

sm.setNpcOverrideBoxChat(NERO)
sm.sendSay("#face0#Not really. #bCold Eye#k tail is more like comfort food. A bit bitter, but in a refreshing way. A lot of us ate Cold Eye Noodle Soup when we were little!")

sm.setNpcOverrideBoxChat(NERO)
if sm.sendAskYesNo("#face0#That's beside the point, though. Go hunt down #b50#k #bCold Eyes#k. "):
    sm.startQuest(parentID)
    sm.sendNext("#face0#Go to #bWhere the Sky Smiles#k to find the Cold Eyes. ")
else:
    sm.sendSayOkay("#face2#You really need to train more, like right now! Think this through!")


