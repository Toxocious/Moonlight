# Guon (2094002) | Inside the  Lord Pirate Party Quest

from net.swordie.ms.constants import GameConstants
from net.swordie.ms.constants import WzConstants

pqItems = [
4001117, # Old Metal Key
4001120, # Rookie Pirate Mark
4001121, # Rising Pirate Mark
4001122, # Veteran Pirate Mark
]

ROOKIE_PIRATE_MARK = 4001120 # Stage 0
RISING_PIRATE_MARK = 4001121 # Stage 1
VETERAN_PIRATE_MARK = 4001122 # Stage 2
count = 5

stage = int(sm.getQRValue(GameConstants.LORD_PIRATE_QUEST))


if sm.getFieldID() == 925100100: # Hidden Street: Through the Head of the Ship!
    item = 0
    if stage == 0:
        item = ROOKIE_PIRATE_MARK
    elif stage == 1:
        item = RISING_PIRATE_MARK
    elif stage == 2:
        item = VETERAN_PIRATE_MARK

    nextItem = 0
    if stage == 0:
        nextItem = RISING_PIRATE_MARK
    elif stage == 1:
        nextItem = VETERAN_PIRATE_MARK

    if sm.isPartyLeader():
        if stage == 3:
            sm.sendNext("Make your way through the portal on the right")

        else:
            if sm.hasItem(item, count):
                if stage == 2:
                    sm.sendNext("Great you may now continue to the next stage!")
                    sm.invokeForParty("showEffectToField", WzConstants.EFFECT_CLEAR)

                else:
                    sm.sendNext("Alright, next up I need "+ str(count) +" #v"+ str(nextItem) +"##b#t"+ str(nextItem) +"##k.")
                sm.consumeItem(item, count)
                sm.invokeForParty("setQRValue", GameConstants.LORD_PIRATE_QUEST, str(int(sm.getQRValue(GameConstants.LORD_PIRATE_QUEST)) + 1))

            else:
                sm.sendNext("Please bring me "+ str(count) +" #v"+ str(item) +"##b#t"+ str(item) +"##k.")
    else:
        sm.sendSayOkay("Please, have your party leader speak to me.")


elif sm.getFieldID() == 925100500: # Hidden Street: The Captain's Dignity
    if not sm.hasMobsInField():
        if not sm.isPartyLeader():
            sm.sendSayOkay("Please have your party leader speak to me.")
        else:
            sm.sendNext("You have done us a great favour, what ever can we do to repay you?")
        sm.warpInstanceIn(925100700, True)
        # For all party members
        for partyMembers in sm.getParty().getMembers():
            # Sets the Stage2 progress back to 0
            if partyMembers.getChr() is None:
                continue

            sm.setQRValue(partyMembers.getChr(), GameConstants.LORD_PIRATE_QUEST, "0")

            # Gives all party members Exp
            sm.giveExp(sm.getPQExp(partyMembers.getChr()))

            # Checks & deletes all items in the  array: pqItems
            for item in pqItems:
                if sm.hasItem(item):
                    quantity = sm.getQuantityOfItem(item)
                    sm.consumeItem(item, quantity)

    else:
        sm.sendSayOkay("Please get rid of the Captain!")

elif sm.getFieldID() == 925100700: #pq exist of completion
     sm.giveNX(200000)
     sm.giveItem(4310212, 2)
     sm.warpInstanceOut(910002000)
     sm.setQRValue(GameConstants.LORD_PIRATE_QUEST, "0")

else:
    response = sm.sendAskYesNo("Are you sure you want to leave?")
    if response:
        if not sm.getParty() is None:
            sm.warpInstanceOut(910002000)
            for partyMembers in sm.getParty().getMembers():
                sm.setQRValue(partyMembers.getChr(), GameConstants.LORD_PIRATE_QUEST, "0", True)
        else:
            sm.warpInstanceOut(910002000, 0)
            sm.setQRValue(GameConstants.LORD_PIRATE_QUEST, "0")
