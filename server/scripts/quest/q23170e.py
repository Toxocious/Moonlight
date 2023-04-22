# Blaster Student of the Resistance
FERDI = 2151010
ELEX = 2151000
AGILITY_ENHANCEMENT_COURSE_UNLOCK = 23128

sm.setSpeakerID(FERDI)
sm.sendNext("We meet again. Congratulations on becoming a part of the Resistance. I've been keeping an eye on you from the start. #p" + str(ELEX) + "# saw something in you and sent you here.")
sm.sendSay("Well, since you're part of our group now, you should train and level up. I'll teach you what you need to know to be a contributing member of the Resistance.")
sm.createQuestWithQRValue(AGILITY_ENHANCEMENT_COURSE_UNLOCK, "1")
sm.completeQuest(parentID)