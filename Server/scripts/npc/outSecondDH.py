response = sm.sendAskYesNo("Are you done with the Knighthood Exam? Should I let you out?")

if response:
    sm.warp(130020000)
else:
    sm.sendSayOkay("Okay, good luck hunting.")