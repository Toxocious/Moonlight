# The Curbrock in the Grass (5499)

SABITRAMA = 1061005 # NPC ID
CURBROCKS_BADGE = 1182054 # ITEM ID

sm.setSpeakerID(SABITRAMA)
sm.sendNext("You don't look well, my friend.")

sm.sendSay("The rumors are true. "
           "We must stop him before he eats anyone else.\r\n"
           "But you are still too weak. "
           "Return to me when you are stronger.\r\n\r\n"
           "I will give you #v1182054# #b#z1182054##k as a gift.")

if sm.canHold(CURBROCKS_BADGE):
    sm.giveItem(CURBROCKS_BADGE)
    sm.completeQuest(parentID)

else:
    sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")