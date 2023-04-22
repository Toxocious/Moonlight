if sm.getFieldID() == 211000000:
    map = [211060000, 211040200, 211041400, 300000100]
    selection = sm.sendNext("Where would you like to go?"
                "\r\n#L0#Desolate Moor#l"
                "\r\n#L1#Ice Valley II#l"
                "\r\n#L2#Forest of Dead Trees IV#l"
                "\r\n#L3#Small Forest#l")
    sm.warp(map[selection], 0)
    sm.dispose()

elif sm.getFieldID() == 300000100:
    map = [211000000, 220000000]
    selection = sm.sendNext("Where would you like to go?"
                "\r\n#L0#El Nath#l"
                "\r\n#L1#Ludibrium#l")
    sm.warp(map[selection], 0)
    sm.dispose()

elif sm.getFieldID() == 220000000:
    map = [220050300, 300000100]
    selection = sm.sendNext("Where would you like to go?"
                "\r\n#L0#Path of Time#l"
                "\r\n#L1#Small Forest#l")
    sm.warp(map[selection], 0)
    sm.dispose()

elif sm.getFieldID() == 240000000:
    map = [240030000, 240040500]
    selection = sm.sendNext("Where would you like to go?"
                "\r\n#L0#Entrance to Dragon Forest#l"
                "\r\n#L1#Entrance to Dragon Nest#l")
    sm.warp(map[selection], 0)
    sm.dispose()

else:
    currentMap = sm.getFieldID()

if currentMap == 220050300:
    map = 220000000
elif currentMap == 105030000:
    map = 105000000
elif currentMap == 105000000:
    map = 105030000
elif currentMap == 211060000:
    map = 211000000
response = sm.sendAskYesNo("Would you like to go to #m" + str(map) + "m#?")
if response:
    sm.warp(map, 0)
