#Secondary transfer NPC - 9010047 - Script name = 9010047.py
# On selection 1 and 2 - NX deduction is handled on the SecondaryFunction.
from net.swordie.ms.client.character.items import BodyPart

STONE_SHIELD = 1092068
PRICE = 100000

selection = sm.sendNext("Hello my name is Gale, I am in charge of transferring potentials between your secondaries.\r\n"
                        "\r\n#eYou have#r " + str(sm.getNX()) + " #bNX#n.\r\n\r\n"
                        "#L0#Purchase a secondary#l \r\n"                                                                
                        "#L1#Buy a Stone Shield #e#r(100,000) NX#b#n#l \r\n"
                        "#L2#Transfer Equipped Secondary potential to the first stone shield in your inventory #e#r(100,000) NX#b#n#l \r\n"
                        "#L3#Transfer the first Stone Shield's potential in your inventory to your current equipped secondary #e#r(100,000) NX#b#n #r\r\n#e(This will overwrite your equipped secondaries potential)#n.#l"
                        "#L4##d#eUnequip your current secondary weapon.#l")

if selection == 0:
    sm.invokeAfterDelay(1, "openShop", 9072100)
    sm.dispose()
elif selection == 1:
    if not sm.canHold(1092068):
        sm.sendSayOkay("You do not have space in your equip inventory.")
        sm.dispose()
    if sm.getNX() >= PRICE:
        sm.deductNX(PRICE)
        sm.giveItem(1092068)
        sm.sendSayOkay("Thank you for your purchase!")
    else:
        sm.sendSayOkay("You do not have enough #rNX#k.")

elif selection == 2:
    result = sm.SecondaryFunction(1)
    if result == -1:
        sm.sendSayOkay("Either there is no stone shield in your inventory or the stone shield lacks potential.")
    elif result == -2:
        sm.sendSayOkay("You do not have a secondary currently equipped.")
    elif result == -3:
        sm.sendSayOkay("You do not have enough #rNX#k to perform this action.")
    elif result == -4:
        sm.sendSayOkay("Your first Stone Shield in your inventory already has potential. Please put a Stone Shield that does not have potential in first slot of your inventory.")

    else:
        sm.sendSayOkay("I have transferred your secondaries potential to the first Stone Shield in your inventory.")

elif selection == 3:
    result = sm.SecondaryFunction(0)
    if result == -1:
        sm.sendSayOkay("Either there is no stone shield in your inventory or the stone shield lacks a potential.")
    elif result == -2:
        sm.sendSayOkay("You do not have a secondary currently equipped.")
    elif result == -3:
        sm.sendSayOkay("You do not have enough #rNX#k to perform this action.")
    else:
        sm.sendSayOkay("I have transferred your Stone Shield's potential to your equipped secondary.")

elif selection == 4:
    currentSecondary = chr.getEquippedItemByBodyPart(BodyPart.Shield)
    if currentSecondary is not None and chr.canHold(currentSecondary.getItemId()):
        sm.unequip(currentSecondary)
    else:
        sm.sendNext("You are not equipped with a secondary weapon or cannot hold it in your equip inventory.")