# 140000000
GIANT_POLEARM = 1201001

sm.setSpeakerID(GIANT_POLEARM)
if sm.sendAskYesNo("#b(Are you certain that you were the hero that wielded the #p1201001#? Yes, you're sure. You better grab the #p1201001# really tightly. Surely it will react to you.)#k"):
    if not sm.canHold(1142129): # Medal
        sm.sendSayOkay("Please make some space in your equipment inventory.")
        sm.dispose()
    sm.giveItem(1142129)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.removeSkill(20000297)
    sm.jobAdvance(2100)
    sm.resetAP(False, 2100)
    sm.removeSkill(20001296)
    sm.giveSkill(20001296)
    sm.chatScript("You learned the Back to Rien skill.")

    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(You might be starting to remember something...)#k")

    sm.lockInGameUI(True, False)
    sm.warp(914090100, 0)
else:
    sm.sendNext("#b(You need to think about this for a second...)#k")
