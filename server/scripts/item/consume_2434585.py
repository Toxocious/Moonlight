PIECE_OF_MOCKERY = 2434585
QUANTITY_REQ = 5


items = [
    1042254, # Eagle Eye Warrior Armor
    1042255, # Eagle Eye Dunwitch Robe
    1042256, # Eagle Eye Ranger Cowl
    1042257, # Eagle Eye Assassin Shirt
    1042258, # Eagle Eye Wanderer Coat
]

msg = "Get your " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_MOCKERY) + "# and Choose your Armor #b\r\n"
for i in range(len(items)):
    msg += "#L" + str(i) + "##v" + str(items[i]) + "# #z"  + str(items[i]) + "##l\r\n"
selection = sm.sendNext(msg)

if sm.getQuantityOfItem(PIECE_OF_MOCKERY) == 1:
    piece_string = "Piece"
else:
    piece_string = "Pieces"

if not sm.hasItem(PIECE_OF_MOCKERY, QUANTITY_REQ):
    sm.sendSayOkay("I see that you have #b" + str(sm.getQuantityOfItem(PIECE_OF_MOCKERY)) + "#k " + piece_string + " of Mockery. " +
            "You need at least #b" + str(QUANTITY_REQ) + "#kto exchange for a piece of equipment.")
elif not sm.canHold(items[selection]):
    sm.sendSayOkay("Please make space in your inventory.")
else:
    sm.consumeItem(PIECE_OF_MOCKERY, QUANTITY_REQ)
    sm.giveItem(items[selection])