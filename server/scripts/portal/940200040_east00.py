# Bad Signs is supposed to be completed on a thought bubble pop up after killing all the tigers but it isn't
if sm.hasMobsInField():
    sm.chatScript("You need to complete the <Bad Signs> quest first.")
else:
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("It's my fault. I should've kept a closer eye on you... Please be safe. I'm coming to save you!")
    sm.giveExp(5000)
    sm.completeQuest(38021)
    sm.warp(940200050, 0)
