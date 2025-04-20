# 272030400 - Arkarium

response = sm.sendAskYesNo("Would you like to leave?")

if response:
    sm.clearPartyInfo(272030300)
sm.dispose()
