Sengoku = 744000020
sm.setSpeakerID(9330279)

if sm.getFieldID() == Sengoku:
    selection = sm.sendNext("You dare wish challenge Sengoku High?.\r\n#b"
                            "#L0#I want to challenge Sengoku High.#l\r\n")
    if selection == 0:
        if not sm.getParty():
            sm.sendSayOkay("You must leave your party first.")
        else:
            sm.warpInstanceIn(744000041)
