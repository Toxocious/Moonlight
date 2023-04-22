# [Commerci Republic] A Chat with Gilberto


sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("So, what happened to the delfinos?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("We defeated the Delfinos. They won't be causing any trouble for a while.")

sm.setSpeakerID(9390203) # Gilberto Daniella
response = sm.sendAskYesNo("Really? As easy as that? Did you encounter any difficulties along the way?")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("It got tougher over time, but nothing we couldn't handle")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("To be totally honest, sir. Leon did a great job leading the squad.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("(Psst, shouldn't we tell him about HER?)")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("I know my son had it in him, buried somewhere deep deep deep deep deep deep deep deep "
                "deep deep deep deep deep deep deep deep inside.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Maybe you're ready for more responsibility, Leon")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Oh, we also found a strange barrier on our way back.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("What do you mean, 'barrier'?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("We're not sure. We didn't think it would be wise to investigate further without consulting with you first.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("I'll send some men out there to check it out. Go rest now. You've earned it.")
sm.startQuest(parentID)
sm.dispose()
