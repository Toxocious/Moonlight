# [Commerci Republic] The Blocked Canal


sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Should we head back to town?")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Not yet...")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Don't tell me you're going to look for her...")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Of course I am.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("She's long gone, Leon.")

sm.setSpeakerID(9390256) # Leon Daniella
response = sm.sendAskYesNo("You can go back to town if you want.")

if response:
    sm.setPlayerAsSpeaker() # Has to be Player Avatar
    sm.sendNext("And leave you here to get jumped by more fishmen? I'd never earn your father's trust that way.")
    sm.startQuest(parentID)
sm.dispose()
