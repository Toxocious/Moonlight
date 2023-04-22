if sm.getShipLevel() == 30:
    sm.deductShipExp(sm.getShipExp())
    sm.dispose()
if sm.getShipExp() > 100:
    sm.deductShipExp(100)
    sm.giveShipLevel(1)
    sm.playSound("profession/levelup")
    sm.dispose()
else: sm.dispose()