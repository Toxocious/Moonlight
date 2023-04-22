# Arwen the Fairy (1032100) | Ellinia

squishyLiquid = 4000004 # goes for 14meso per item in Shops
squishyLiquidQ = 100

slimeid = 100006
mesoReward = 5000

if sm.getQuantityOfItem(squishyLiquid) >= 100:
    if sm.sendAskYesNo("Hey, I see you got "+ str(squishyLiquidQ) +" pieces of #b#z"+ str(squishyLiquid) +"##k!\r\nDo you want to trade them for a reward?"):
        if sm.getQuantityOfItem(squishyLiquid) >= 100:
            sm.sendSayOkay("There you go!\r\n"+ str(mesoReward) +" mesos, for your hard work.\r\n\r\nIf you want to make more money, talk to me again at anytime!")
            sm.giveMesos(mesoReward)
            sm.consumeItem(squishyLiquid, squishyLiquidQ)

else:
    if sm.sendAskYesNo("Hey you!\r\nAre you willing to help me?"):
        sm.sendNext("Ellinia used to be a peaceful time, but lately it has turned into chaos. Ellinia has been surrounded by #r#o"+ str(slimeid) +"##k. "
                      "You may believe this is a bad thing, but for me, however.. it's not bad at all. "
                      "I am researching how these slimes come to life.\r\n"
                      "If you could bring me "+ str(squishyLiquidQ) +" #b#z"+ str(squishyLiquid) +"##k. "
                      "It would allow me to do a lot of research, I will certainly reward you!")
    else:
        sm.sendSayOkay("Alright, let me know if you change your mind!")