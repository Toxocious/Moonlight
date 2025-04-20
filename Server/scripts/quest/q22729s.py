# Created by MechAviv
# Kinesis Introduction
KINESIS = 1531000
NERO = 1531003

sm.setIntroBoxChat(KINESIS)
sm.sendNext("A cat? Here? Hey kitty, this place is dangerous. ")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face4#Meow.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Geez, you're still a kitten. ")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face5#Meow! ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("What are you so mad about? You're such a small kitty. Did you lose your mommy?")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face5#MEOW! ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Ha. Yeah, you've got some fight in you. You look lost, want to come with me? ")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face4#...Meow! ")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("Good. Let's call you... Nero! How about Nero? ")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face4#..")


sm.sendSay("#face2#You like that? Of course you do. I came up with it.")


sm.sendSay("#face4#Meow... ")

if sm.sendAskYesNo("#b(Take it with you? )#k"):
    sm.sendNext("#b(Hurry back to Jay.)#k")
    sm.setQuestEx(22700, "blackCat", "2")
    sm.startQuest(parentID)
    sm.setPartner(True, 1531003, 80001789, False)
else:
    sm.sendSayOkay("#face4#Meow... ")
    sm.dispose()