

# Vote Item NPC \\ Cygnus \\ 9010034 \\ Free Market

VoteShop = { #[itemid, price, expiration time]
    0 : [5211067, 5, 24],
    1 : [5211068, 2, 1],
    2 : [5360042, 1, 2],
    3 : [5211068, 5, 4],
    4 : [5360042, 2, 4],
    5 : [5211068, 7, 12],
    6 : [5360042, 3, 6],
    7 : [5211060, 20, 24],
    8 : [5211046, 10, 3],
    9 : [5050100, 10, 0],
    10 : [5051001, 5, 0],
    11 : [2023604, 4, 0],
    12 : [2023380, 5, 0],
    13 : [1122171, 6, 12],
    14 : [1122219, 5, 72],
    15 : [1122219, 10, 168],
    16 : [2022035, 4, 24],

}

votePrice = 1
votecoinId = 4310195
secondaryPendantPrice = 20

if sm.sendNext:
    selection = sm.sendNext("Hello my name is Cygnus, This shop is where you can spend your #bVote Points#k.\r\n"
                            "\r\n#eYou have#r " + str(sm.getVotePoints()) + " #bVote Points.\r\n#n#b"
                            "#L0#I'd like to my Trade Vote Points for Vote Coins\r\n"
                            "#L1#I'd like to my Trade Vote Coins for Vote Points\r\n"
                            "#L2#I'd like to purchase items from the Vote Shop\r\n"
                            "#L3#I'd like to purchase a 7 Day Pendant Slot (Character)\r\n")

    items = []
    if selection == 0:
        amount = sm.sendAskNumber("#b#eA Vote Coin costs #r(1)#b Vote Point.\r\n"
                                  "You currently have #e#r" + str(sm.getVotePoints()) + " #bVote Points\r\n"
                                                                                            "#k#eHow many would you like exchange?", 1, 1, 100)
        price = amount * votePrice
        if sm.getVotePoints() >= price and chr.canHold(votecoinId, amount):
            sm.deductVotePoints(price)
            sm.giveItem(votecoinId, amount)
            sm.dispose()
        else:
            sm.sendNext("You do not have enough #bVote points#k or you do not have room in your inventory for this item.")
            sm.dispose()

    elif selection == 1:
        amount = sm.sendAskNumber("#b#eA Vote Point costs #r(1)#b Vote Coin.\r\n"
                                  "You currently have #r" + str(sm.getQuantityOfItem(votecoinId)) + " #bVote Coins.\r\n"
                                                                                                 "#kHow many would you like exchange?", 1, 1, 100)
        if sm.getQuantityOfItem(votecoinId) >= amount:
            sm.deductVotePoints(votePrice * amount)
            chr.consumeItem(votecoinId, amount);
            sm.dispose()
        else:
            sm.sendNext("You do not have enough Vote Coins for this exchange.")
            sm.dispose()

    elif selection == 2:
        items = VoteShop
    elif selection == 3:
        answer = sm.sendAskYesNo("Are you sure you want to purchase a permanent secondary pendant slot for #r" + str(secondaryPendantPrice) + "#b Vote Points#k.")
        if answer and sm.getVotePoints() >= secondaryPendantPrice:
            if sm.setSecondaryPendantDateInXDays(7):
                sm.deductVotePoints(secondaryPendantPrice)
                sm.sendSayOkay("Please re-log for your secondary pendant slot to take effect.")
                sm.dispose()
            else:
                sm.sendSayOkay("You already own a secondary pendant slot")
                sm.dispose()
        else:
            sm.sendNext("You do not have enough #bVote Points#k.")
            sm.dispose()

 #   output = "Choose the item you would like to buy \r\n"
  #  for x in range (len(items)):
   #     output += "#b" + su.addSelectItem(x) + su.getItemImg(items[x][0]) + "   " + su.getItemName(items[x][0]) + " #r#e(" + str(items[x][1]) + ") VP#n"
    #    if items[x][2] >= 24:
     #       output += " for " + str(items[x][2] / 24) + " Day(s)"
      #  elif items[x][2] >= 1:
       #     output += " for " + str(items[x][2] / 1) + " Hour(s)"
       # output += "\r\n"
    #selection = sm.sendNext(output)
    #itemId = items[selection][0]
    #text = "how many #r" + su.getItemName(itemId) + " #bwould you like to buy?"
    #amount = sm.sendAskNumber(text, 1, 1, 100)
    #totalPrice = items[selection][1] * amount
    #answer = sm.sendAskYesNo("are you sure you wanna buy " + str(amount) + " #b" + su.getItemName(itemId) + " #kfor #r(" + str(totalPrice) + ") VP")
    #if answer and sm.canHold(itemId, amount) and sm.getVotePoints() >= totalPrice:
     #   sm.giveItem(itemId, amount, items[selection][2])
      #  sm.deductVotePoints(totalPrice)
       # chr.checkAndRemoveExpiredItems();
    #else:
     #   sm.sendNext("You do not have enough Vote Points or cannot hold this item")
