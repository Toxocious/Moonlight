# WA - End of Knight-in-Training - Start
sm.setSpeakerID(1101002)
if sm.sendAskYesNo("So you killed all the monsters? Are you ready to advance?"):
    if sm.canHold(1142067):
        sm.completeQuest(parentID)
        sm.jobAdvance(1110)
        sm.giveItem(1142067)
    else:
        sm.sendSay("Please make room in your Equip inventory.")
else:
    sm.sendSay("Please speak to me after You've defeated all monsters required.")
