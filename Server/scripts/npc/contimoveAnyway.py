MAPS = [
    ["Victoria Island Station", 104020100],
    ["Ereve Sky Ferry", 130000210],
    ["Orbis Station", 200000100],
    ["Ludibrium Station", 220000100],
    ["Ariant Station", 260000100],
    ["Leafre Station", 240000100],
    ["#rEdelstein#k", 310000010]
]

text = "Welcome aboard. Please let me know where you would like to go. #b\r\n\r\n"
i = 0
while i < len(MAPS):
    text += "\r\n#L" + str(i) + "#" + str(MAPS[i][0]) + "#l"
    i += 1

answer = sm.sendNext(text)

if sm.sendAskYesNo("Would you like to go directly to " + str(MAPS[answer][0]) + "?"):
    sm.createQuestWithQRValue(25010, str(MAPS[answer][1]))
    sm.warp(150000001, 0)
    sm.dispose()
else:
    sm.sendNext("Ah, so you wish to go somewhere else. Please tell me your destination.")
    sm.dispose()