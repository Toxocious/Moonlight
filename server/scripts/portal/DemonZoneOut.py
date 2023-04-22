# Exit Blackgate City
if not sm.getReturnField() is None:
    sm.warp(sm.getReturnField())
else:
    sm.warp(100000000, 19)