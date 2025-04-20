# 59002 - Brave Like a Hero

sm.setSpeakerID(9390306)
sm.sendNext("What is this with you and that hero thing? Do you really think you're cut out to be a hero?")
sm.setPlayerAsSpeaker()
sm.sendNext("Sure, I'm going to be a legend, just like the five Maple Heroes!")
sm.setSpeakerID(9390306)
sm.sendNext("Yeah. *snort* Sure.")
sm.setPlayerAsSpeaker()
sm.sendNext("You don't believe me? That's okay. What can I do to prove it to you?")
sm.setSpeakerID(9390306)
if sm.sendAskYesNo("You know #bWolf Forest#k to the east? The place even grown-ups are afraid of? Go there... ALONE. Then maybe I'll believe you'll be a hero someday."):
    sm.setPlayerAsSpeaker()
    sm.sendNext("You got it! Be back in a jiffy!")
    sm.setSpeakerID(9390306)
    sm.sendNext("W-wait! Really? Are you sure?")
    sm.setPlayerAsSpeaker()
    sm.sendNext("Bah, a hero like me ain't scared of no forest!")
    sm.startQuest(parentID)
    sm.dispose()
else:
    sm.dispose()