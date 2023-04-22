# Used in Party Quest - Escape
if sm.hasMobsInField():
    sm.warp(921160400, 0) # A secret Door to the Aerial Prison
else:
    sm.chat("Please eliminate all mobs.")
sm.dispose()
