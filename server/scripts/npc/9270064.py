from net.swordie.ms.enums import InvType

potList = ["STR %", "DEX %", "INT %", "LUK %", "All Stat %", "STR", "DEX", "INT", "LUK", "ATT", "Magic ATT", "All Stat","Max MP","Max HP", "HP %", "MP %", "Boss Damage", "IED", "ATT %", "Magic ATT %", "Damage %", "STR Per 10 levels", "DEX Per 10 levels", "INT Per 10 levels", "LUK Per 10 levels", "Min Crit", "Max Crit", "#rProceed with current list"]
cubeList = [[2710000, "Occult Cube", 700], [2711005, "Master Craftsman's Cube", 2000], [5062009, "Red Cube", 11000], [5062000, "Miracle Cube", 15000], [5062005, "Enlightning Miracle Cube", 20000], [5062006, "Platinum Miracle Cube", 30000], [5062500, "Bonus Potential Cube", 30000]]
playerPotList = []
eeScroll=0
tempList = []
listitem = []
itemID = []
newlist = []
bagIndex = 0
bPotCube = 5062500
requirementsArr = [{}, {}, {}, {}, {}]
circulatorsList = [[2702000, "Ability Circulator", 250], [5062800, "Miracle Circulator", 4000]]

action = sm.sendNext("Hello there I can help you cube your items or inner potential.\r\n#b#L0# I'd like to cube my equips.#l\r\n#L1# I'd like to reset my inner ability.#l")
if action == 0:
    def checkWhatIsIt(str):
        if str.count("%") > 0:
            return "%"
        else:
            return "+"
    def FilterPots(givenPots, toFilterIn):
        toFilterIn = toFilterIn.split("\r\n")
        for x in range(len(givenPots)):
            print "\n".join(s for s in givenPots if toFilterIn[x] in s)
    for x in range(len(cubeList)):
        newlist.append('#L'+str(x)+'##v'+str(cubeList[x][0])+'#'+'#t'+str(cubeList[x][0])+'##l\r\n')
    selection = sm.sendNext("Hey #h #. I am in charge of Auto Cubing. Please choose of the cubes below:\r\n\r\n\r\n"+''.join(newlist))
    selectedCube = selection
    newlist = []
    if cubeList[selectedCube][0] == bPotCube:
        listitem = eval(sm.getItemsEligibleForBonusPot())
    else:
        listitem = eval(sm.getItemsEligibleForBasePot(cubeList[selectedCube][0]))
    listitem.sort()
    for x in range(len(listitem)):
        itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP))
        newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#\r\n")
    if not newlist:
        sm.sendSayOkay("No equips to cube.")
        sm.dispose()
    selection = sm.sendNext(''.join(newlist))
    bagIndex = selection
    itemToCube = str(sm.getItemIDByBagIndex(selection, InvType.EQUIP))
    selection = sm.sendNext("You have selected #v"+itemToCube+"# #e #t"+itemToCube+"##n.\r\n\r\How would you like to cube the item?:\r\n#L0#Manually cube the item.#l\r\n#L1#Cube using an automated, pre-setup potential lines.#l")
    if cubeList[selectedCube][0] == 5062500:
        bonus = True
    else:
        bonus = False
    if selection == 0:
        while sm.sendNext("#fs12#"+ sm.getPotentialLines(bagIndex, False, bonus) + "\r\nPress any line to re-cube.") > -1:
            if sm.hasItem(cubeList[selectedCube][0]):
                if sm.getQuantityOfItem(cubeList[selectedCube][0]) <= 1:
                    sm.setBoxChat()
                    sm.sendNext("You are almost out of cubes.")
                    sm.setSpeakerID(9270064)
                sm.consumeCube(bagIndex, cubeList[selectedCube][0])
                sm.consumeItem(cubeList[selectedCube][0])

            else:
                if chr.getUser().getMaplePoints() >= cubeList[selectedCube][2]:
                    sm.consumeCube(bagIndex, cubeList[selectedCube][0])
                    chr.addNx(-cubeList[selectedCube][2])
                else:
                    sm.sendSayOkay("You do not have enough NX to cube.")
                    break
    elif selection == 1:
        choice = 0
        while choice < len(potList) - 1:
            text = ""
            for x in range(len(potList)):
                text += "#L" + str(x) + "##b" + potList[x] + "#l\r\n"
            choice = sm.sendNext(text)
            if choice < len(potList) - 1:
                amount = sm.sendAskNumber("How much " + potList[choice] + " would you like to cube for?", 1, 1, 100)
                #
                outPut = ""
                for k in range (len(requirementsArr)):
                    outPut += "#bCombo " + str(k + 1) + ":\r\n"
                    for key, value in requirementsArr[k].items():
                        outPut += "#r" + ("{}: {}".format(key, value)) + ", "
                    outPut += "\r\n"
                combo = sm.sendAskNumber(outPut + "What combo would you like to add this stat to?", 0, 1, 5)
                requirementsArr[combo - 1][potList[choice]] = amount
        if choice == len(potList) - 1:
            chosenStatsSay = "Once one of these combinations is achieved the npc will automatically stop cubing.\r\n"
            outPut = ""
            for k in range (len(requirementsArr)):
                outPut += "#bCombo " + str(k) + ":\r\n"
                for key, value in requirementsArr[k].items():
                    outPut += "#r" + ("{}: {}".format(key, value)) + ", "
                outPut += "\r\n"
            chosenStatsSay += outPut
            sm.sendNext(chosenStatsSay)
            while sm.sendNext("#fs12#"+ sm.getPotentialLines(bagIndex, False, bonus) + "\r\nPress any line to re-cube.") > -1:
                if sm.itemHasWantedStatsList(requirementsArr, bagIndex, bonus):
                    sm.setNpcOverrideBoxChat(9270064)
                    sm.sendNext("The item has achieved the required stats.")
                    sm.dispose()
                if sm.hasItem(cubeList[selectedCube][0]):
                    sm.consumeCube(bagIndex, cubeList[selectedCube][0])
                    sm.consumeItem(cubeList[selectedCube][0])

                else:
                    if chr.getUser().getMaplePoints() >= cubeList[selectedCube][2]:
                        sm.consumeCube(bagIndex, cubeList[selectedCube][0])
                        chr.addNx(-cubeList[selectedCube][2])
                    else:
                        sm.sendSayOkay("You do not have enough NX to cube.")
                        sm.dispose()
elif action == 1:
    newlist = ""
    for x in range(len(circulatorsList)):
        newlist += ('#L'+str(x)+'##v'+str(circulatorsList[x][0])+'#'+'#t'+str(circulatorsList[x][0])+'##l\r\n')
    circulatorChoice = sm.sendNext(newlist)
    while sm.sendNext("#fs12#" + sm.getInnerAbilityLines() + "\r\nPress any line to reset your inner ability.") > -1:
        if sm.hasItem(circulatorsList[circulatorChoice][0]):
            if sm.getQuantityOfItem(circulatorsList[circulatorChoice][0]) <= 1:
                sm.setNpcOverrideBoxChat(9270064)
                sm.sendNext("You are almost out of circulators.")
                sm.setSpeakerID(9270064)
            if sm.resetInnerAbility(circulatorsList[circulatorChoice][0]):
                sm.consumeItem(circulatorsList[circulatorChoice][0])
            else:
                sm.sendNext("You cannot reset your Inner Ability with this circulator.")
                sm.dispose()

        else:
            if sm.getHonorExp() >= circulatorsList[circulatorChoice][2]:
                if sm.resetInnerAbility(circulatorsList[circulatorChoice][0]):
                    sm.consumeItem(circulatorsList[circulatorChoice][0])
                    sm.deductHonorExp(circulatorsList[circulatorChoice][2])
                else:
                    sm.sendNext("You cannot reset your Inner Ability with this circulator.")
                    sm.dispose()
            else:
                sm.sendSayOkay("You do not have enough Honor Exp to reset your Inner Ability.")
                sm.dispose()