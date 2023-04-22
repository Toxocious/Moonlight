# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setIntroBoxChat(NERO)
sm.sendNext("#face1#10 eggs! That's alla them. Good job.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("So we done here, Nero?")


sm.setIntroBoxChat(NERO)
sm.sendSay("#face2##fs25##rI told you, my name is not Nero!")
sm.sendSay("#face3#...Oh, whatever. Call me what you want. Grendel the Really Old is taking his sweet time, so we should get some firewood for cooking.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("Firewood? You're not asking me to chop wood, are you?")


sm.setIntroBoxChat(NERO)
sm.sendSay("#face0#As much as I'd like to see that, no. We're gonna hunt monsters. So, uh, chopping monsters instead of wood!")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("More fighting? Really? Geez, do you people beat up monsters for everything you need?")

sm.setIntroBoxChat(NERO)
if sm.sendAskYesNo("#face0#Yup! Go hunt the #bAxe Stumps#k to collect 15 pieces of Firewood.\r\nAnd then take them to #bThree Moon#k."):
    sm.sendNext("#face1#Let's head to the right. Oh boy, I can't WAIT to get my face into those eggies! There are plenty of #bAxe Stumps#k at #bWhere the Sun Rises and Where the Forest Sings#k.")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("#face2#Rawr! No work, no grub!")