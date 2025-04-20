# Fishing Npc (9209005 Tae Gong)

from net.swordie.ms.constants import GameConstants

FISH_NET = 2270008
NET_COST = 3000000

FISHING_MAP = GameConstants.FISHING_MAP

opts = ["Go to Fishing Map", "Buy fishing nets", "Exchange ", "Exchange ", "Exchange "]
menu = sm.sendNext("What would you like to do?\r\n#b" + sm.menu(opts) + "#k")

def exchangeFish(opt):
    sm.sendNext("TODO: ")

if menu == 0:
    sm.warp(FISHING_MAP)
elif menu == 1:
    if sm.sendAskYesNo("It requires " + str(net_cost) + " mesos for 120 nets. Do you want to purchase?"):
        if sm.getMesos() >= NET_COST and sm.canHold(FISH_NET):
            sm.deductMesos(NET_COST)
            sm.giveItem(FISH_NET, 120)
            sm.sendSayOkay("Happy Fishing!")
        else:
            sm.sendNext("Seems like you don't have enough mesos or enough space in your USE inventory.")
elif menu >= 2 and menu <= 4:
    exchangeFish(menu)
