# Regards, Takeda Shingen Questline | Near Momiji Hills 1 (811000001)
# Completes Quest 58902

TAKEDA = 9000427 # Takeda Shingen
ITEMID = 4034126 # 100 Spells for the Serious Soldier

if "3" in sm.getQRValue(58901): # Regards, Takeda Shingen
    sm.setSpeakerID(TAKEDA)

    sm.flipDialogue()
    sm.sendNext("Not bad, not bad. Thanks.")

    sm.flipDialogue()
    sm.sendSay("Let me take a look and I'll surely find a way to lift the spell. Come back later.")

    itemQty = sm.getQuantityOfItem(ITEMID) # TODO: This for some reason doesn't remove all quantites of the item.

    sm.consumeItem(ITEMID, itemQty)
    sm.completeQuest(parentID) # Regards, Takeda Shingen
