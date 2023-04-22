from net.swordie.ms.enums import InvType

selection = sm.sendNext("#fs14##bGame Manager:#n\r\n#fs12#\r\n#kHello There! I am the game manager. Below you can find game settings that you can adjust to make your gameplay as best as possible for your needs.\r\n#k#L0#- Ignore an Item#l\r\n#L1#- Get mob drops by name#l\r\n#L2#- Check drops from mobs on current map#l\r\n#L3#- Search what monsters drop an item#l\r\n#L4#- Check Map Ownership#l\r\n#L6#- Disable Remote Skills#l"
                        + "\r\n#L7# Select damage skins.#l" + "\r\n#L5#- Sell items#l")
temp = ""
if selection == 0:
    ignoredDrops = set(chr.getIgnoredDrops())
    for i in ignoredDrops:
        temp += "#L"+str(i)+"##z"+str(i)+"##l\r\n"
    ignoredDropsShow = ''.join(temp)
    selection = sm.sendNext("Your ignored items:\r\n"+ignoredDropsShow+ "\r\n\r\nClick on an item to remove it from the ignore list.\r\n#L90#Add an item to ignore.#l")

    if selection != 90:
        if chr.existsInIgnoredDrops(selection):
            chr.removeIgnoredDrop(selection)
            chr.chatMessage("Removed from ignore list.")
        
    if selection == 90:
        toSearch = sm.sendAskText("Enter an item name or part of it:", "", 3, 100)
        searchResults = sm.searchItems(toSearch)
        if not searchResults:
            sm.sendSayOkay("No items were found.")
            sm.dispose()
        if len(searchResults) > 150:
            sm.sendNext("Too many results. Please be more specific in your search.")
            sm.dispose()
        somestr=""
        for key, value in searchResults.items():
            somestr += "#L"+str(key)+"##z"+str(key)+"##l\r\n"
        
        selectedItem = sm.sendNext(somestr)
        if chr.existsInIgnoredDrops(selectedItem) == False:
            chr.addIgnoredDrop(selectedItem)
            chr.chatMessage("Added an item to ignore list")
        else:
            chr.chatMessage("You already have this item in your ignore list.")
            sm.dispose()
            
elif selection == 1:
    toSearch = sm.sendAskText("Enter a monster name or part of it:", "", 3, 50)
    searchResults = sm.searchMobs(toSearch)
    if not searchResults:
        sm.sendSayOkay("No monsters were found.")
        sm.dispose()
    if len(searchResults) > 150:
        sm.sendNext("Too many results. Please be more specific in your search.")
        sm.dispose()
    somestr=""
    for key, value in searchResults.items():
        somestr += "#L"+str(key)+"##eID:#n "+str(key)+" #eName:#n "+value+"#l\r\n"
    mobSelected = sm.sendNext(somestr)
    sm.sendSayOkay(sm.getDropPercentageByMobForNPC(mobSelected))
    
    
elif selection == 2:
    searchResults = sm.searchMobsByField()
    if not searchResults:
        sm.sendSayOkay("No monsters were found.")
        sm.dispose()
    if len(searchResults) > 150:
        sm.sendNext("Too many results.")
        sm.dispose()
    somestr=""
    for key, value in searchResults.items():
        somestr += "#L"+str(key)+"##eID:#n "+str(key)+" #eName:#n "+value+"#l\r\n"
    mobSelected = sm.sendNext(somestr)
    sm.sendSayOkay(sm.getDropPercentageByMobForNPC(mobSelected))
    
elif selection == 3:
    toSearch = sm.sendAskText("Enter an item name or part of it:", "", 3, 100)
    searchResults = sm.searchItems(toSearch)
    if not searchResults:
        sm.sendSayOkay("No items were found.")
        sm.dispose()
    if len(searchResults) > 150:
        sm.sendNext("Too many results. Please be more specific in your search.")
        sm.dispose()
    somestr=""
    for key, value in searchResults.items():
        somestr += "#L"+str(key)+"##eID:#n "+str(key)+" #eName:#n "+value+"#l\r\n"
    itemSelected = sm.sendNext(somestr)
    searchResults = sm.searchDrop(itemSelected)
    if not searchResults:
        sm.sendSayOkay("No monsters were found dropping this item.")
        sm.dispose()
    if len(searchResults) > 150:
        sm.sendNext("Too many monsters are dropping this item. Are you searching for a global-drop item?")
        sm.dispose()
    somestr=""
    for key, value in searchResults.items():
        somestr += "#L"+str(key)+"##eID:#n "+str(key)+" #eName:#n "+value+"#l\r\n"
    mobSelected = sm.sendNext(somestr)
    sm.sendSayOkay(sm.getDropPercentageByMobForNPC(mobSelected))

elif selection == 4:
    sm.sendSayOkay("The current map owner is #e"+chr.getField().getOwner().getName()+"#n."
                                                                                     "\r\n\r\nOther AFK Players:" + sm.getCharsNoOwner())

elif selection == 5:
    selection = sm.sendNext("Sell:\r\n#L1#Equips#l\r\n#L2#Use#l\r\n#L4#Etc#l\r\n#L3#Setup#l\r\n#L5#Cash#l\r\n")
    inventory = InvType.getInvTypeByVal(selection)
    outPut = ""
    itemsBagIndexes = eval(sm.getItemsByInventory(inventory))
    itemsBagIndexes.sort()
    itemsIds = []
    for x in range(len(itemsBagIndexes)):
        itemsIds.append(sm.getItemIDByBagIndex(itemsBagIndexes[x], inventory))
    for x in range(len(itemsIds)):
        outPut += "#L" + str(x) + "##i" + str(itemsIds[x]) + "##l\r\n"
    choice = sm.sendNext(outPut)
    startIndex = itemsBagIndexes[choice]
    outPut = ""
    j = choice
    while j < (len(itemsIds)):
        outPut += "#L" + str(itemsBagIndexes[j]) + "##i" + str(itemsIds[j]) + "##l\r\n"
        j += 1
    endIndex = sm.sendNext(outPut)
    sm.sellMass(startIndex, endIndex, inventory)

elif selection == 6:
    if chr.isRemoteEffects():
        chr.setRemoteEffects(False)
    else:
        chr.setRemoteEffects(True)
    chr.chatMessage("Remote Skills toggled " + str(chr.isRemoteEffects()))

elif selection == 7:
    damageSkins = chr.getAccount().getDamageSkinsItemIds()
    damageSkins.add(2431965)
    outPut = ""
    for x in range(len(damageSkins)):
        outPut += "#L" + str(x) + "##i" + str(damageSkins[x]) + "##l\r\n"
    choice = sm.sendNext(outPut)
    chr.setDamageSkinAndBroadCast(damageSkins[choice])