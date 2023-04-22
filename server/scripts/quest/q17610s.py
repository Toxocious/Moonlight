# [Commerci Republic] Berry Concerned 1

sm.setSpeakerID(9390201) # Mayor Berry
sm.sendNext("Hm...")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Mayor Berry, are you all right?")

sm.setSpeakerID(9390201) # Mayor Berry
sm.sendNext("I'm worried about my fish... There are these monsters that were stealing my fish the other day..")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("I'll take care of those monster for you, Mayor.")

sm.setSpeakerID(9390201) # Mayor Berry
response = sm.sendAskYesNo("Will you really?")

if response == 1:
    sm.sendNext("If you could eliminate 100 #r#o9390807##k, I would be very grateful.")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("Oh, alright.. that's too bad.")
sm.dispose()
