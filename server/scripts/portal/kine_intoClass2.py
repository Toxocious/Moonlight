# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor
if sm.hasQuest(22728):
    sm.warpInstanceIn(331002400)
else:
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendSayOkay("I don't need to check this classroom.")