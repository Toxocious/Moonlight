# Mr.Newname (ID: 902016)

if chr.getLevel() < 33:
    sm.sendSayOkay("You may use #i4034803##b#t4034803##k from Lv. 33+. Please try again after reaching at least Lv. 33.")
elif sm.hasItem(4034803):
    sm.openUI(1110)
else:
    sm.sendSayOkay("You need a #i4034803##b#t4034803##k to change your name.")