itemID = [4000660,4000661,4000662,4000663,2430200]

for i in range(len(itemID)):
    if not sm.hasItem(itemID[i]):
        sm.sendSayOkay("You are missing some of the items required to make the #bDream Key")
        sm.dispose()
for i in range(len(itemID)):
    sm.consumeItem(itemID[i])
sm.giveItem(4032923)