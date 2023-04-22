#   [Job Adv] (Lv.30)   Way of the Bandit

darkMarble = 4031013
job = "Night Lord"

sm.setSpeakerID(1052001)
if sm.hasItem(darkMarble, 30):
    sm.sendNext("I am impressed, you surpassed the test. Only few are talented enough.\r\n"
                "You have proven yourself to be worthy, I shall mold your body into a #b"+ job +"#k.")
else:
    sm.sendSayOkay("You have not retrieved the #t"+ darkMarble+"#s yet, I will be waiting.")
    sm.dispose()


sm.consumeItem(darkMarble, 30)
sm.completeQuestNoRewards(parentID)
sm.jobAdvance(420) # Bandit
sm.sendNext("You are now a #b"+ job +"#k.")
sm.dispose()
