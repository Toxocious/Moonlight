from net.swordie.ms.enums import InvType

eeScroll=0
listitem = []
itemID = []
newlist = []
selection = sm.sendNext("#e<Equip Enchancement NPC>#n \r\n \r\n Hey #h #. I am in charge of Equip Enchancing. Please choose of the options below: \r\n \r\n \r\n#b#L1#Use Equip Enchancement.#l \r\n#L0##bUse Sccisors of Karma#l \r\n#L2#Restore Equipment Trace.#l")
if selection == 0:
    selection = sm.sendNext("Please choose what you would like to do:\r\n\r\n#L0##bApply Scissor of Karama for certain item.#l\r\n#L1#Apply Scissors of Karma for all available equips(4,000 NX per equip).#l");
    if selection == 0:
        newlist = []
        listitem = eval(sm.getScissorEquips())
        listitem.sort()
        for x in range(len(listitem)):
            itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP))
            newlist.append('#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
        if not newlist:
            sm.sendSayOkay("No equips to scissor")
            sm.dispose()
        selection = sm.sendNext(''.join(newlist))
        itemToScissor = str(sm.getItemIDByBagIndex(selection, InvType.EQUIP))
        if sm.sendAskAccept("You have selected #v"+itemToScissor+"# #e #t"+itemToScissor+"##n. This will cost 4,000 NX.#"):
            sm.applyScissor(selection)
    elif selection == 1:
        sm.applyScissorToAll()
elif selection == 1:
    newlist = []
    listitem = eval(sm.getAllEEScrolls())
    listitem.sort()
    for x in range(len(listitem)):
        itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.CONSUME))
        newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
    if not newlist:
        sm.sendSayOkay("You do not have any equip enhancement scrolls.")
        sm.dispose()
    eeScroll = sm.sendNext(''.join(newlist))
    newlist = []
    itemID = []
    listitem = eval(sm.getEquipsForEE())
    listitem.sort()
    for x in range(len(listitem)):
        itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP))
        newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
    if not newlist:
        sm.sendSayOkay("No equips to enhance")
        sm.dispose()
    selection = sm.sendNext(''.join(newlist))
    if sm.isEqpEligibleForAddedChance(selection) > 0:
        selectionNX = sm.sendNext("Added chance.\r\nThis will multiply your chances of succeeding by #1.5x#n.\r\n#L0#Do not add extra chance#l\r\n#L1#Increase chance (Cost: #e"+str(sm.isEqpEligibleForAddedChance(selection))+"#n NX)#l")
        if selectionNX == 0:
            sm.EnchantItem(selection, eeScroll, selection, 0)
        elif selectionNX == 1:
            sm.EnchantItem(selection, eeScroll, selection, 1)
    else:
        sm.EnchantItem(selection, eeScroll, selection, 0)

elif selection == 2:
    newlist = []
    listitem = eval(sm.getEquipmentTracesByIndex())
    for x in range(len(listitem)):  
        item = sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP)
        itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP))
        newlist.append('#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
    if not newlist:
        sm.sendSayOkay("No equips to restore")
        sm.dispose()
    selection = sm.sendNext(''.join(newlist))
    itemToRestore = str(sm.getItemIDByBagIndex(selection, InvType.EQUIP))
    if sm.sendAskAccept("You have selected #v"+itemToRestore+"# #e #t"+itemToRestore+"##n. This will cost TBD NX.#"):
        sm.restoreEquipTrace(selection)