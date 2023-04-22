# The Curbrock in the Grass (5499)

SABITRAMA = 1061005 # NPC ID
CURBROCKS_HIDEOUT = 600050000 # MAP ID
CURBROCKS_ESCAPE_ROUTE = 600050030 # MAP ID

sm.setSpeakerID(SABITRAMA)

if sm.getFieldID() == CURBROCKS_HIDEOUT or \
    sm.getFieldID() == CURBROCKS_ESCAPE_ROUTE:
    sm.sendSayOkay("Please leave before reaccepting this quest again.")

else:
    sm.sendNext("Have you come to hear the story of Curbrock?")

    sm.sendSay("Come close, then, and I will tell you my tale.")

    sm.sendSay("Long ago, there was a snake that tried to become a man by eating helpless human beings.")

    sm.sendSay("The snake pretended to be like us, even taking on a human name--Curbrock! "
               "However, when the rumors of his evil spread, he vanished.")

    sm.sendSay("But I've heard rumors recently. "
               "People have been vanishing from SleepyWood. "
               "They say that Curbrock has returned.")

    response = sm.sendAskYesNo("Would you go there for yourself and see if these rumors are true? "
                    "I can send you there now if you want.\r\n\r\n"
                    "#b(You'll be teleported to the Curbrock's Hideout if you accept.#k "
                    "#bForfeit and reaccept the quest to start again.)#k")

    if response:
        sm.sendNext("Remember, Curbrock is deadly. "
                    "You must run away if you meet him!")

        chr.setPreviousFieldID(chr.getFieldID())
        sm.startQuest(parentID)
        sm.warpInstanceIn(CURBROCKS_HIDEOUT)