SHIBA = 1057000
RYDEN = 1057001

sm.setSpeakerID(SHIBA)
sm.sendNext("Slow. Much, much too slow. Like a wilting leaf... Are you sure you have what it takes to learn to dual-wield?")
sm.sendSay("My insults slide like rain off your back... Hey, Ryden! What's with the new recruit?")
sm.completeQuest(parentID)
sm.giveExp(50)

sm.spawnNpc(RYDEN, -880, 141)
sm.removeNpc(1057000)
sm.showNpcSpecialActionByTemplateId(RYDEN, "summon", 0)