# Manji (1022002) | Perion
if sm.getChr().getLevel() < 50:
    sm.sendSayOkay("Leave now.. before you get hurt.")
else:
    if sm.sendAskYesNo("You appear strong. Would you like to head to the Balrog Temple?"):
        sm.warp(105100100, 0)