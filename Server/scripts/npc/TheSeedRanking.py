# Tower of Oz Weekly Ranking Bulletin Board | Tower of Oz (992000000)
from net.swordie.ms.enums import UIType

answer = sm.sendNext("#e[Tower of Oz Ranking Board] #n\r\n#b"
                    "#L0#Tower of Oz Exploration Ranking#l\r\n"
                    "#L1#Tower of Oz Exploration Ranking Reward#l\r\n"
                    "#L2#Your Deepest Floor#l")

if answer == 0:
    sm.openUI(UIType.UI_TOWERRANK)

elif answer == 1:
    sm.sendNext("#e[Tower of Oz Exploration Ranking Reward]\r\n\r\n#b"
                "Ranking Reset: Every Monday at midnight\r\n#n"
                "Make it into the #etop 30#n before the reset to get a reward for the week.")
    sm.sendPrev("#b#e1st: #i1190502# Tower of Oz Legend Emblem\r\n"
                "2nd - 10th: #i1190500# Tower of Oz Master Emblem\r\n"
                "11th - 30th: #i1190501# Tower of Oz Expert Emblem\r\n\r\n#n#k"
                "Usable until next #e#bMonday at midnight#n#k, beginning when you obtain the reward.")
else:
    sm.sendSayOkay("Your deepest floor is #b#e[FLOOR]") # [FLOOR] for now
