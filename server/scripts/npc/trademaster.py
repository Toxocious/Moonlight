# Created by MechAviv
# [Maestra Fiametta]  |  [9390220]
# Commerci Republic : San Commerci
from net.swordie.ms.enums import EventType

names = ["Rosa (Ship level 1 Required) -", "Herb Town (Ship level 6 Required) -", "Rein Harbor (Ship level 12 Required) -", "Lith Harbor (Ship level 18 Required) -", "Dolce (Ship level 24 Required) -"]
runsADay = [15, 12, 10, 7, 5]
shipLevel = [1, 6, 12, 18, 24]
mapId = 865000000

if sm.getShipLevel() == 0:
    response = sm.sendAskYesNo("Welcome to the #eSan Commerci: Trading Post#n\r\n\r\nI see this is your first time here so let me explain some things first. "
                   "By completing voyages you can earn #e#bCommerci Denaros#k#n Which you can spend by talking to #e#bJavert#k#n over on the right."
                   "\r\n\r\n#e#kWould you like to receive a Level #r1#k ship?")
    if response:
        sm.giveShipLevel(1)
        sm.flipSpeaker()
        sm.setPlayerAsSpeaker()
        sm.sendSayOkay("#e#kCongragulations you have received a Level #r1#k ship.\r\n\r\n Talk to #bMaestra#k to begin your voyaging adventures.")
        sm.dispose()
else:
    say = "#eShip level: #r" + str(sm.getShipLevel()) + "#k\r\nShip EXP: #r" + str(sm.getShipExp()) + "/100#k\r\n\r\n#nAs you complete more voyages your ships EXP will increase unlocking more voyages that you can attempt.\r\n"
    for x in range(len(names)):
        say += "#L" + str(x) + "##b" + names[x] + "   " + str(sm.getEventAmountDone(EventType.getByVal(40 + x))) + "/" + str(runsADay[x]) + " Attempted today""#l\r\n"
    selection = sm.sendSay(say)

    if sm.getShipLevel() < (selection * 6):
        sm.sendSayOkay("#eYour ship must be level #r" + str(selection * 6) + "#k to go on this voyage.")
        sm.dispose()
    if chr.getLevel() < 220:
        sm.sendSayOkay("You must be level #b220#k to go on a voyage.")
        sm.dispose()
    if sm.getEventAmountDone(EventType.getByVal(40 + selection)) >= runsADay[selection]:
        sm.sendSayOkay("You are currently on cooldown")
        sm.dispose()
    if sm.getParty() is not None:
        sm.sendSayOkay("Please leave your party.")
        sm.dispose()

    else:
        sm.setDeathCount(3)
        sm.setInstanceTime(60*10)
        sm.addCoolDownInXays(EventType.getByVal(40 + selection), 1, 1)
        if selection == 0:
            sm.warpInstanceIn(865000200)
        if selection == 1:
            sm.warpInstanceIn(865000900)
        if selection == 2:
            sm.warpInstanceIn(865000300)
        if selection == 3:
            sm.warpInstanceIn(865000100)
        if selection == 4:
            sm.warpInstanceIn(865000400)