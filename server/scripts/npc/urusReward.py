from net.swordie.ms.enums import InvType
from net.swordie.ms.scripts import ScriptUtil as su;

ursusEssenceId = 4009349
ursusHatId = 1004597
ursusSandalId = 1073090
ursusGloveId = 1082666

if not sm.hasItem(ursusEssenceId):
    sm.sendNext("You do not have a #b" + su.getItemImg(ursusEssenceId) + " " + su.getItemName(ursusEssenceId) + "#k")
    sm.dispose()

if not sm.canHold(1004598):
    sm.sendNext("Please make sure you have room in your inventory first.")
    sm.dispose()

text = "The names #bJawad#k, what can I do for you?#b\r\n#L0# I want to upgrade my Ursus equip. #l\r\n"
text += "#L1# I want to get a Ursus equip. #l"

selection = sm.sendNext(text)

if selection == 0:

    itemsEligibleForUpgrade = eval(sm.getItemsEligibleForUrsusUpgrade())
    itemsEligibleForUpgrade.sort()
    itemsIDs = []

    for x in range(len(itemsEligibleForUpgrade)):
        itemsIDs.append(sm.getItemIDByBagIndex(itemsEligibleForUpgrade[x], InvType.EQUIP))

    if not itemsIDs:
        sm.sendSayOkay("No equips to upgrade.")
        sm.dispose()

    outPut = "Choose the equip you wish to upgrade:\r\n"

    for i in range(len(itemsIDs)):
        outPut += su.addSelectItem(itemsEligibleForUpgrade[i]) + " " + su.getItemImg(itemsIDs[i]) + "\r\n"

    itemSelection = sm.sendNext(outPut)
    sm.upgradeUrsusItem(itemSelection)

elif selection == 1:

    text = "Which one would you like?\r\n#b#L0# I want a Ursus Hat. #l\r\n"
    text += "#L1# I want a Ursus Glove. #l\r\n"
    text += "#L2# I want a Ursus Slipper. #l"

    selection2 = sm.sendNext(text)

    if selection2 == 0:
        sm.giveItem(ursusHatId)

    elif selection2 == 1:
        sm.giveItem(ursusGloveId)

    elif selection2 == 2:
        sm.giveItem(ursusSandalId)

sm.consumeItem(ursusEssenceId)
