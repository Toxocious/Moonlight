PIECE_OF_RUIN = 2434588
QUANTITY_REQ = 15

items = [
    1212014,
    1222014,
    1232014,
    1242014,
    1242042,
    1252014,
    1262015,
    1272014,
    1282014,
    1302152,
    1312065,
    1322096,
    1332130,
    1342036,
    1362019,
    1372084,
    1382104,
    1402095,
    1412065,
    1422066,
    1432086,
    1442116,
    1452111,
    1462099,
    1472122,
    1482084,
    1492085,
    1522018,
    1532018,
    1542015,
    1552015,
    1582015
]

msg = "Get your " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_RUIN) + "# and Choose your Weapon #b\r\n"
for i in range(len(items)):
    msg += "#L" + str(i) + "##v" + str(items[i]) + "# #z"  + str(items[i]) + "##l\r\n"
selection = sm.sendNext(msg)

if not sm.hasItem(PIECE_OF_RUIN, QUANTITY_REQ):
    sm.sendSayOkay("You need at least " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_RUIN) + "#.")
elif not sm.canHold(items[selection]):
    sm.sendSayOkay("Please make space in your inventory.")
else:
    sm.consumeItem(PIECE_OF_RUIN, QUANTITY_REQ)
    sm.giveItem(items[selection])