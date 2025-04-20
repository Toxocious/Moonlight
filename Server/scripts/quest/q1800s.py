# 1800 - [Evolution System] Suspicious Movement on the path
sm.setSpeakerID(9400115)
if sm.sendAskYesNo("Calling the Alliance. The Black Wings are operating deep within the mines below Edelstein. Something fould is afoot. Need help in #m310010000# immediately. Please Accept"):
    sm.warp(310010000, 0)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)