from net.swordie.ms.constants import TipConstants

if sm.sendNext:
    selection = sm.sendNext("Hello I'm the Information Board. What would you like to know?\r\n\r\nYou have been online for " + str(sm.getOnlineTimeInHours()) + "\r\n"
                            "#L0##rSeasonal Information\r\n"
                            "#L1#Seasonal Leaderboards\r\n"
                            "#L2#Server Tips\r\n"
                            "#L4##n#bItem Enhancement Costs\r\n"
                            "#L5#Superior Item Enhancement Costs\r\n"
                            "#L6#Special Item Information\r\n"
                            "#L7#Boss Information\r\n"
                            "#L8#Pierce Information\r\n"
                            "#L9#Acheivement Information\r\n")
    if selection == 0:

        sm.sendSayOkay("#eSeason 1 will begin on June 1st 2020#n\r\n\r\nSeasons will occur in the same world doing this will prevent all wipes so the server will never have to wipe and the player can choose to continue playing their Non-Seasonal Characters without fear of it ever being wiped."
                       "\r\n\r\nSeasonal characters will have a different interaction with Non-Seasonal Characters to prevent gaining items and leeching from Non-Seasonal Characters.\r\n\r\n#eThis List Below Shows All Changed Interactions Between Seasonal and Non Seasonal.\r\n\r\n"
                       "\r\n\r\n#bYou will not be able to Trade with Non-Seasonal Characters.\r\n\r\nYou will not see items dropped by Non-Seasonal Characters.\r\n\r\nYou will not be able to loot items from monsters that Non-Seasonal Characters have killed.\r\n\r\nYou will only be allowed to trade NX Equips with Non-Seasonal Characters.\r\n\r\n"
                       "You will not have a shared storage or cash shop inventory with your Non-Seasonal Characters.\r\n\r\nYou will not be able to join parties of Non-Seasonal Characters.\r\n\r\n"
                       "You will gain items at the end of the season for Beating the game I.E. Completing CWKPQ.\r\n\r\nAt the end of the season your Characters will become Non-Seasonal Characters thus making it able to interact with other Non-Seasonal characters.\r\n\r\n"
                       "You will have a icon next to your name in chat indicating you are a Seasonal Character.\r\n\r\nCannot Use link skills from Non-Seasonal Characters on your account.\r\n\r\nYou may choose to make your characters Seasonal or Non-Seasonal in the character creation when making new characters.\r\n\r\n"
                       "Seasonal Characters will not be able to enter CWKPQ with Non-Seasonal Character parties.\r\n\r\nSeasonal Characters will not share the same Achievements and Pierce stats as their Non-Seasonal Characters.\r\n\r\n"
                       "The only things you may use from your Non-Seasonal Characters are as follows. Donation Points, Vote Points and NX Equips.")
        sm.dispose()

    if selection == 1:

        sm.sendSayOkay("#eSeasonal Leaderboards will begin on June 1st 2020.")
        sm.dispose()

    if selection == 2:
        # tip = ""
        # # prefix
        # for tips in TipConstants.tips:
        #     tip_len = len(tips)
        #     if tip_len % 2 == 0:
        #         prefix = "#b"
        #     else:
        #         prefix = ""
        #     tip += prefix + tips + "#k\r\n\r\n"
        #     sm.sendSayOkay(str(tip))
        sm.sendSayOkay("Information to be added.")
        sm.dispose()

    if selection == 3:
        sm.sendSayOkay("Information to be added.")
        sm.dispose()

    if selection == 4:
        sm.sendSayOkay("Information to be added.")
        sm.dispose()

    if selection == 5:
        equipmentLevel = sm.sendAskNumber("Enter the Equipment's Level.", 1, 0, 255)
        mesoCost = pow(equipmentLevel, 3.2394)
        mesoCost = "{0:,.0f}".format(mesoCost)
        sm.sendSay("Rank  |    Success %    |    Destruction %    |    Meso    |    NX\r\n\r\n"
                   " 1    |    50%    |       0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 2    |    50%    |       0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 3    |    45%    |       0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 4    |    40%    |       0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 5    |    40%    |       0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 6    |    40%    |     1.8%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 7    |    40%    |     3.0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 8    |    40%    |     4.2%    |    "+mesoCost+"    |    1,170,000\r\n"
                   " 9    |    40%    |     6.0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   "10    |    37%    |     9.5%    |    "+mesoCost+"    |    1,170,000\r\n"
                   "11    |    35%    |    13.0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   "12    |    35%    |    16.2%    |    "+mesoCost+"    |    1,170,000\r\n"
                   "13    |     3%    |    48.5%    |    "+mesoCost+"    |    1,170,000\r\n"
                   "14    |     2%    |    49.0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   "15    |     1%    |    50.0%    |    "+mesoCost+"    |    1,170,000\r\n"
                   )
        sm.sendSayOkay("Information to be added.")
        sm.dispose()

    if selection == 6:

        sm.sendSayOkay("Information to be added.")
        sm.dispose()

    if selection == 7:

        sm.sendSayOkay("Information to be added.")
        sm.dispose()

    if selection == 8:

        sm.sendSayOkay("Information to be added.")
        sm.dispose()

    if selection == 9:

        sm.sendSayOkay("Information to be added.")
        sm.dispose()
