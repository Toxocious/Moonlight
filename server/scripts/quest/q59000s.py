# 59000 - The Town Prankster

sm.setSpeakerID(9390304)
sm.sendNext("#b#h ##k, are you responsible for this? I know it's fun to pick on #b#p9390305##k, but it's really not very nice.")
sm.sendNext("I'm ashamed of you. Go apologise to #b#p9390305##k!")
if sm.sendAskYesNo("A true hero is never afraid to apologise!"):
    sm.startQuest(parentID)
    sm.setPlayerAsSpeaker()
    sm.sendNext("I'm going to give the most epic apology ever!")
    sm.dispose()
else:
    sm.dispose()