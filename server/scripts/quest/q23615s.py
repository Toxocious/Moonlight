# Getting Caught?! ; Xenon 3rd Job

STEPHAN = 2159421

sm.setSpeakerID(STEPHAN)
sm.sendNext("The Watchman are all over. I need to get out of here!")
sm.setPlayerAsSpeaker()
if sm.sendAskYesNo("The Watchman is headed this way, but I can't arrouse any suspicions. Maybe I should just try to act casual!"):
    sm.sendNext("No, I won't run, I can do this. I just need to be a Black Wing. I am a Black Wing, I am a Black Wing, I am a...")
else:
    sm.sendNext("No, I shouldn't be nervous, I can do this. I just need to be a Black Wing. I am a Black Wing, I am a Black Wing, I am a...")

sm.warpInstanceIn(931060030)
sm.startQuest(parentID)
