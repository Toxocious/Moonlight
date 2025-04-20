sm.setSpeakerID(2143000)

answer = sm.sendNext("So you want to go to the Hallowed Ground? Oh yeah, we discovered a new Hallowed Ground. I hear the"
                     "key to the Cygnus Garden can be found there. Please continue doing your best to bring peace to our world.\r\n#b"
                     "#L0#Hallowed Ground of Dawn#l\r\n"
                     "#L1#Hallowed Ground of Blaze#l\r\n"
                     "#L2#Hallowed Ground of Wind#l\r\n"
                     "#L3#Hallowed Ground of Night#l\r\n"
                     "#L4#Hallowed Ground of Thunder#l\r\n")

if answer == 0:
    sm.warpInstanceIn(271030201)

elif answer == 1:
    sm.warpInstanceIn(271030202)

elif answer == 2:
    sm.warpInstanceIn(271030203)

elif answer == 3:
    sm.warpInstanceIn(271030204)

elif answer == 4:
    sm.warpInstanceIn(271030205)
