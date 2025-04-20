# Regards, Takeda Shingen (Sengoku Era) Questline (Stage 3) | Momijigaoka (807000000)
# Completes part 3 of prequest
# Author: Tiger

MOURI = 9130006 # Mouri Motonari
ITEMID = 4034129 # Mega Bitter Medicine

sm.setSpeakerID(MOURI)

sm.flipDialogue()
sm.sendNext("Hm? Shingen didn't come here himself? How disappointing.")

sm.flipDialogue()
sm.sendSay("Regardless, I pass on the #i" + str(ITEMID) + "##z" + str(ITEMID) + "# to treat the warrior.")

sm.flipDialogue()
sm.sendSay("I'm sure she will awaken soon. Go to the Master Room to meet her")

sm.flipDialogue()
sm.sendSay("The Master Room is to the left.")

sm.completeQuest(parentID)
sm.consumeItem(ITEMID) # remove mega Bitter Medicine from user
