# 921160700 - Escape! - PQ

if sm.hasMobsInField():
    sm.chat("The portal is not opened.")
elif sm.getFieldID() == 921160700:
    sm.warpInstanceOut(910002000) # Party Quest Map
    sm.giveExp(sm.getPQExp()) #Gives player PQ exp
    sm.giveNX(400000)
    sm.giveItem(4310212, 2)
else:
    dialog = "Are you sure you want to leave?"
    if sm.sendAskYesNo(dialog):
        sm.warpInstanceOut(910002000)
sm.dispose()
