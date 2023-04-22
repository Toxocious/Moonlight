# Juliet (2112003) | PQ site
from net.swordie.ms.enums import UIType
sm.setSpeakerID(2112003)
if sm.getFieldID() != 910002000:
    selection = sm.sendNext("#e<Party Quest: Romeo & Juliet>#n \r\n"
                "Magatia faces a grave threat. We need brave adventurers to help us.\r\n\r\n"
                "#b#L0#Listen to Juliet's story.#l \r\n"
                "#L1#Start the quest.#l \r\n"
                "#L2#Find a party.#l\r\n"
                "#L3#Make a necklace with Alcadno Marbles.#l \r\n"
                "#L4#Combine two necklaces into one.#l")
    #Listen to Story
    if selection == 0:
        sm.sendNext("Romeo and I are in love. But I am an Alcadno, and he is a Zenumist. There's no hope for us to be together...")
        sm.sendNext("The Alcadno and the Zenumist were not always enemies! There must be a way to bring peace to our two sides!")
        sm.sendNext("But in spite of everything I've tried, Magatia is#b on the verge of war#k. It's all because#b someone stole the power source of both Zenumist and Alcadno#k. And the two sides are blaming each other for it!")
        sm.sendNext("I got a tip that the real thief is#b a third party#k. If we're ever going to have peace -- and love for me and Romeo -- we need to find#b the thirs party#k and stop his evil plan!")
        sm.sendNext("Fight for the peace of Magatia! \r\n"
                    "#e-Level#n: 70+ #r(Recommended: 70 - 99)#k \r\n"
                    "#e-Time Limit#n: 20 min \r\n"
                    "#e-Players#n: 4 \r\n"
                    "#e-Reward#n: \r\n"
                    "#i1122117# Juliet's Pendant \r\n"
                    "(Can be obtained from #bJuliet#k once you collect #r2#b Alcadno Marbles#k.) \r\n"
                    "#i1122118# Symbol of Eternal Love \r\n"
                    "(Can be traded for 1 #bRomeo's Pendant#k and 1 #bJuliet's Pendant#k)")
    #Enter PQ
    #TODO add pq
    elif selection == 1:
        if sm.isPartyLeader():
            sm.warpPartyIn(926100000)
        else:
            sm.sendNext("The party leader can proceed to the next stage.")

    #Search for party
    elif selection == 2:
        sm.openUI(UIType.PARTYSEARCH)

    #Craft juliet pendant
    elif selection == 3:
        if sm.hasItem(4001160, 2):
            if sm.canHold(1122117):
                sm.consumeItem(4001160, 2)
                sm.giveItem(1122117)
            else:
                sm.sendNext("Please make some space in your equipment inventory.")
        else:
            sm.sendNext("To make Juliet's Pendant, we need 2 Alcadno Marbles. You seem to be missing a few.")

    #Combine two necklaces
    elif selection == 4:
        if sm.hasItem(1122116) and sm.hasItem(1122117):
            if sm.canHold(1122118):
                sm.consumeItem(1122116)
                sm.consumeItem(1122117)
                sm.giveItem(1122118)
            else:
                sm.sendNext("Please make some space in your equipment inventory.")
        else:
            sm.sendNext("You need Romeo's Pendant and Juliet's Pendant to combine them.")

else:
    selection = sm.sendNext("Brave Maplers, please help us preserve the fragile peace of Magatia!\r\n\r\n"
                "#b#L10#Go to #m261000021# to listen to Juliet's story.#l")
    if selection == 10:
        sm.warp(261000021)