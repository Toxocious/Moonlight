# 925100400 - Fifth Map of the Lord Pirate PQ
if sm.getReactorQuantity() > 1:
    sm.chat("The portal is not opened.")
else:
    sm.warpInstanceIn(sm.getFieldID() + 100, 0, True) #Boss Map of Lord Pirate PQ
