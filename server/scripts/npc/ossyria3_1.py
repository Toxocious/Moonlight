# Orbis Magic Spot (2012014) | Orbis Tower <20th/16th/8th/1st Floor>

maps =  [
        200080200, # Orbis Tower <20th Floor>
        200080600, # Orbis Tower <16th Floor>
        200081400, # Orbis Tower <8th Floor>
        200082100  # Orbis Tower <1st Floor>
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
