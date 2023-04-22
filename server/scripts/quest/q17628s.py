# [Commerci Republic] Delfino Deleter 3

sm.setSpeakerID(9390256) # Leon Daniella
response = sm.sendAskYesNo("Shall we?")

if response:
    sm.setPlayerAsSpeaker() # Has to be Player Avatar
    sm.sendNext("#b(I have a bad feeling about this whole thing...)")
    sm.startQuest(parentID)
else:
    sm.sendNext("Alright, let me know when you are ready!")
sm.dispose()
