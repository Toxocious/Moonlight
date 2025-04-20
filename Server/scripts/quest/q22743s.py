# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setIntroBoxChat(THREE_MOON)
sm.sendNext("Our meal has come to be.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("Please tell me that means we can eat it.")


sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("Controlling your cravings is yet another key to disciplining your mind. Focus less on your hunger, and more on the power it masks.")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face1#OMNOMNOMNOM")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#......")

sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("Grendel the Really Old remains absent. Perhaps you and the child should locate him.")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face1#OMNOMNOMNOM")

sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("...Perhaps you should locate him. I fear our youthful friend may be, ah, otherwise occupied.")

if sm.sendAskYesNo("Make your way to the #bGiant Tree#k and speak with #bFanzy#k. Surely the feline knows of Grendel's whereabouts."):
    sm.sendNext("Remember, it is at the #bGiant Tree#k that you will find #bFanzy#k the cat.")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("...")