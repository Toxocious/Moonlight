# Suspicious Man (1061016) | Stairway to the Underground Temple

items = [
2040728,
2040729,
2040730,
2040731,
2040732,
2040733,
2040734,
2040735,
2040736,
2040737,
2040738,
2040739
]
balrogLeather = 4001261

if sm.hasItem(balrogLeather):
    sm.sendNext("Hello #h0#. I see you have #c"+ str(balrogLeather) +"# Piece"+ ("s" if sm.getQuantityOfItem(balrogLeather) > 1 else "") +" of Balrog Leather, interested in exchanging those for items?")
else:
    sm.sendNext("Hello #h0#. I can exchange #z"+str(balrogLeather)+"#for items")


selString = "Alright, this is what I can offer you\r\n#b"
i = 0
while i < len(items):
    selString += "#L"+ str(i) +"##z"+ str(items[i]) +"##l\r\n"
    i += 1
selection = sm.sendNext(selString)

quantity = sm.sendAskNumber("How many of your #b#z"+ str(balrogLeather) +"##k are you willing to trade for my #b#z"+ str(items[selection]) +"##k?"
                 "\r\nyou have #c"+ str(balrogLeather) +"# Piece"+ ("s" if sm.getQuantityOfItem(balrogLeather) > 1 else "") +" of Balrog Leather", 1, 1, 100)

if not sm.canHold(items[selection]):
    sm.sendSayOkay("You don't have enough space in your inventory.")
elif sm.getQuantityOfItem(balrogLeather) < quantity:
    sm.sendSayOkay("You don't fool me\r\nYou do not have enough leather pieces.")
else:
    sm.giveItem(items[selection], quantity)
    sm.consumeItem(balrogLeather, quantity)
    sm.sendSayOkay("Thank you for your redemption")
