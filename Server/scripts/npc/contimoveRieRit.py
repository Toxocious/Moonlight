# Puro (1200003) | Snow Island : Penguin Port

victoriaIslandPrice = 800

answer = sm.sendNext("Heading Out? \r\n\r\n#b"
            "#L0##eTheme Dungeon: Riena Strait#n (Price: 0 mesos)\r\n"
            "#L1#Victoria Island (Price: "+ str(victoriaIslandPrice) +" mesos)#l")

if answer == 0:
    sm.warp(141000000, 0)
elif answer == 1:
    if sm.getMesos() < victoriaIslandPrice:
        sm.sendSayOkay("You will need more money for my services.")
    else:
        sm.deductMesos(800)
        sm.warp(104000000, 0)
