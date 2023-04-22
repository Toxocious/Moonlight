# [Commerci Republic] Delfino Deleter 5

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("I keep thinking I see a Delfino out of the corner of my... WHAT THE?!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("It's the Delfinos! They're back!")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Who's that scary-looking fish behind them?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("He's bad news, that's who!")

sm.setSpeakerID(9390208) # Riverson
sm.sendNext("Why you attack my people?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("What the...? You guys attacked the Merchant Union first! Who ARE you, anyway?")

sm.setSpeakerID(9390208) # Riverson
sm.sendNext("I am... RIVERSON! you pay for wrongdoing!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Wrongdoing? What did we do?")

sm.setSpeakerID(9390208) # Riverson
response = sm.sendAskYesNo("No play coy! You know what you did! No more talk, prepare for PAIN!")

if response:
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.completeQuest(17631) # Finish a cutscene quest
    sm.warpInstanceIn(865020061)
else:
    sm.sendSayOkay("... Oh... Okay... Uhh... I will wait for a bit!")
sm.dispose()
