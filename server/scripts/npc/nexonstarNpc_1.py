sm.setSpeakerType(1)
sm.setParam(548)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9001137) # Machine Wheel
if sm.getFieldID() == 100000000:
    if sm.sendAskYesNo("Would you like to go to the Celestial Event Hall?"):
        sm.warp(450013840)
else:
    selection = sm.sendNext("What would you like to do?#b\r\n#L0#Join the current Event #l\r\n#L1#Spend my Event Coins#l\r\n#L2#Go back to Henesys!#l")
    if selection == 0:
        sm.sendNext("There is no Event going on")
    elif selection == 1:
        sm.sendNext("Open shop spot")
    elif selection == 2:
        sm.warp(100000000)



