#   [Job Adv] (Lv.60)   Way of the Berseker

blackCharm = 4031059
job = "Berserker"

sm.setSpeakerID(2020008)
if sm.hasItem(blackCharm, 1):
    sm.sendNext("I am impressed, you surpassed the test. Only few are talented enough.\r\n"
                "You have proven yourself to be worthy, thus I shall mold your body into a #b"+ job +"#k.")
else:
    sm.sendSayOkay("You have not retrieved the #t"+ blackCharm +"#s yet, I will be waiting.")
    sm.dispose()


sm.consumeItem(blackCharm, 1)
sm.completeQuestNoRewards(parentID)
sm.jobAdvance(131) # Crusader
sm.sendSayOkay("You are now a #b"+ job +"#k.")
sm.dispose()
