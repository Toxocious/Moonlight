
# Stage Items
items = [1032191, 1113038, 1122256]
rand = sm.getRandomIntBelow(len(items))
random = sm.getRandomIntBelow(30)

# Boss Items
items2 = [1003893, 1132230]
rand2 = sm.getRandomIntBelow(len(items))
random2 = sm.getRandomIntBelow(200)

if sm.hasMobsInField(sm.getFieldID()):
    sm.chatRed("You must kill all the mobs in the map before proceeding")
else:
    if sm.getFieldID() == 861000500:
        sm.warpInstanceOut(861000000)
        if random2 == 1:
            item2 = items2[rand]
            sm.giveItem(item2)
    else:
        if sm.isPartyLeader():
            sm.setInstanceTime(5*60)
            sm.warp(sm.getFieldID() + 100)
            if random == 1:
                item = items[rand]
                sm.giveItem(item)
        else: 
            sm.warp(sm.getFieldID() + 100)
            if random == 1:
                item = items[rand]
                sm.giveItem(item)