#Snow Day Coin NPC

sm.setSpeakerID(9010040)

if sm.sendNext:
    selection = sm.sendNext("The snowdrop events are happening now!\r\n#b"
                            "#L0#Get Snowdrop Coins\r\n"
                            "#L1#Spend Snowdrop Coins\r\n"
                            "#L2#Spend Event Coins\r\n")

    if selection == 0:
        selection = sm.sendNext("Here you can find your progress regarding Snowdrop Coin daily challenges. Claim them all by simply clicking on one of the daily lines!\r\n" + chr.getAccount().getDailyStatusToNPC())
        if selection == 0:
            #chr.getAccount().completeDaily(0)
            chr.getAccount().claimDailies()
    if selection == 1:
        sm.invokeAfterDelay(1, "openShop", 9010040)
        sm.dispose()

    if selection == 2:
        sm.invokeAfterDelay(1, "openShop", 9010040)
        sm.dispose()