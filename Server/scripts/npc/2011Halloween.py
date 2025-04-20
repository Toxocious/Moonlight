sm.setSpeakerID(9000095) # Witchy Woman
sm.sendNext("Do you want to test your knowledge?")

response = sm.sendAskYesNo("Let's get started shall we")

if response == 1:
    sm.setHintText("This is a hint")
    sm.setAnswer("Yes")
else:
    sm.sendNext("nvm")
sm.dispose()