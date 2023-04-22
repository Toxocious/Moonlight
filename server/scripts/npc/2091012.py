from net.swordie.ms.constants import ItemConstants

# Lao (2091012) | Mu Lung Dojo Hall

# Dojo Array Start

itemsDojo = [
    [5062500,5062006,2070018,2046897],
    [2431174,2433808,4001832,4310015,4021031,2502000,2430692,2022740,2022741,2022742,2022743,2022744,2022745,2022794,2022795,2022796,2022797,2022798,2022799,3700080,3700096,3010425,3700049,5220000,5220100,3010412,3010521],
    [1002790,1002791,1002792,1002793,1002794,1052160,1052161,1052162,1052163,1052164,1072361,1072362,1072363,1072364,1072365,1082239,1082240,1082241,1082242,1082243],
    [1212012,1222012,1232012,1242012,1252012,1312038,1322061,1332075,1332076,1342012,1362017,1372045,1382059,1402047,1412034,1422038,1432049,1442067,1452059,1462051,1472071,1482024,1492025,1522016,1532016,1542012,1552057],
    [1002776,1002777,1002778,1002779,1002780,1032031,1052155,1052156,1052157,1052158,1052159,1072356,1072357,1072358,1072359,1072360,1082234,1082235,1082236,1082237,1082238,1092057,1092058,1092059,1122012],
    [1212011,1222011,1232011,1242011,1252011,1312037,1322060,1332074,1332075,1342011,1362016,1372044,1382057,1402046,1412033,1422037,1432047,1442063,1452057,1462050,1472068,1482023,1492023,1522015,1532015,1542013,1552013],
    [1003280,1003281,1003282,1003283,1003284,1052374,1052375,1052376,1052377,1052378,1072544,1072545,1072546,1072547,1072548,1082328,1082329,1082330,1082331,1082332],
    [1212017,1222017,1232017,1242017,1252018,1302173,1312072,1322107,1332148,1332149,1342040,1362022,1372100,1382124,1402111,1412071,1422073,1432099,1442136,1452129,1462118,1472141,1482102,1492101,1522020,1532037,1542033,1552033],
    [1003285,1003286,1003287,1003288,1003289,1032108,1052379,1052380,1052381,1052382,1052383,1072549,1072550,1072551,1072552,1072553,1082333,1082334,1082335,1082336,1082337,1092092,1092093,1092094,1122148],
    [1212018,1222018,1232018,1242018,1252022,1302174,1312073,1322108,1332150,1332151,1342041,1362023,1372101,1382125,1402112,1412072,1422074,1432100,1442137,1452130,1462119,1472142,1482103,1492102,1522021,1532038,1542034,1552034],
]

costDojoPoints = [
    [400000,400000,100000,2000000],
    [20000,400000,5000,100000,50000,8000,15000,7000,7000,7000,7000,7000,7000,35000,35000,35000,35000,35000,35000,5000000,12000000,50000000,100000000,20000,400000,100000000,500000000],
    [100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000],
    [250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000,250000],
    [125000,125000,125000,125000,125000,200000,125000,125000,125000,125000,125000,125000,125000,125000,125000,125000,125000,125000,125000,125000,125000,100000,100000,100000,250000],
    [500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000,500000],
    [175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000,175000],
    [750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000,750000],
    [200000,200000,200000,200000,200000,240000,200000,200000,200000,200000,200000,200000,200000,200000,200000,200000,200000,200000,200000,200000,200000,150000,150000,150000,400000],
    [1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000,1000000],
]

# Dojo Array Finish

dojoHall = 925020001

if sm.getFieldID() == dojoHall:
    selection = sm.sendNext("My master is the strongest person in Mu Lung. His challenges grant Dojo Points Which you can spend here.\r\nYou currently have #r"+ str(sm.getDojoPoints()) +" #bDojo Points\r\n#b"
                            "#L0#Trade a Bamboo Luck Sack for 1,000,000 Dojo Points.#l\r\n"
                            "#L1#Trade 1,000,000 Dojo Points for a Bamboo Luck Sack.#l\r\n"
                            "#L2#Spend Dojo Points.#l\r\n")

    if selection == 0:
        answer = sm.sendAskNumber("How many #b#v 3993002 # #t 3993002 #(s)#k #kdo you wish purchase?", 0, 1, 1000)

        Total = answer * 1
        totalQty = answer * 1000000

        if  sm.getDojoPoints() <= totalQty:
            sm.sendSayOkay("You do not have enough #bDojo Points#k.")
            sm.dispose()

        else:
            sm.deductDojoPoints(totalQty)
            sm.giveItem(3993002, Total)
            sm.sendSayOkay("Thank you for your purchase!\r\nYou have #r"+ str(sm.getDojoPoints()) +"#b Dojo Points#k left.")
            sm.dispose()

    if selection == 1:
        answer = sm.sendAskNumber("How many #b#v 3993002 # #t 3993002 #(s)#k #kdo you wish sell?", 0, 1, 1000)

        Total = answer * 1
        totalQty = answer * 1000000


        if not sm.hasItem(3993002, Total):
            sm.sendSayOkay("You do not have enough #b#v 3993002 # #t 3993002 #(s)#k.")
            sm.dispose()

        else:
            sm.consumeItem(3993002, Total)
            sm.giveDojoPoints(totalQty)
            sm.sendSayOkay("Thank you for your purchase!\r\nYou now have #r"+ str(sm.getDojoPoints()) +"#b Dojo Points#k.")
            sm.dispose()

    if selection == 2:

        selection1 = sm.sendNext("Which category of items would you like to purchase from?\r\nYou currently have #r"+ str(sm.getDojoPoints()) +" #bDojo Points\r\n"
                                "#L0#Useables.#l\r\n"
                                "#L1#Other.#l\r\n"
                                "#L2#Reverse Armors.#l\r\n"
                                "#L3#Reverse Weapons.#l\r\n"
                                "#L4#Timeless Armors.#l\r\n"
                                "#L5#Timeless Weapons.#l\r\n"
                                "#L6#Abyss Armors.#l\r\n"
                                "#L7#Abyss Weapons.#l\r\n"
                                "#L8#Fearless Armors.#l\r\n"
                                "#L9#Fearless Weapons.#l\r\n")

        listStr = "What item would you like to purchase? #b"

        i = 0

        while i < len(itemsDojo[selection1]):
            listStr += "\r\n#L" + str(i) + "##v" + str(itemsDojo[selection1][i]) + "#"   "#z" + str(itemsDojo[selection1][i]) + "# #r(" + str(costDojoPoints[selection1][i]) + " Dojo Points)#b"

            i += 1

        selection2 = sm.sendNext(listStr)

        if selection1 == 0 or selection1 == 1:
            materialStr = "So you want #b#v" + str(itemsDojo[selection1][selection2]) + "##z" + str(itemsDojo[selection1][selection2]) + "#s? \r\n #kThat will cost you.\r\n"

        else:
            materialStr = "So you want a #b#v" + str(itemsDojo[selection1][selection2]) + "##z" + str(itemsDojo[selection1][selection2]) + "#? \r\n#kThat will cost you.\r\n"

        i = 0

        if costDojoPoints[selection1][selection2] > 0:
            materialStr += "\r\n#i4001620#   #r" + str(costDojoPoints[selection1][selection2]) + " #bDojo Points"

        if (selection1 == 0 or selection1 == 1) and not ItemConstants.isThrowingItem(itemsDojo[selection1][selection2]):
            sm.chat("a")
            materialStr += "\r\n\r\nHow many do you wish to purchase?"
            amount = sm.sendAskNumber(materialStr, 1, 1, 50000)

            TotalCost = (amount * costDojoPoints[selection1][selection2])
            TotalQty = amount

            if sm.getDojoPoints() <= TotalCost:
                sm.sendSayOkay("I'm afraid you cannot afford this purchase.")
                sm.dispose()

            else:
                if not sm.canHold(itemsDojo[selection1][selection2]):
                    sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                    sm.dispose()

                else:
                    i = 0
                    if costDojoPoints[selection1][selection2] > 0:
                        sm.deductDojoPoints(TotalCost)
                        sm.giveItem(itemsDojo[selection1][selection2], TotalQty)
                        sm.sendSayOkay("Come and see me if you need anything else.")

        else:
            response = sm.sendAskYesNo(materialStr)



            if sm.getDojoPoints() <= costDojoPoints[selection1][selection2]:
                sm.sendSayOkay("I'm afraid you cannot afford this purchase.")
                sm.dispose()

            else:
                if not sm.canHold(itemsDojo[selection1][selection2]):
                    sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                    sm.dispose()

                else:
                    i = 0
                    if costDojoPoints[selection1][selection2] > 0:
                        sm.deductDojoPoints(costDojoPoints[selection1][selection2])
                        sm.giveItem(itemsDojo[selection1][selection2])
                        sm.sendSayOkay("Come and see me if you need anything else.")

elif sm.sendNext:
    selection = sm.sendNext("Hey there would you like to come try your luck at Mu Lung Dojo?\r\n#b"
                            "#L0#Yes, Teleport me to Dojo now.#l\r\n"
                            "#L1#No, I would like to purchase something.#l\r\n")
    if selection == 0: #
        sm.warp(925020001)
    if selection == 1:

        selection1 = sm.sendNext("Which category of items would you like to purchase from?\r\nYou currently have #r"+ str(sm.getDojoPoints()) +" #bDojo Points\r\n"
                                                                                                                                               "#L0#Useables.#l\r\n"
                                                                                                                                               "#L1#Other.#l\r\n"
                                                                                                                                               "#L2#Reverse Armors.#l\r\n"
                                                                                                                                               "#L3#Reverse Weapons.#l\r\n"
                                                                                                                                               "#L4#Timeless Armors.#l\r\n"
                                                                                                                                               "#L5#Timeless Weapons.#l\r\n"
                                                                                                                                               "#L6#Abyss Armors.#l\r\n"
                                                                                                                                               "#L7#Abyss Weapons.#l\r\n"
                                                                                                                                               "#L8#Fearless Armors.#l\r\n"
                                                                                                                                               "#L9#Fearless Weapons.#l\r\n")

        listStr = "What item would you like to purchase? #b"

        i = 0

        while i < len(itemsDojo[selection1]):
            listStr += "\r\n#L" + str(i) + "##v" + str(itemsDojo[selection1][i]) + "#"   "#z" + str(itemsDojo[selection1][i]) + "# #r(" + str(costDojoPoints[selection1][i]) + " Dojo Points)#b"

            i += 1

        selection2 = sm.sendNext(listStr)

        if selection1 == 0 or selection1 == 1:
            materialStr = "So you want #b#v" + str(itemsDojo[selection1][selection2]) + "##z" + str(itemsDojo[selection1][selection2]) + "#s? \r\n #kThat will cost you.\r\n"

        else:
            materialStr = "So you want a #b#v" + str(itemsDojo[selection1][selection2]) + "##z" + str(itemsDojo[selection1][selection2]) + "#? \r\n#kThat will cost you.\r\n"

        i = 0

        if costDojoPoints[selection1][selection2] > 0:
            materialStr += "\r\n#i4001620#   #r" + str(costDojoPoints[selection1][selection2]) + " #bDojo Points"

        if selection1 == 0 or selection1 == 1:
            materialStr += "\r\n\r\nHow many do you wish to purchase?"
            amount = sm.sendAskNumber(materialStr, 1, 1, 50000)

            TotalCost = (amount * costDojoPoints[selection1][selection2])
            TotalQty = amount

            if sm.getDojoPoints() <= TotalCost:
                sm.sendSayOkay("I'm afraid you cannot afford this purchase.")
                sm.dispose()

            else:
                if not sm.canHold(itemsDojo[selection1][selection2]):
                    sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                    sm.dispose()

                else:
                    i = 0
                    if costDojoPoints[selection1][selection2] > 0:
                        sm.deductDojoPoints(TotalCost)
                        sm.giveItem(itemsDojo[selection1][selection2], TotalQty)
                        sm.sendSayOkay("Come and see me if you need anything else.")

        else:
            response = sm.sendAskYesNo(materialStr)



            if sm.getDojoPoints() <= costDojoPoints[selection1][selection2]:
                sm.sendSayOkay("I'm afraid you cannot afford this purchase.")
                sm.dispose()

            else:
                if not sm.canHold(itemsDojo[selection1][selection2]):
                    sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                    sm.dispose()

                else:
                    i = 0
                    if costDojoPoints[selection1][selection2] > 0:
                        sm.deductDojoPoints(costDojoPoints[selection1][selection2])
                        sm.giveItem(itemsDojo[selection1][selection2])
                        sm.sendSayOkay("Come and see me if you need anything else.")
