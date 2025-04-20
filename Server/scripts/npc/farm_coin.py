# Clara
from net.swordie.ms.loaders import ItemData

sendStr = "Your equips:\r\n"
for equip in chr.getEquippedInventory().getItems():
    itemId = str(equip.getItemId())
    sendStr += "#i " + itemId + "##z" + itemId + "#\r\n"
    for i in range(6):
        opt = equip.getOptions().get(i)
        if opt == 0:
            sendStr += "<None>"
        else:
            tier = opt // 10000
            sendStr += "(" + str(opt) + ") - "
            if tier <= 1:
                sendStr += "#b(Rare) #k"
            elif tier == 2:
                sendStr += "#d(Epic) #k"
            elif tier == 3:
                sendStr += "#r(Unique) #k"
            else:
                sendStr += "#g(Legendary) #k"
            pOpt = ItemData.getItemOptionById(opt)
            sendStr += pOpt.getString(equip.getrLevel())
        sendStr += "\r\n"
sm.sendSayOkay(sendStr)
