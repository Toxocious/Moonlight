# Regards, Takeda Shingen (Sengoku Era) Questline (Stage 3) | Near Momiji Hills 1 (811000001)
# Starts part 3 of prequest
# Author: Tiger

TAKEDA = 9000427
NPCID = 9130006 # Mouri Motonari
ITEMID = 4034129 # Mega Bitter Medicine
MAPID = 807000000 # Momijigaoka

sm.setSpeakerID(TAKEDA)

sm.flipDialogue()
sm.sendNext("I've made the medicine. It stinks like burning hair, so take it to #p" + str(NPCID) + "# ASAP.")

sm.flipDialogue()
sm.sendSay("No way, this stuff smells viel. You do it.")

sm.flipDialogue()
sm.sendSay("I can't go right now anyway, so just drop it off. Also ask her how and why she traveled all the way here.")

response = sm.sendAskYesNo("Please bring this #i" + str(ITEMID) + "##z " + str(ITEMID) + "# to #p" + str(NPCID) + "# in #m" + str(MAPID) + "#.")

if response:
    sm.giveItem(ITEMID, 1) # give mega bitter medicine
    sm.startQuest(parentID) # start quest #3
