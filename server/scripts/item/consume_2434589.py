PIECE_OF_DARKNESS = 2434589
QUANTITY_REQ = 5


items = [
    1003172,
    1003173,
    1003174,
    1003175,
    1003176,
    1102275,
    1102276,
    1102277,
    1102278,
    1102279,
    1082295,
    1082296,
    1082297,
    1082298,
    1082299,
    1052314,
    1052315,
    1052316,
    1052317,
    1052318,
    1072485,
    1072486,
    1072487,
    1072488,
    1072489,
]

msg = "Get your " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_DARKNESS) + "# and Choose your Weapon #b\r\n"
for i in range(len(items)):
    msg += "#L" + str(i) + "##v" + str(items[i]) + "# #z"  + str(items[i]) + "##l\r\n"
selection = sm.sendNext(msg)

if not sm.hasItem(PIECE_OF_DARKNESS, QUANTITY_REQ):
    sm.sendSayOkay("You need at least " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_DARKNESS) + "#.")
elif not sm.canHold(items[selection]):
    sm.sendSayOkay("Please make space in your inventory.")
else:
    sm.consumeItem(PIECE_OF_DARKNESS, QUANTITY_REQ)
    sm.giveItem(items[selection])