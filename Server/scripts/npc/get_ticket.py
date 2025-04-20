maps = {
    2012013 : [220000100, "Ludibrium"],
    1032008 : [200000100, "Orbis"],
    2012021 : [240000100, "Leafre"],
    2012001 : [104020110, "Victoria Island"],
    2041000 : [220000100, "Orbis"],
    2012025 : [260000100, "Ariant"],
    2102000 : [200000100, "Orbis"],
}

if parentID in maps:
    vals = maps[parentID]
    if sm.sendAskYesNo("Would you like to go to " + vals[1] + "?"):
        sm.warp(vals[0], 0)
else:
    sm.sendSayOkay("Not coded :(")


