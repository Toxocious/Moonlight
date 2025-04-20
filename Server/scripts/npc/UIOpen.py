# Medina (2183003) | Azwan Refuge Zone : Azwan

selection = sm.sendNext("#e<Fight for Azwan>#n \r\n \r\n Can you handle the rest of Hilla's forces in Azwan? \r\n \r\n \r\n#L0##bGo clear out whoever's left.#l \r\n #L1#Face Hilla herself(Level 120 and above).#l")
if selection == 0:
    if sm.getChr().getLevel() < 120:
        sm.sendSayOkay("You have to be above level 120 to participate in the fight for Azwan")
        sm.dispose()
    else:
        sm.warp(262000300, 0)
        sm.dispose()
elif selection == 1:
    if sm.getChr().getLevel() < 120:
        sm.sendSayOkay("You have to be above level 120 to participate in Hilla")
        sm.dispose()
    else:
        sm.warp(262030000, 0)
        sm.dispose()