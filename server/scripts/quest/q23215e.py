# 23215 | True Awakening
sm.setSpeakerID(2450017)
sm.sendNext("You made it back, #h #! How are you feeling?")
sm.setPlayerAsSpeaker()
sm.sendNext("Fighting myself from the past wasn't easy, but I remembered many of the skills I had forgotten.")
sm.setSpeakerID(2450017)
if sm.sendAskYesNo("Excellent! I was hoping it would work like that. You really do feel strong now. Hey, #h #, do you want me to write this all down for you?"):
    if sm.canHold(1142344):
        sm.giveItem(1142344)
        sm.jobAdvance(3112)
        sm.giveAndEquip(1099004)  # todo: upgrade instead of replace secondary? (potentials)
        sm.completeQuest(parentID)
        sm.sendSayOkay("Sounds like you've gotten all your old powers back. For now, #h #, I suggest you focus on training steadily and improving your basics.")
    else:
        sm.sendSayOkay("Please make space in your Equip inventory.")
