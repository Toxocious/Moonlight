map = sm.getReturnField()
if map == 0 or map == 910000000:
    sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
    map = 100000000
    portal = 0

if "910001000" in sm.getQRValue(9999):
    sm.setQRValue(9999, "")
    map = 910001000

sm.warpNoReturn(map, 2)