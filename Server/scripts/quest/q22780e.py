# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setIntroBoxChat(THREE_MOON)
sm.sendNext("#bYour missing people#k, hmm?")


sm.setIntroBoxChat(NERO)
sm.sendSay("#face0#Whatcha think? Can we find them?")


sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("Locations with the greatest mana disruptions should yield what you seek.")
sm.sendSay("Allow me to review the reports I have received...")

sm.completeQuest(parentID)
sm.giveExp(1200)