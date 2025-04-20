# Portal in Field: Momijigaoka : Momijigahara 2 (807020100)
# Used for Kanna's quest -> Honnou-ji Infiltration 1 & 2 (57436, 57437)
# Author: Tiger

if sm.hasQuest(57436) or sm.hasQuest(57437): # Honnou-ji Infiltration 1 & 2
    sm.warpInstanceIn(807050200) # Honnou-ji : Honnou-ji Eastern Wall
else:
    chr.chatMessage("You may not enter right now.")
