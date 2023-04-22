maps = [104000000, 100000000, 103000000, 101000000, 102000000]

selection = sm.sendNext("Where would you like to go? \r\n#L0#Lith Harbor#l\r\n#L1#Henesys#l\r\n#L2#Kerning City#l"
                        + "\r\n#L3#Ellinia#l\r\n#L4#Perion#l")

sm.warp(maps[selection], 0)
