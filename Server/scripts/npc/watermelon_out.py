import random
from net.swordie.ms.enums import InvType
from net.swordie.ms.loaders import ItemData
from net.swordie.ms.util import FileTime

rlsPrice = 1000
rlsId = 4000306
damageSkinPrice = 500
chairPrice = 100
pvacHourlyPrice = 15
pvacItemId = 4030003
secondaryPendantPrice = 2000
accSecondaryPendantPrice = 5000
nameChangePrice = 1000
errorMessage = "You do not have enough #bDonation Points#k or you do not have room in your inventory for this item."

items = { #[itemid, price, expiration time]
    0 : [2434039, 5000, 0],
    1 : [2434038, 5000, 0],
    3 : [5360042, 3000, 0],
    4 : [5211122, 3000, 0],
    2 : [1122303, 1000, 0],
    5 : [1113171, 1000, 0],
}

damageSkins = [2431966, 2432084, 2431967, 2432131, 2432153, 2432638, 2432659, 2432154, 2432637, 2432658, 2432207, 2432354, 2432355, 2432972, 2432465, 2432479, 2432526, 2432639, 2432660, 2432532, 2432592, 2432640, 2432661, 2432710, 2432836, 2432973, 2433063, 2433178, 2433456, 2435960, 2433715, 2433804, 5680343, 2433913, 2433980, 2433981, 2436229, 2434248, 2433362, 2434274, 2434289, 2434390, 2434391, 5680395, 2434528, 2434529, 2434530, 2433571, 2434574, 2433828, 2432804, 2434654, 2435326, 2432749, 2434710, 2433777, 2434824, 2434662, 2434664, 2434868, 2436041, 2436042, 2435046, 2435047, 2435836, 2435141, 2435179, 2435162, 2435157, 2435835, 2435159, 2436044, 2434663, 2435182, 2435850, 2435184, 2435222, 2435293, 2435313, 2435331, 2435332, 2435333, 2435334, 2435316, 2435408, 2435427, 2435428, 2435429, 2435456, 2435493, 2435331, 2435334, 2435959, 2435958, 2435431, 2435430, 2435432, 2435433, 2434601, 2435521, 2435523, 2435524, 2435538, 2435832, 2435833, 2435839, 2435840, 2435841, 2435849, 2435972, 2436023, 2436024, 2436026, 2436027, 2436028, 2436029, 2436045]

if sm.sendNext:
    selection = sm.sendNext("Hello my name is Agent W, This shop is where you can spend your #bDonation Points#k or exchange them for #i4000306# Red Packet(s). "
                "\r\n\r\n#eYou have #r" + str(sm.getDonationPoints()) + " #bDonation points.\r\n#n#b"
                "#L9##r#eI'd like to redeem my Donation key.#b#n\r\n"
                "#L0#I'd like to exchange my Donation Points for a Red Packet(s).\r\n"
                "#L1#I'd like to exchange my Red Packet(s) for Donation Points.\r\n"
                "#L2#I'd like to purchase items.\r\n"
                "#L3#I'd like to purchase a random damage skin.\r\n"
                "#L4#I'd like to purchase a random chair.\r\n"
                "#L5#I'd like to purchase a Pet Vac.\r\n"
                "#L6#I'd like to purchase a permanent pendant slot (Character).\r\n"
                "#L8#I'd like to purchase a permanent pendant slot (Account).\r\n"
                "#L7#I'd like to purchase a name change.\r\n"
                "#L10#I'd like to purchase a donor slot.\r\n")
    if selection == 0:
        amount = sm.sendAskNumber("A Red Packet costs #r(1000)#b Donation Points.\r\n"
                    "You currently have #e#r" + str(sm.getDonationPoints()) + " #bDonation points#n\r\n"
                    "How many would you like exchange1?", 1, 1, 100)
        price = amount * rlsPrice
        if sm.getDonationPoints() >= price and chr.canHold(rlsId, amount):
            sm.deductDonationPoints(price)
            sm.giveItem(rlsId, amount)
        else:
            sm.sendNext("You do not have enough #bDonation points#k or you do not have room in your inventory for this item.")

    elif selection == 1:
        amount = sm.sendAskNumber("A Red Packet costs #r(1000)#b Donation Points. \r\n"
                    "You currently have #r#e" + str(sm.getQuantityOfItem(rlsId)) + " #bRed Packets \r\n"
                    "#nHow many would you like exchange2?", 1, 1, 100)
        if sm.getQuantityOfItem(rlsId) >= amount:
            sm.addDonationPoints(rlsPrice * amount)
            sm.consumeItem(rlsId, amount);
        else:
            sm.sendNext("You do not have enough Red Packets for this exchange.")

    elif selection == 2:
        outPut = "You currently have #e#r" + str(sm.getDonationPoints()) + " #bDonation points#n\r\n"
        "Which item would you like to purchase?"
        for x in range (len(items)):
            outPut += "#b" + su.addSelectItem(x) + su.getItemImg(items[x][0]) + "   " + su.getItemName(items[x][0]) + " for #r" + str(items[x][1]) + " #bDonation Points" + "\r\n"
        selection = sm.sendNext(outPut)
        itemId = items[selection][0]
        price = items[selection][1]
        answer = sm.sendAskYesNo("Would you like to purchase " + " #b" + su.getItemName(itemId) + " #kfor #r" + str(price) + " #bDonation Points#k?")
        if answer and sm.canHold(itemId) and sm.getDonationPoints() >= price:
            sm.giveItem(itemId, 1, items[selection][2])
            sm.deductDonationPoints(price)
        else:
            sm.sendNext(errorMessage)

    elif selection == 3:
        answer = sm.sendAskYesNo("Are you sure you want to exchange #r" + str(damageSkinPrice) + " #bDonation Points#k for a random damage skin?\r\n\r\n#ePlease note that this will give you a random damage skin from all existing damage skins in the current game version.\r\n\r\nFurthermore use damage skins wisely as they are not account wide they are only character bound.")
        if answer and sm.canHold(damageSkins[0]) and sm.getDonationPoints() >= damageSkinPrice:
            sm.giveItem(random.choice(damageSkins))
            sm.deductDonationPoints(damageSkinPrice)
        else:
            sm.sendNext(errorMessage)

    elif selection == 4:
        answer = sm.sendAskYesNo("Are you sure you want to exchange #r" + str(chairPrice) + " #bDonation Points#k for a random chair?\r\n\r\n#ePlease note that this will give you a random chair from all existing chairs in the current game version.")
        if answer and sm.canHold(3015609) and sm.getDonationPoints() >= chairPrice: #id is a random chair
            chairId = random.randint(3010002, 3015609)
            item = ItemData.getItemDeepCopy(chairId)
            while item is None: #reroll item id until itemid exist since chairs ids are not consistent
                chairId = random.randint(3010002, 3010695)
                item = ItemData.getItemDeepCopy(chairId)
            sm.giveItem(chairId)
            sm.deductDonationPoints(chairPrice)
        else:
            sm.sendNext(errorMessage)

    elif selection == 5:
        hours = sm.sendAskNumber("For every #r" + str(pvacHourlyPrice) + "#b Donation Points#k your #rPet Vac#k will gain #r1 #bHour.#k\r\n"
                                  "You currently have #e#r" + str(sm.getDonationPoints()) + " #bDonation points#n\r\n"
                                                                                              "How many Hour(s) would you like your #rPet Vac#k to be?", 1, 1, 1000)
        totalPrice = pvacHourlyPrice * hours
        answer = sm.sendAskYesNo("Are you sure you want your #rPet Vac#k to last #r" + str(hours) + "#b Hour(s) #kfor #r" + str(totalPrice) + "#bDonation Points#k?")
        if answer and sm.canHold(pvacItemId) and sm.getDonationPoints() >= totalPrice: #id is a random chair
            sm.giveItem(pvacItemId, 1, hours)
            sm.deductDonationPoints(totalPrice)
        else:
            sm.sendNext(errorMessage)

    elif selection == 6:
        answer = sm.sendAskYesNo("Are you sure you want to purchase a permanent secondary pendant slot for #r" + str(secondaryPendantPrice) + "#bDonation Points#k.")
        if answer and sm.getDonationPoints() >= secondaryPendantPrice:
            if sm.setSecondaryPendantDate(FileTime.fromType(FileTime.Type.MAX_TIME)):
                sm.deductDonationPoints(secondaryPendantPrice)
                sm.sendSayOkay("Please re-log for your secondary pendant slot to take effect.")
            else:
                sm.sendSayOkay("You already own a secondary pendant slot")
        else:
            sm.sendNext("You do not have enough #bDonation Points#k.")

    elif selection == 7:
        name = sm.sendAskText("#b#eWhat name would you like to change your character to?", "", 4, 13)
        if sm.getDonationPoints() >= nameChangePrice:
            if not sm.setName(name):
                sm.sendSayOkay("You cannot use this name or this name is already in use.")
                sm.dispose()
            else:
                sm.deductDonationPoints(nameChangePrice)
            sm.sendSayOkay("Please re-log for your new name to take take effect.")
        else:
            sm.sendSayOkay("You do not have enough #bDonation Points#k.")

    elif selection == 8:
        answer = sm.sendAskYesNo("Are you sure you want to purchase a permanent secondary pendant slot for #r" + str(
            accSecondaryPendantPrice) + " #bDonation Points#k.")
        if answer and sm.getDonationPoints() >= accSecondaryPendantPrice:
            if sm.setAccSecondaryPendantDate(FileTime.fromType(FileTime.Type.MAX_TIME)):
                sm.deductDonationPoints(accSecondaryPendantPrice)
                sm.sendSayOkay("Please re-log for your secondary pendant slot to take effect.")
            else:
                sm.sendSayOkay("You already own a secondary pendant slot")
        else:
            sm.sendNext("You do not have enough #bDonation Points#k.")
    
    elif selection == 9:
        dpkey = sm.sendAskText("Here you can redeem your key, if you donated through our site.\r\nPlease enter the key you received on e-mail:", "", 4, 81)
        if sm.isDonationKeyExists(dpkey):
            if sm.commitDonationKey(dpkey):
                sm.sendSayOkay("Thank you! The key has been validated and redeemed.")
            else:
                sm.sendSayOkay("An error occured while attempting to redeem your donation points. Please contact an Administrator..")
        else:
            sm.sendSayOkay("This key does not exists.")

    elif selection == 10:
        newlist = []
        itemID = []
        listitem = eval(sm.getItemsByInventory(InvType.EQUIP))
        listitem.sort()
        for x in range(len(listitem)):
            itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP))
            newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
        if not newlist:
            sm.sendSayOkay("I was unable to find any equips in your equip inventory.")
            sm.dispose()
        selection = sm.sendNext(''.join(newlist))
        if sm.sendAskYesNo("This will cost " + str(sm.getDonorSlotCost(selection)) + " donor points. Are you sure?"):
            result = sm.doDonorSlot(selection) # DP Reduction and everything is handled in this function.
            if result == -3:
                sm.sendSayOkay("Your equip needs to not have any upgrade slots remaining.")
            elif result == -2:
                sm.sendSayOkay("Sorry. You do not have enough Donation Points.")
            elif result == -1:
                sm.sendSayOkay("The selected equip has already maxed out his available donor slots.")
            else:
                sm.sendSayOkay("Success! I have given the equip another slot to use. Enjoy!")