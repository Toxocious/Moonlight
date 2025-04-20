items = [1004039, 1102650]
removeitems = [0]

if sm.hasQuest(38023):
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("Wow, this is... really nice. This medal means they've accepted me. No more tests, no more suspicion. Is this... what having a family is like?")
    sm.sendSay("It's been so long. The Black Mage, the seal, Maple World. Everything feels like a dream. I've gotten used to things around here. Maybe someday I can go back to Maple World... but do I even want to?")
    sm.giveItem(1142671)
    sm.completeQuestNoRewards(32024)
    sm.completeQuest(38023)
    sm.giveExp(4000)
elif sm.hasQuest(38018):
    sm.lockInGameUI(True, False)
    sm.forcedInput(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("Their special gift is... #rfox ears and a tail#k? Huh. Well, um... could be worse, I guess.")
    sm.sendSay("I really, really don't like this, but not accepting it would be out of line. And I might as well give it a try...")

    sm.avatarLookSet(items)
    sm.sendDelay(1000)

    sm.sendNext("......!")

    sm.avatarLookSet(removeitems)
    sm.sendSay("...No, I can't do this! Not after what I've been through. I... I like it here, but I don't want to lose who I am. I don't like this. I... I should hide this before I do anything else.")

    sm.completeQuest(38018)
    sm.giveExp(3000)
    sm.lockInGameUI(False)

    sm.warp(940200030, 0)
elif sm.hasQuest(38008):
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("What...is this? They wrapped something in this leaf. It's quite heavy and warm. Maybe a little wet, too. Something's dripping from it. Wait, this mushy texture... Is this...?")
    sm.sendSay("Did they seriously give me #rraw liver#k? This is my special gift? Right, something about #rbeast livers#k being the best...")
    sm.sendSay("Well... People eat raw fish, so maybe this isn't all that bad. I can eat this... I can... Ugh, why do my tastebuds have to put up with this?")
    sm.giveExp(1200)
    sm.completeQuest(38008)
    sm.startQuest(38009)
