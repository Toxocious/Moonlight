PIECE_OF_ANGUISH = 2434586
QUANTITY_REQ = 5

items = [
    1062165, # Trixter Warrior Pants
    1062166, # Trixter Dunwitch Pants
    1062167, # Trixter Ranger Pants
    1062168, # Trixter Assassin Pants
    1062169, # Trixter Wanderer Pants
]

msg = "Get your " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_ANGUISH) + "# and choose your armor #b\r\n"
for i in range(len(items)):
    msg += "#L" + str(i) + "##v" + str(items[i]) + "# #z"  + str(items[i]) + "##l\r\n"
selection = sm.sendNext(msg)

if sm.getQuantityOfItem(PIECE_OF_ANGUISH) == 1:
    piece_string = "Piece"
else:
    piece_string = "Pieces"

if not sm.hasItem(PIECE_OF_ANGUISH, QUANTITY_REQ):
    sm.sendSayOkay("I see that you have #b" + str(sm.getQuantityOfItem(PIECE_OF_ANGUISH)) + "#k " + piece_string + " of Anguish. " +
        "You need at least #b" + str(QUANTITY_REQ) + "#kto exchange for a piece of equipment.")
elif not sm.canHold(items[selection]):
    sm.sendSayOkay("Please make space in your inventory.")
else:
    sm.consumeItem(PIECE_OF_ANGUISH, QUANTITY_REQ)
    sm.giveItem(items[selection])