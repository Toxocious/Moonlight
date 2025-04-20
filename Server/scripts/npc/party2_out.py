# Sgt. Anderson(2040047) | Abandoned tower : Exiting Ludibrium Dimension pq
sm.setSpeakerID(2040047)
response = sm.sendAskYesNo("Are you sure you want to leave your party behind and leave the quest?")

if response == 0:
    sm.sendNext("Thank you for choosing to stay here and finish this quest.")
elif response == 1:
    sm.sendNext("Okay, I will send you back to the 101st floor of the Eos tower.")
    sm.warpInstanceOut(221023300)