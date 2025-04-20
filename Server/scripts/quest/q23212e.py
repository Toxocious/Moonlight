# 23212 | Contract with Mastema

isDS = chr.getJob() == 3100

sm.setSpeakerID(2450017)

if not sm.canHold(1142342):
    sm.sendSayOkay("Please make space in your equip inventory.")
    sm.dispose()

if sm.sendAskYesNo("Everything is ready. Let us begin the contract ritual. Focus on your mind."):
    sm.jobAdvance(isDS and 3110 or 3120)
    sm.giveItem(isDS and 1142342 or 1142554)
    sm.giveAndEquip(isDS and 1099002 or 1099007)  # todo: upgrade instead of replace secondary? (potentials)
    sm.completeQuest(parentID)
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(You feel a curious energy flowing into you.)")
    sm.setSpeakerID(2450017)
    sm.sendNext("There... our contract is made. Now we can communicate through our minds. Isn't that neat?")
