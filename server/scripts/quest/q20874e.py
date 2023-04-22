# NW - End of Knight-in-Training - Complete

sm.setSpeakerID(1101006)
response = sm.sendAskYesNo("You've defeated the 30 Tigurus! I like how hard you work! Are you ready work even harder by "
                           "accepting the responsibilities of an official knight?")

if response:
    sm.completeQuest(parentID)
    sm.jobAdvance(1410)
    sm.giveItem(1142067)
    sm.sendSayOkay("You have been officially promoted to a Cygnus Knight! I've also given you some AP and SP to work with.")
else:
    sm.sendSayOkay("Okay, come back later when you've changed your mind.")
