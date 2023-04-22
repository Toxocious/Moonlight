from net.swordie.ms.enums import InvType

alienCube = 5750000
alienCubePrice = 7000
shop = [
    [5750000, 7000], # Alien Cube for 7000 cash
    [2930000, 3000], # Socket Creator 3000 cash
    [5750001, 20000] # Nebulite Diffuser 20000 cash
]
newlist = []
itemID = []


menu = sm.menu("#i5750000# Alien Cube #r(7000 cash)#k#b" ,
   "#i2930000# Socket Creator #r(3000 cash)#k#b",
   "#i5750001# Nebulite Diffuser #r(20000 cash)#k#b",
    "Fuse Nebulite",
    "Fuse Nebulite (multiple)",
    "Use an Alien Cube",
    "Remove Nebulites from inventory",
    "Use a nebulite",
    "Remove Nebulite from equipment")


def buy(opt, items):
    name = items[opt][0]
    cost = items[opt][1]
    if sm.sendAskYesNo("Are you sure you want to buy #z " + str(name) + "# #i" + str(name) + "# for #r" + str(cost) + "#k cash?"):
            if chr.getUser().getMaplePoints() >= cost:
                if sm.canHold(name):
                    sm.giveItem(name)
                    chr.deductNx(cost)
                    sm.sendSayOkay("You have obtained #b#z" + str(name) + "##k for #r" + str(cost) + "#k nx cash.")
                else:
                    sm.sendNext("Please make sure you have enough space in your inventory")
            else:
                sm.sendNext("You don't have enough cash. You need #r" + str(cost) + "#k nx.")

def fuseNeb(opt, multi):
    if multi:
        sm.sendNext("TODO: Fuse Multiple Nebulites")
    else:
        sm.sendNext("TODO: Fuse Nebulite")

def useAlienCube(items):
    listitem = eval(sm.getNebsFromInv())
    listitem.sort()
    for x in range(len(listitem)):
        itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.INSTALL))
        newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
    if not newlist:
        sm.sendSayOkay("You do not posses any nebulites in your inventory.")
        sm.dispose()
    nebSlot = sm.sendNext(''.join(newlist))
    neb = chr.getInventoryByType(InvType.INSTALL).getItemBySlot(nebSlot)
    while sm.sendNext("#L0##fs12#"+ "#i" + str(neb.getItemId()) + "##t" + str(neb.getItemId()) + "#\r\n") > -1:
        if sm.hasItem(alienCube):
            if sm.getQuantityOfItem(alienCube) <= 1:
                while sm.sendAskText("You are almost out of cubes. Once your cubes are up - I will use your NX to cube.\r\ntype 'OK' to continue", "", 1, 5) != "OK":
                    pass
            nebSlot = sm.cubeNeb(nebSlot)
            neb = chr.getInventoryByType(InvType.INSTALL).getItemBySlot(nebSlot)
            sm.consumeItem(alienCube)

        else:
            if chr.getUser().getMaplePoints() >= alienCubePrice:
                nebSlot = sm.cubeNeb(nebSlot)
                neb = chr.getInventoryByType(InvType.INSTALL).getItemBySlot(nebSlot)
                chr.addNx(-alienCubePrice)
            else:
                sm.sendSayOkay("You do not have enough NX to cube.")
                break

def removeNebFromInventory(opt, items):
    sm.sendNext("TODO: Remove Nebulite from inventory")

def useNebulite(opt, items):
    newlist = []
    itemID = []
    listitem = eval(sm.getItemsEligibleForNeb())
    listitem.sort()
    for x in range(len(listitem)):
        itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP))
        newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
    if not newlist:
        sm.sendSayOkay("No equips to apply Nebulites to.")
        sm.dispose()
    equipSlot = sm.sendNext(''.join(newlist))
    newlist = []
    itemID = []
    listitem = eval(sm.getNebsFromInv())
    listitem.sort()
    for x in range(len(listitem)):
        itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.INSTALL))
        newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
    if not newlist:
        sm.sendSayOkay("You do not posses any Nebulites in your inventory.")
        sm.dispose()
    nebSlot = sm.sendNext(''.join(newlist))
    sm.fuseNebuliteIntoEquip(equipSlot, nebSlot)


def removeNebEquipment(opt, items):
    sm.sendNext("TODO: Remove Nebulite equipment")


selection = sm.sendNext("#b" + menu + "#k")

if 0 <= selection <= len(shop) - 1 :
    buy(selection, shop)
elif selection == 3:
    fuseNeb(selection, False)
elif selection == 4:
    fuseNeb(selection, True)
elif selection == 5:
    useAlienCube(shop)
elif selection == 6:
    removeNebFromInventory(selection, shop)
elif selection == 7:
    useNebulite(selection, shop)
elif selection == 8:
    removeNebEquipment(selection, shop)
