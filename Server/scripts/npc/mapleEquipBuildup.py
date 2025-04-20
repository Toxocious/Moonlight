from net.swordie.ms.enums import InvType
from net.swordie.ms.scripts import ScriptUtil as su;

tinkerersChestId = 4033667
tinkerersBeltsId = 1132211
tinkerersShouldersId = 1152120

if not sm.hasItem(tinkerersChestId):
    sm.sendNext("You do not have a #b" + su.getItemImg(tinkerersChestId) + " " + su.getItemName(tinkerersChestId) + "#k")
    sm.dispose()

if not sm.canHold(1132211):
    sm.sendNext("Please make sure you have room in your inventory first.")
    sm.dispose()

text = "Hello my name is Yulia, I just love these Tinkerer's so much but I have too many. If you could bring me some boxes to store them in I would be happy to trade some with you.#b\r\n#L0# I want to upgrade my Tinkerer equip. #l\r\n"
text += "#L1# I want to get a Tinkerer equip. #l"

selection = sm.sendNext(text)

if selection == 0:

    itemsEligibleForUpgrade = eval(sm.getItemsEligibleForTinkerersUpgrade())
    itemsEligibleForUpgrade.sort()
    itemsIDs = []

    for x in range(len(itemsEligibleForUpgrade)):
        itemsIDs.append(sm.getItemIDByBagIndex(itemsEligibleForUpgrade[x], InvType.EQUIP))

    outPut = "Choose the equip you wish to upgrade:\r\n"

    for i in range(len(itemsIDs)):
        outPut += su.addSelectItem(itemsEligibleForUpgrade[i]) + " " + su.getItemImg(itemsIDs[i]) + "\r\n"

    itemSelection = sm.sendNext(outPut)
    sm.upgradeTinkerersItem(itemSelection)

elif selection == 1:

    text = "Which one would you like?\r\n#b#L0# I want a Tinkerer Belt. #l\r\n"
    text += "#L1# I want a Tinkerer Shoulder. #l"

    selection2 = sm.sendNext(text)

    if selection2 == 0:
        sm.giveItem(tinkerersBeltsId)

    elif selection2 == 1:
        sm.giveItem(tinkerersShouldersId)

sm.consumeItem(tinkerersChestId)
