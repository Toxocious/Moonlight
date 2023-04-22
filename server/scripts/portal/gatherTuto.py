# Hidden Street - Ardentmill :: 910001000
Maps = [910001003, 910001004, 910001007, 910001009, 910001005, 910001006, 910001008, 910001010]
GRANT = 9031000
MINING_SKILL = 92010000
HERBALISM_SKILL = 92000000
sm.setSpeakerID(GRANT)


if sm.hasSkill(HERBALISM_SKILL) or sm.hasSkill(MINING_SKILL):
    text = "Where do you want to go?\r\n"
    if sm.hasSkill(HERBALISM_SKILL):
        for x in range(4):
            text += "#b#L" + str(x) + "##m" + str(Maps[x]) + "##l\r\n"
    if sm.hasSkill(MINING_SKILL):
        for i in range(4):
            text += "#b#L" + str(i + 4) + "##m" + str(Maps[i + 4]) + "##l\r\n"
    selection = sm.sendSay(text)

    sm.warp(Maps[selection], 1)
else:
    selection = sm.sendSay("Where do you want to go?\r\n\r\n#L0##bSaffron's Herb Field#k\r\n#L1##bCole's Mine#k")
    if selection == 0:
        sm.warp(910001001, 1)
    else:
        sm.warp(910001002, 1)