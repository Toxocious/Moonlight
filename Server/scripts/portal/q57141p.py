# Portal in Field: Honnou-ji : Honnou-ji Eastern Wall (807050200)
# Used for Kanna's Quest: Honnou-ji Infiltration 2 (57437)
# Author: Tiger

if sm.hasQuest(57437): # Honnou-ji Infiltration 2
    if sm.getFieldID() == 807050204: # Honnou-ji : Honnou-ji Eastern Wall
        sm.warpInstanceOut(807020100, 0)
    else:
        sm.warpInstanceIn(807050201, 0) #  Honnou-ji : Honnou-ji Eastern Wall Exterior
else:
    chr.chatMessage("You may not enter at this moment.")
