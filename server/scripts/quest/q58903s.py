# Regards, Takeda Shingen (Sengoku Era) Questline (Stage 2) | Near Momiji Hills 1 (811000001)
# Starts stage 2 of pre-quest
# Author: Tiger

TAKEDA = 9000427

ITEMID = 4034128 # Bitter Resurrection Herb
ITEMID_2 = 4009378 # Contaminated Goblin Detritus

MAPID_1 = 811000004 # Hidden Field of Momiji Hills 1
MAPID_2 = 811000005 # Hidden Field of Momiji Hills 2
MAPID_3 = 811000006 # Hidden Field of Momiji Hills 3

MOBID_1 = 9450026 # Contaminated Goblin 1
MOBID_2 = 9450027 # Contaminated Goblin 2

sm.setSpeakerID(TAKEDA)
sm.flipDialogue()

sm.sendNext("Looking at those spell scrolls, I was able to remember the instructions on that letter on how to lift the spell. Just needed to jump-start the of noggin")

response = sm.sendAskYesNo("Unfortunately for you, lifting the spell requires more materials.\r\nBetter get to it!")

if response:
    sm.flipDialogue()
    sm.sendNext("This #v" + str(ITEMID) + "##z" + str(ITEMID) + "# is just what we need.")

    sm.flipDialogue()
    sm.sendSay("So, go to #m" + str(MAPID_1) + "#, #m" + str(MAPID_2) + "#, and #m" + str(MAPID_3) + "#. Hunt #o" + str(MOBID_1) + "# and #o" + str(MOBID_2) +
        "# monsters to get these #i" + str(ITEMID_2) + "# #z" + str(ITEMID_2) + "# items. We need them to grow the #i" + str(ITEMID) + "# #z" + str(ITEMID) + "#.")

    sm.flipDialogue()
    sm.sendSay("If you run around #m" + str(MAPID_1) + "#, #m" + str(MAPID_2) + "#, and #m" + str(MAPID_3) + "#. You'll see herb sprouts. Just drop one #z" + str(ITEMID_2) + "# on them.")

    sm.flipDialogue()
    sm.sendSay("You want to grow #v" + str(ITEMID) + "##z" + str(ITEMID) + "# to Stage 4 and you need 10 #z" + str(ITEMID_2) + "# items for each stage")
    sm.startQuest(parentID)
