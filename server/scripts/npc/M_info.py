if sm.getFieldID() == 993073000:
    sm.sendSayOkay("No")
else:
    sm.setReturnField(chr.getFieldID())
    options = ["Warp me!"]

    options2 = ["Town Maps","Free Market","Boss Entrances","Jump Quests"]

    maps = [
    [
        300000000, 680000000, 230000000, 910001000, 260000000, 541000000, 540000000, 211060010,
        105300000, 310000000, 211000000, 101072000, 101000000, 101050000, 130000000, 820000000, 223000000, 410000000,
        141000000, 120040000, 209000000, 310070000, 401000000, 100000000, 271010000, 251000000, 744000000, 551000000,
        103000000, 224000000, 241000000, 240000000, 104000000, 220000000, 150000000, 261000000, 701220000, 807000000,
        701210000, 250000000, 800000000, 600000000, 120000000, 200000000, 800040000, 400000000, 102000000, 914040000,
        865000000, 801000000, 105000000, 866190000, 270000000, 273000000, 701100000, 320000000
    ], # Town Maps

    [
        910000000
    ], # Free Market

    [
        [120040000, "Black Bean"], [105100100, "Balrog"],
        [105200000, "Root Abyss"], [211070000, "Von Leon"], [272020110, "Arkarium"], [401000001, "Easy Magnus"],
        [401060000, "Normal/Hard Magnus"], [270050000, "Pink Bean"], [271040000, "Cygnus"],
        [211041700, "Ranmaru"], [105300303, "Damien"], [992000000, "Dorothy"], [450007240, "Will"]
    ],

    [
               280020000, 910130000, 220000006, 100000202, 921110000, 992017000, 910360000
    ], #Jump Quests

    ]

    list = "I can warp you to any #bTown, #dBoss#k or #rJump Quest#k !"
    i = 0
    while i < len(options):
        list += "\r\n#b#L" +str(i)+ "#" + str(options[i])
        i += 1
    i = 0
    option = sm.sendNext(list)
    if option == 0: # I want to go somewhere (maps)
        list = "Where would you like to go? "
        while i < len(options2):
            list += "\r\n#b#L" +str(i)+ "#" + str(options2[i])
            i += 1
        i = 0
        ans1 = sm.sendNext(list)
        list = "These are your options: "
        if ans1 == 2: # boss maps
            while i < len(maps[ans1]):
                list += "\r\n#L" + str(i) + "##b" + str(maps[ans1][i][1])
                i += 1
        else: # town/monster maps
            while i < len(maps[ans1]):
                list += "\r\n#L" + str(i) + "##b#m" + str(maps[ans1][i]) + "#"
                i += 1
        ans2 = sm.sendNext(list)
        if ans1 == 2: # boss maps
            sm.warp(maps[ans1][ans2][0], 1)
        else:
            sm.warp(maps[ans1][ans2], 0)

    else:
        sm.sendSayOkay("This option currently is uncoded.")