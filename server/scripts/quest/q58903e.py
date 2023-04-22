# Regards, Takeda Shingen (Sengoku Era) Questline (Stage 2) | Near Momiji Hills 1 (811000001)
# Completes stage 2 of prequest
# Author: Tiger

TAKEDA = 9000427
ITEMID = 4034128 # Bitter Resurrection Herb

sm.setSpeakerID(TAKEDA)

sm.flipDialogue()
sm.sendNext("Do you have the #i" + str(ITEMID) + "##z" + str(ITEMID) + "#?")

sm.flipDialogue()
sm.sendSay("Not bad-looking #i" + str(ITEMID) + "##z" + str(ITEMID) + "#, I have to say.\r\nLet's mash it up and make that medicine.")

sm.flipDialogue()
sm.sendSay("Give me a minute to mix this stuff up. Takes me back to my bartending days.")

sm.completeQuest(parentID) # completes part 2 of Regards, Takeda Shigen quest
sm.consumeItem(ITEMID, 1) # remove the herb
