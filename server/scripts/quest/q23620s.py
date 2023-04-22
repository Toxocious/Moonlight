# A Warm(er) Welcome ; Xenon Level 200 Quest

CLAUDINE = 2151003

sm.setSpeakerID(CLAUDINE)
sm.sendNext("Hello, #b#h ##k? Do you have time to come by Edelstein?")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Is everything okay?")
sm.setSpeakerID(CLAUDINE)
if sm.sendAskYesNo("Yes, we're fine, but I could use your help. Can you come right away?"):
    sm.startQuest(parentID)
    sm.sendSayOkay("Come talk to me again later.")
