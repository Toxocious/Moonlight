#   [Job Adv] (Lv.60)    Blade Lord

blackCharm = 4031059
job = "Blade Lord"
sm.setSpeakerID(2020011) # Alec

if sm.hasItem(blackCharm, 1):
    sm.sendNext("I am impressed, you surpassed the test. Only few are talented enough.\r\n"
                "You have proven yourself to be worthy, thus I shall mold your body into a #b"+ job +"#k.")
    sm.consumeItem(blackCharm, 1)
    sm.completeQuestNoRewards(parentID)
    sm.sendSayOkay("You are now a #b"+ job +"#k.")
    sm.jobAdvance(433) # Blade Lord
else:
    sm.sendSayOkay("You have not retrieved the #t"+ blackCharm +"# yet, I will be waiting.")


