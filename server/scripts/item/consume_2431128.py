sm.setSpeakerID(9000138)

items = [4033606,4033607,4033608,4033609,2020013,2022176,2022003,2431174,2433808,4001832,4310015,4021031,2502000,2430692,2022740,2022741,2022742,2022743,2022744,2022745,2022794,2022795,2022796,2022797,2022798,2022799]
rand = sm.getRandomIntBelow(len(items))

if not sm.canHold(4033606) and sm.canHold(2020013):
    sm.sendSayOkay("Please make sure you have room in your inventory.")

else:
    item = items[rand]
    sm.consumeItem(2431128)
    sm.giveItem(item)