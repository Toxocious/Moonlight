# Red sign | Party quest entrance
from net.swordie.ms.enums import UIType
selection = sm.sendNext("#e<Party Quest: Dimensional Schism>#n \r\n\r\n"
                    "You can't go any higher because of the extremely dangerous creatures above. Would you like to collaborate with party members to complete the quest? If so, please have your #bparty leader#k talk to me. \r\n\r\n"
                    "#b#L0#I want to participate in the party quest.#l \r\n"
                    "#L1#I want to find party members.#l \r\n"
                    "#L2#I want to receive the Broken Glasses.#l \r\n"
                    "#L3#I would like to hear more details.#l \r\n")
#warp inside PQ
if selection == 0:
    if not sm.getFieldID() == 910002000:
        #TODO edit party size back and add party level
        if sm.getPartySize() >= 1:
            if sm.isPartyLeader():
                sm.clearPartyInfo(922010100)
                sm.warpPartyIn(922010100)
            else:
                sm.sendNext("Please have your party leader speak to me.")
        else:
            sm.sendNext("You cannot participate in the quest because you do not have at least 3 party members. If you're having trouble finding party members, try using Party Search.")
    else:
        sm.sendNext("If you're up for the challenge, I'll bring you to the top of the tower.")
        sm.warp(221023300)

#Look for party members
elif selection == 1:
    sm.openUI(UIType.PARTYSEARCH)

#Reward shop
elif selection == 2:
#TODO add times helped
    sm.sendNext("I am offering 1x #i1022073# #bBroken Glasses#k for every 5 times you help me. If you help me #b5 more times#k, #byou can receive Broken Glasses#k.")

#Explanation
elif selection == 3:
    sm.sendNext("#e<Party Quest: Dimensional Crack>#n \r\n"
                "A Dimensional Crack has appeared in #b#m220000000##k! We desperately need brave adventurers who can defeat the monsters pouring through. Please, party with some dependable allies to save #m220000000#!"
                "You must pass through several stages by defeating monsters and solving quizzes, and ultimately defeat #r#o2600622##k. \r\n"
                "#e-Level#n: 120 or above #r(Recommended Level: 120 - 139)\r\n"
                "#e-Time Limit#n: 20 min \r\n"
                "#e-Number of Players#n: 3 to 6 \r\n"
                "#e-Reward#n: #i1022073# Broken Glasses #b(obtained every 5 times you participate)#k, various Use Etc and Equip items.")
