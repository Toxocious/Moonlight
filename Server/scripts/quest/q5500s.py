# The New Me (5500)

SABITRAMA = 1061005 # NPC ID
CURBROCKS_HIDEOUT_VER2 = 600050010 # MAP ID
CURBROCKS_ESCAPE_ROUTE_VER2 = 600050040 # MAP ID 2
CURBROCKS_ESCAPE_ROUTE_VER3 = 600050050 # MAP ID 3

sm.setSpeakerID(SABITRAMA)

if sm.getFieldID() == CURBROCKS_ESCAPE_ROUTE_VER2 or \
    sm.getFieldID() == CURBROCKS_ESCAPE_ROUTE_VER3:
    sm.sendSayOkay("Please leave before reaccepting this quest again.")

else:
    sm.sendNext("Oh my... I can feel overflowing energy from your body. "
                "Do you still remember the rumour about Curbrock?")

    sm.sendSay("Recently, many people have disappearing from the Broken city. "
               "The rumour about Curbrock's return is spreading throughout the city.")

    response = sm.sendAskYesNo("Then, are you ready to defeat #rCurbrock right away?#k")

    if response:
        sm.sendNext("Curbrock will only fall to your strongest attack. "
                    "Good luck!")

        chr.setPreviousFieldID(chr.getFieldID())
        sm.startQuest(parentID)
        sm.warpInstanceIn(CURBROCKS_HIDEOUT_VER2)