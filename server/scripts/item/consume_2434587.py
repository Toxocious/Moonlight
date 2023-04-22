PIECE_OF_DESTRUCTION = 2434587
QUANTITY_REQ = 15

items = [
    1212127, # Fafnir Mana Cradle
    1222058, # Fafnir Angelic Shooter
    1232057, # Fafnir Death Bringer
    1242060, # Fafnir Split Edge
    1252015, # Fafnir Scepter
    1262016, # Fafnir Psy-limiter
    1272015, # Fafnir Chain
    1282015, # Fafnir Lucent Gauntlet
    1302275, # Fafnir Mistilteinn
    1312153, # Fafnir Twin Cleaver
    1322203, # Fafnir Guardian Hammer
    1332225, # Fafnir Damascus
    1342082, # Fafnir Rapid Edge
    1362090, # Fafnir Ciel Claire
    1372177, # Fafnir Mana Taker
    1382208, # Fafnir Mana Crown
    1402196, # Fafnir Penitent Tears
    1412135, # Fafnir Battle Cleaver
    1422140, # Fafnir Lightning Striker
    1432167, # Fafnir Brionak
    1442223, # Fafnir Moon Glaive
    1452205, # Fafnir Wind Chaser
    1462193, # Fafnir Windwing Shooter
    1472214, # Fafnir Risk Holder
    1482168, # Fafnir Perry Talon
    1492179, # Fafnir Zeliska
    1522094, # Fafnir Dual Windwing
    1532098, # Fafnir Lost Cannon
    1542063, # Fafnir Raven Ring
    1552063, # Fafnir Indigo Flash
    1582016, # Fafnir Big Mountain
    1592018, # Fafnir Ancient Bow
]

msg = "Get your " + str(QUANTITY_REQ) + " #v" + str(PIECE_OF_DESTRUCTION) + "# and Choose your Weapon #b\r\n"
for i in range(len(items)):
    msg += "#L" + str(i) + "##v" + str(items[i]) + "# #z"  + str(items[i]) + "##l\r\n"
selection = sm.sendNext(msg)

if sm.getQuantityOfItem(PIECE_OF_DESTRUCTION) == 1:
    piece_string = "Piece"
else:
    piece_string = "Pieces"

if not sm.hasItem(PIECE_OF_DESTRUCTION, QUANTITY_REQ):
    sm.sendSayOkay("I see that you have #b" + str(sm.getQuantityOfItem(PIECE_OF_DESTRUCTION)) + "#k " + piece_string + " of Destruction. " +
                    "You need at least #b" + str(QUANTITY_REQ) + "#k to exchange for a piece of equipment.")
elif not sm.canHold(items[selection]):
    sm.sendSayOkay("Please make space in your inventory.")
else:
    sm.consumeItem(PIECE_OF_DESTRUCTION, QUANTITY_REQ)
    sm.giveItem(items[selection])