# Sylph Ring NPC

from net.swordie.ms.loaders import ItemData

sylphRings = [1114200,1114219,1114201,1114205,1114206,1114202,1114212,1114211,1114210,1114230]
stageOne = 1114200
stageTwo = 1114219
stageThree = 1114201
stageFour = 1114205
stageFive = 1114206
stageSix = 1114202
stageSeven = 1114212
stageEight = 1114211
stageNine = 1114210
stageTen = 1114230
freudsJournal = 4460005

if sm.hasItem(stageTen) or chr.getEquippedInventory().containsItem(stageTen):
    sm.sendSayOkay("So you finally completed your #bSylph Ring#k mortal... I for one have also finished my research. I Wish you luck on your future endeavors.")

else:
    response = sm.sendNext("Welcome mortal one. You come seeking power as I have come seeking knowledge. I will "
                           "trade you a #bSylph Ring#k for a #bFreud's Journal#k.#b\r\n"
                           "#L0#What is a Sylph Ring.#l\r\n"
                           "#L1#Yes I would like a Sylph Ring.#l\r\n#r"
                           "#L2#I would like to upgrade my Sylph Ring.#l\r\n")

    if response == 0:

        sm.sendSayOkay("A Sylph Ring is a very powerful ring that only I can infuse with power. To make this ring "
                       "reach its full potential I will require #r10 #bFreud's Journals#k for within those journals "
                       "lay the power to fully awaken the Sylph.")
        sm.dispose()

    elif response == 1:

        for x in range (len(sylphRings)):
            if sm.hasItem(sylphRings[x]):
                sm.sendSayOkay("Mortal I can not give you something you already possess. If you wish to upgrade your ring"
                               " you may talk to me again then select the #rUpgrade#k option.")
                sm.dispose()

        if not sm.hasItem(freudsJournal):
            sm.sendSayOkay("Do not try my patience mortal I can sense that you do not possess a #bFreud's Journal#k.")
            sm.dispose()

        if not sm.canHold(stageOne):
            sm.sendSayOkay("Mortal how do you expect to carry a #bSylph Ring#k while your inventory is full.")
            sm.dispose()

        for x in range (len(sylphRings)):
            if chr.getEquippedInventory().containsItem(sylphRings[x]):
                sm.sendSayOkay("Mortal I can not give you something you already possess. If you wish to upgrade your ring"
                               " unequip it and then you may talk to me again by selecting the #rUpgrade#k option.")
                sm.dispose()

        if sm.hasItem(freudsJournal):
            answer = sm.sendAskYesNo("Ahhhh yes mortal one I can feel the radiating energy from your #bFreud's Journal#k. Do you"
                                     " wish to claim your #bSylph Ring#k?")

            if answer:
                sm.sendSayOkay("It is done then mortal... The ring is now yours. Return to me when you acquire more "
                               "#bFreud's Journals#k so that we may unravel the mysteries of the Sylph together.")
                sm.consumeItem(freudsJournal)
                sm.giveItem(stageOne)
                sm.dispose()

            else:
                sm.sendSayOkay("Do not waste my time mortal.")

    elif response == 2:

        if not sm.canHold(stageOne):
            sm.sendSayOkay("Mortal how do you expect to upgrade your #bSylph Ring#k while your inventory is full.")
            sm.dispose()

        if not sm.hasItem(freudsJournal):
            sm.sendSayOkay("Do not try my patience mortal I can sense that you do not possess a #bFreud's Journal#k.")
            sm.dispose()

        for x in range (len(sylphRings)):
            if chr.getEquippedInventory().containsItem(sylphRings[x]):
                sm.sendSayOkay("Mortal I can not upgrade you ring while it is equipped please"
                               " unequip it and then you may talk to me again.")
                sm.dispose()

        if sm.hasItem(freudsJournal):

            if sm.hasItem(stageOne):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageOne)
                    sm.giveItem(stageTwo)
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageTwo):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageTwo)
                    sm.giveItem(stageThree)
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageThree):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageThree)
                    sm.giveItem(stageFour)
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageFour):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageFour)
                    sm.giveItem(stageFive)
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageFive):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageFive)

                    def giveRing():
                        Ring = ItemData.getEquipDeepCopyFromID(stageSix, False)
                        Ring.setSocket(0, 4311)
                        chr.addItemToInventory(Ring)

                    giveRing()
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageSix):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageSix)

                    def giveRing():
                        Ring = ItemData.getEquipDeepCopyFromID(stageSeven, False)
                        Ring.setSocket(0, 4311)
                        Ring.setOptionBase(1, 40601)
                        chr.addItemToInventory(Ring)

                    giveRing()
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageSeven):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageSeven)

                    def giveRing():
                        Ring = ItemData.getEquipDeepCopyFromID(stageEight, False)
                        Ring.setSocket(0, 4311)
                        Ring.setOptionBase(0, 40601)
                        Ring.setOptionBase(1, 30291)
                        chr.addItemToInventory(Ring)

                    giveRing()
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageEight):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageEight)

                    def giveRing():
                        Ring = ItemData.getEquipDeepCopyFromID(stageNine, False)
                        Ring.setSocket(0, 4311)
                        Ring.setOptionBase(0, 40601)
                        Ring.setOptionBase(1, 30291)
                        Ring.setOptionBase(2, 42051)
                        chr.addItemToInventory(Ring)

                    giveRing()
                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            elif sm.hasItem(stageNine):

                stage1 = sm.sendAskYesNo("Do you wish to unlock the hidden power within your #bSylph Ring#k?")

                if stage1:
                    sm.consumeItem(freudsJournal)
                    sm.consumeItem(stageNine)

                    def giveRing():
                        Ring = ItemData.getEquipDeepCopyFromID(stageTen, False)
                        Ring.setSocket(0, 4311)
                        Ring.setSocket(1, 4311)
                        Ring.setSocket(2, 4311)
                        Ring.setOptionBase(0, 40601)
                        Ring.setOptionBase(1, 30291)
                        Ring.setOptionBase(2, 42051)
                        chr.addItemToInventory(Ring)

                    giveRing()

                    sm.sendSayOkay("It is done then mortal... The ring has been infused with magical energies. Return to me when you acquire more "
                                   "#bFreud's Journals#k so that we may further delve into the mysteries of the Sylph together.")
                    sm.dispose()

                else:
                    sm.sendSayOkay("Do not waste my time mortal.")

            else:
                sm.sendSayOkay("Do not waste my time mortal. I know you do not possess a #bSylph Ring#k")