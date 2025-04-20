from net.swordie.ms.enums import InvType

potList = ["STR","DEX","INT","LUK", "ATT", "Magic ATT", "All Stats", "Accuracy", "Avoidability","Max MP","Max HP", "Proceed with current list"]

cubeList = [[2711005, "Master Craftsman's Cube", 2000], [2710000, "Occult Cube", 700], [5062009, "Red Cube", 11000], [5062000, "Miracle Cube", 15000]]
playerPotList = []
eeScroll=0
tempList = []
listitem = []
itemID = []
newlist = []
bagIndex = 0

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
    newlist.append('#L'+str(x)+'##v'+str(cubeList[x][0])+'#'+'#t'+str(cubeList[x][0])+'#\r\n')
selection = sm.sendNext("#e<Auto Cube NPC>#n \r\n \r\n Hey #h #. I am in charge of Auto Cubing. Please choose of the cubes below: \r\n \r\n \r\n"+''.join(newlist))
selectedCube = selection
newlist = []
listitem = eval(sm.getScissorEquips())
for x in range(len(listitem)):
    itemID.append(sm.getItemIDByBagIndex(listitem[x], InvType.EQUIP))
    newlist.append('\n#L'+str(listitem[x])+'##v'+str(itemID[x])+'#'+"#t"+str(itemID[x])+"#")
selection = sm.sendNext(''.join(newlist))
bagIndex = selection
itemToCube = str(sm.getItemIDByBagIndex(selection, InvType.EQUIP))
selection = sm.sendNext("You have selected #v"+itemToCube+"# #e #t"+itemToCube+"##n.\r\n\r\How would you like to cube the item?:\r\n#L0#Manually cube the item.#l\r\n#L1#Cube using an automated, pre-setup potential lines.#l")
if selection == 0:
    while sm.sendNext("You have selected #v"+itemToCube+"# #e #t"+itemToCube+"##n.\r\n\r\nThe current potentials for this item is:\r\n" + sm.getPotentialLines(bagIndex, 0) + "\r\nPress next to re-cube.") > -1:
        if sm.hasItem(cubeList[selectedCube][0]):
            if sm.getQuantityOfItem(cubeList[selectedCube][0]) <= 2:
                while sm.sendAskText("You are almost out of cubes. Once your cubes are up - I will use your NX to cube.\r\ntype 'OK' to continue", "", 1, 5) != "OK":
                    pass
            sm.consumeCube(bagIndex, cubeList[selectedCube][0])
            sm.consumeItem(cubeList[selectedCube][0])
            
        else:
            if chr.getAccount().getNxCredit() > cubeList[selectedCube][2]:
                sm.consumeCube(bagIndex, cubeList[selectedCube][0])
                chr.addNx(-cubeList[selectedCube][2])
            else:
                sm.sendSayOkay("You do not have enough NX to cube.")
                break
