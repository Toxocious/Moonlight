# Thomas | Amoria Ambassador

if sm.getFieldID() == 100000000:
    response = sm.sendAskYesNo("I can take you to Amoria Village. Are you ready to go?")
else:
    response = sm.sendAskYesNo("I can take you to back Henesys. Are you ready to go?")


if response:
    if sm.getFieldID() == 100000000:
        sm.warp(680000000, 0)
    else:
        sm.warp(100000000, 0)
else:
    sm.sendSayOkay("Ok, feel free to hang around until you're ready to go!")
