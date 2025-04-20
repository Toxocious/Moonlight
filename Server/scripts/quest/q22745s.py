# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setIntroBoxChat(THREE_MOON)
sm.sendNext("The tighter you grasp the water, the faster it will flow through your fingers.")
sm.sendSay("Hold not with your hands what you can hold in a vessel. ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Are you telling me to... get a cup? Are you thirsty?")

sm.setIntroBoxChat(NERO)
if sm.sendAskYesNo("#face0#He's saying you need to train your body! I'll fill you in on the details! "):
    sm.setIntroBoxChat(THREE_MOON)
    sm.sendNext("Go. Speak with the one you call Nero.\r\n\r\n#b(Train in this area until you reach level 30.)")
    sm.startQuest(parentID)
else:
    sm.setIntroBoxChat(THREE_MOON)
    sm.sendSayOkay("You will not leave this place without training.")


