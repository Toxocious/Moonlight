# 59001 - Crybaby

sm.setSpeakerID(9390305)
sm.sendNext("Also, I saw #b#p9390306##k crying... Do you know anything about that?")
sm.setPlayerAsSpeaker()
sm.sendNext("Well...")
sm.setSpeakerID(9390305)
if sm.sendAskYesNo("#b#h ##k! You have to treat your friends better! Apologise to #b#p9390306##k!"):
    sm.startQuest(parentID)
    sm.setPlayerAsSpeaker()
    sm.sendNext("You got it! A true hero is excellent at delivering apologies, as you just experienced!")
    sm.dispose()
else:
    sm.dispose()