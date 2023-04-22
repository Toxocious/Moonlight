JAY = 1531001

sm.setSpeakerID(JAY)
sm.sendNext("K, buddy, I've prepared something to mark your 2nd upgrade.")
if sm.sendAskYesNo("Did you like that bag I got you last time? I hope so, because I got you another! I told the company the first broke, so they sent me another."):
    # inv grow 1- 4
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.sendSayOkay("Keep it up, K. Not that you need me to tell you that.")
else:
    sm.sendSayOkay("I know you'll change your mind. Let me know when you do.")