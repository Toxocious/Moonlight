PIECE_OF_TIME = 2434584
QUANTITY_REQ = 5


items = [
    1003797, # Royal Warrior Helm
    1003798, # Royal Dunwitch Hat
    1003799, # Royal Ranger Beret
    1003800, # Royal Assassin Hood
    1003801, # Royal Wanderer Hat
]

msg = "Get your " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_TIME) + "# and Choose your Armor #b\r\n"
for i in range(len(items)):
    msg += "#L" + str(i) + "##v" + str(items[i]) + "# #z"  + str(items[i]) + "##l\r\n"
selection = sm.sendNext(msg)

if sm.getQuantityOfItem(PIECE_OF_TIME) == 1:
    piece_string = "Piece"
else:
    piece_string = "Pieces"

if not sm.hasItem(PIECE_OF_TIME, QUANTITY_REQ):
    sm.sendSayOkay("I see that you have #b" + str(sm.getQuantityOfItem(PIECE_OF_TIME)) + "#k " + piece_string + " of Time. " +
                "You need at least #b" + str(QUANTITY_REQ) + "#kto exchange for a piece of equipment.")
elif not sm.canHold(items[selection]):
    sm.sendSayOkay("Please make space in your inventory.")
else:
    sm.consumeItem(PIECE_OF_TIME, QUANTITY_REQ)
    sm.giveItem(items[selection])