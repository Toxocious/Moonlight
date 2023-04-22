MASTEMA = 2151009

sm.setSpeakerID(MASTEMA)

if not sm.canHold(1142556):
    sm.sendNext("Please clear some space in your equip inventory.")
    sm.dispose()

sm.sendNext("You made it back, #h #! How are you?")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I didn't know I had such anger within me. It is not easy to control.")

sm.setSpeakerID(MASTEMA)
if sm.sendAskYesNo("But you succeeded, #h #! I should write this down for posterity, right?"):
    sm.completeQuest(parentID)
    sm.giveItem(1142556)
    sm.giveAndEquip(1099009)
    sm.jobAdvance(chr.getJob()+1)
    sm.sendSayOkay("Your inner rage is now under your control, #h #! All that's elft for you is to keep training.")
