# Blaster - The First Mission
CLAUDINE = 2151003
ELEX = 2151000
ELIMINATE_PATROL_ROBOT_UNLOCK = 23129

sm.setSpeakerID(CLAUDINE)
sm.sendNext("Greetings, #h #! My name is #p" + str(CLAUDINE) + "#, and I am in charge of Resistance mission assignments. It's strange seeing you here instead of in town...")
sm.sendSay("I should actually be a Thief Job instructor but I've taken this position since the Resistance doesn't train thieves.")
sm.sendSay("In any case, since I'm in charge of missions, you'll be seeing me more often than even #p2151000#, your job instructor. Now, let's drive those Black Wings out of our territory.")
sm.createQuestWithQRValue(ELIMINATE_PATROL_ROBOT_UNLOCK, "1")
sm.completeQuest(parentID)