MASTEMA = 2151009

sm.setSpeakerID(MASTEMA)
if sm.sendAskYesNo("#h #. Have you fulfilled your mission?"):
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("#p2151009#, I wanted to talk to you...")

    sm.setSpeakerID(MASTEMA)
    sm.sendSay("(Eh?! #h # wanted to talk to me!?) Ah, yes? About what?")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("I would like to repeat my training.")

    sm.setSpeakerID(MASTEMA)
    sm.sendSay("Repeat? Ah, you must have hit another wall. I doubt more of the same is going to help, #h #...")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("I understand, but I seem to be out of options.")

    sm.setSpeakerID(MASTEMA)
    sm.sendSay("Hm. Wait...wait! I've got an idea. Ohh, I've got a really good idea! You want to get back to your old power level, right? #h #? In that case, you should tap into your vengeance! Why don't you practice against your inner rage?")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("Inner rage?")

    sm.setSpeakerID(MASTEMA)
    sm.sendSay("Yes! Rage is a potent source of power. I mean, you know that. But you won't have full access to it until you conquer it fully.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("You have a point. How can I fight my inner rage, though?")

    sm.setSpeakerID(MASTEMA)
    sm.sendSay("Well, #h #... I just happen to have the power to send you inside...yourself. It's not as weird as it sounds. More like meditation, really.")
    sm.sendSay("But you won't be completely out of danger. The damage you sustain there will transfer to your real body.")

    if sm.sendAskYesNo("Are you ready? (Press Yes to move automatically. You will have to forfeit the quest and restart if you fail.)"):
        sm.startQuest(parentID)
        sm.warpInstanceIn(924020020, False)
else:
    sm.sendSayOkay("Not much. You sure you're doing well.")
