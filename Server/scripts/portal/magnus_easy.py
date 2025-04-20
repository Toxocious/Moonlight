from net.swordie.ms.constants import BossConstants

EASY_MAGNUS_QUEST = 31851

sm.setSpeakerID(3001000) # Edea

response = sm.sendAskYesNo("Would you like to fight easy magnus?")
if response:
    if not sm.hasQuestCompleted(EASY_MAGNUS_QUEST):
        sm.sendSayOkay("Please talk to Piston to know more about the Magnus simulator.")
    elif sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Magnus.")
    elif sm.checkParty():
        sm.setDeathCount(BossConstants.MAGNUS_DEATHCOUNT)
        sm.warpInstanceIn(401060300, True)
