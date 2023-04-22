# Selective 8 Slot Coupon
invs = [
    # not sure about the inv id
    ["Equip", 1],
    ["Use", 2],
    ["Set-up", 3],
    ["Etc", 4]
]
text = "I can use the Slot Coupon to increase it by #b#e8 slots#n#k. Which slots do you want to add? Ook?\r\n"
i = 0
while i < len(invs):
    text += "\r\n#L" + str(i) + "#" + str(invs[i][0]) + "#l"
    i += 1
text += "\r\n#L4# #eLemme think about it some more.#n#l"
answer = sm.sendNext(text)

if answer >= 4:
    sm.sendSayOkay("It will disappear when the #e#rduration#n#k runs out, so make sure to use it before then.")
    sm.dispose()
else:
    inv = invs[answer][0]
    if sm.sendAskYesNo(inv + " inventory can be expanded by #r8 slots#k. Add more " + inv + " slots? "):
        invSlots = 48
        sm.sendSayOkay("Your "+ inv +" inventory has been expanded to " + str(invSlots) +" slots.")
        # TODO: handle slot expand.
    else:
        sm.sendNext("Use it again if you want to add more slots.")