# [Grand Athenaeum] Warp out
sm.removeEscapeButton()
sm.setSpeakerID(2500001)

answer = sm.sendSay("Return to the Grand Athenaeum?\r\n\r\n#b#L0#Return to the Grand Athenaeum. #l\r\n#L1#Not just yet.")
if answer == 0:
    sm.sendNext("Remembering this location...")
    sm.warp(302000000, 0)
elif answer == 1:
    sm.addEscapeButton()
    sm.sendSayOkay("You can go back if you still have stories to experience.")
