# Curbrock's Revenge (5501)

SABITRAMA = 1061005 # NPC ID
CURBROCKS_HIDEOUT_VER3 = 600050020 # MAP ID
CURBROCKS_ESCAPE_ROUTE_VER3 = 600050050 # MAP ID 3

sm.setSpeakerID(SABITRAMA)

if sm.getFieldID() == CURBROCKS_ESCAPE_ROUTE_VER3:
    sm.sendSayOkay("Please leave before reaccepting this quest again.")

else:
    sm.sendNext("The rumors are true. "
                "Curbrock has returned, and the people are worried.")

    sm.sendSay("However, he doesn't seem to be interested in anyone but you.")

    response = sm.sendAskYesNo("I can send you there now if you want. "
                               "Are you prepared?")

    if response:
        sm.sendNext("Since you last fought, Curbrock has learned a few new tricks.")

        sm.sendSay("His Seal spell will stop you from using your skills.")

        sm.sendSay("His Blind spell will stop you from seeing.")

        sm.sendSay("And his Stun spell will stop you from moving.")

        sm.sendSay("If you can avoid or counter his spells, you might yet beat him.")

        chr.setPreviousFieldID(chr.getFieldID())
        sm.startQuest(parentID)
        sm.warpInstanceIn(CURBROCKS_HIDEOUT_VER3)