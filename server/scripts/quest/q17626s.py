# [Commerci Republic] Delfino Deleter 1

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("#h0#! I knew my loyal sidekick wouldn't abandon me! How'd you get around daddy?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("I didn't. He said I could come. As an official ally.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("You must really have a way with words, like me. I'm glad you made it. You can learn a lot!")

response = sm.sendAskYesNo("Now, are we ready to hunt some fish?")

if response:
    sm.sendNext("Let's see who's faster!")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("What?.. You're not ready yet?")
sm.dispose()
