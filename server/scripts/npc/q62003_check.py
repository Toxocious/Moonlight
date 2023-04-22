CHIEF_PRIESTS_WOES = 62003

if sm.hasQuest(CHIEF_PRIESTS_WOES):
    sm.setPlayerAsSpeaker()
    if parentID == 9310585:
        if "check1" not in sm.getQRValue(CHIEF_PRIESTS_WOES):
            sm.addQRValue(CHIEF_PRIESTS_WOES, "check1")
        sm.sendNext("People must be making a lot of offerings. Look at that altar! It's so... grandiose!")

    elif parentID == 9310586:
        if "check2" not in sm.getQRValue(CHIEF_PRIESTS_WOES):
            sm.addQRValue(CHIEF_PRIESTS_WOES, "check2")
        sm.sendNext("That censer over there sure is releasing a lot of smoke. Is it haunted?")

    elif parentID == 9310587:
        if "check3" not in sm.getQRValue(CHIEF_PRIESTS_WOES):
            sm.addQRValue(CHIEF_PRIESTS_WOES, "check3")
        sm.sendNext("There's a lot of weird stuff coming out of that window. \r\n"
                    "Something terrible must be happening inside the depository.")

    if "check1" in sm.getQRValue(CHIEF_PRIESTS_WOES) and \
        "check2" in sm.getQRValue(CHIEF_PRIESTS_WOES) and \
        "check3" in sm.getQRValue(CHIEF_PRIESTS_WOES):

        sm.giveExp(1010226)
        sm.completeQuest(CHIEF_PRIESTS_WOES)