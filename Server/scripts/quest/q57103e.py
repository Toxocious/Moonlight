# 57103 - Picking up the Pieces

sm.setSpeakerID(9130024)
sm.sendNext("This place appears to be our land, but do you feel it? The air is too sweet, the ground is too soft.")
sm.setPlayerAsSpeaker()
sm.sendNext("But it looks like Japan, like our home.")
sm.setSpeakerID(9130024)
sm.sendNext("I too believed this place to be Japan, but I assure you it is not. We are in a different world. Whatever that strange light was at Honnou-Ji, it has sent us somewhere we could never have imagined.")
sm.setPlayerAsSpeaker()
sm.sendNext("This sounds preposterous, yet...")
sm.setSpeakerID(9130024)
if sm.sendAskYesNo("I will take you to the others. We have established a temporary base of operations, hidden in the forest at the top of this hill. You will seee that I speak the truth."):
    sm.completeQuest(parentID)
    sm.dispose()
else:
    sm.sendNext("Speak to me again after you wish to enter our base of operations.")
    sm.dispose()