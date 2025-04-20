# 271030600
sm.setSpeakerID(2143000)
if not sm.hasItem(4032923):
    sm.sendSayOkay("You do not possess a #bDream Key")
else:
    sm.chatRed("You have lost your Dream Key")
    sm.consumeItem(4032923)
    sm.warp(271040000, 2)
    sm.dispose()
