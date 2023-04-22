#   [Job Adv] (Lv.30)   Way of the Mage IL

darkMarble = 4031013
job = "Mage (Ice, Lightning)"

sm.setSpeakerID(1032001) # Grendel the Really Old
if sm.hasItem(darkMarble, 30):
    sm.sendNext("I am impressed, you surpassed the test. Only few are talented enough.\r\n"
                "You have proven yourself to be worthy, I shall mold your body into a #b"+ job +"#k.")
else:
    sm.sendSayOkay("You have not retrieved the #t"+ darkMarble+"#s yet, I will be waiting.")
    sm.dispose()


sm.consumeItem(darkMarble, 30)
sm.completeQuestNoRewards(parentID)
sm.jobAdvance(220) # Mage IL
sm.sendNext("You are now a #b"+ job +"#k.")
sm.dispose()
