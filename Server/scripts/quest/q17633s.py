# [Commerci Republic] Back to Town
sm.setSpeakerID(9390256) # Leon Daniella
response = sm.sendAskYesNo("Should we go right now?")

if response:
    sm.warp(865000000, 0) # San Commerci
    sm.startQuest(parentID)
sm.dispose()
