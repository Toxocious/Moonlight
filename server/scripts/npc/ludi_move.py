# Eos Magic Rock (2040024) | Ludibrium Eos Tower

maps =  [
221023200, # Eos Tower F100
221022300, # Eos Tower F91
221022100, # Eos Tower F70
221021700, # Eos Tower F66
221021500, # Eos Tower F35
221021100, # Eos Tower F31
221020900, # Eos Tower F10
221020000  # Eos Tower F1
]

mapString = "Where do you want to go?\r\n\r\n#b"
i = 0
while i < len(maps):
    if maps[i] == sm.getFieldID():
        i += 1
        continue
    else:
        mapString += "#L"+ str(i) +"##m"+ str(maps[i]) +"##l\r\n"
    i += 1
answer = sm.sendNext(mapString)

sm.warp(maps[answer], 0)
