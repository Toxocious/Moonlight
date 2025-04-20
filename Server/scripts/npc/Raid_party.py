# Vote Points & Donation Points NPC

# Format to follow for items
# ItemID, Quantity/Duration, Cost, Time-sensitive (0 : false | 1 : enabled)

# ===========================================
# VP Items
# ===========================================

vp_exp = [
    [5360042, 240, 4, 1],
    [5360000, 1440, 8, 1],
    [2022463, 30, 2, 1],
    [2022461, 30, 2, 1],
    [5211048, 240, 4, 1],
    [5211046, 1440, 8, 1],
    [2023380, 30, 4, 1]
]

vp_cosmetics = [
    [5062400, 1, 1, 0],
	[2430182, 50, 2, 0],
	[2210010, 8, 3, 0],
	[5072000, 20, 1, 0],
	[5072000, 20, 1, 0],
	[5073000, 20, 1, 0],
	[5076000, 20, 3, 0],
	[5077000, 20, 3, 0]
]

vp_game_changers = [
	[2435383, 9999999, 24, 0],
	[1202236, 240, 2, 1],
	[5680729, 1, 8, 0],
	[1202089, 10080, 8, 1],
    [1202090, 10080, 8, 1],
    [1202091, 1080, 8, 1]
]

vp_pet_shop = [
    [5190000, 1, 1, 0],
    [5190001, 1, 1, 0],
    [5190006, 1, 1, 0],
    [5190009, 1, 1, 0],
    [5190010, 1, 1, 0],
    [5190011, 1, 1, 0],
    [5190002, 1, 1, 0],
    [5190003, 1, 1, 0],
    [5190004, 1, 1, 0],
    [5190005, 1, 1, 0]
]

# ===========================================
# DP Items
# ===========================================

# Format to follow for items
# ItemID, Quantity/Duration, Cost, Time-sensitive (0 : false | 1 : enabled)

dp_exp = [
    [2450015, 30, 250, 0],  # 3x Exp 30 Min
    [2450016, 60, 400, 0], # 3x Exp 60 Min
    [2023722, 30, 250, 0],  # 2x Meso Buff 30 min
]

dp_cosmetics = [
    [5552000, 1, 200, 0],  # Hair Slot
    [5553000, 1, 200, 0],  # Face Slot
	[5155000, 1, 1000, 0], # Carta's Indigo Pearl
	[5155004, 1, 1000, 0], # Carta's Teal Pearl
	[5155005, 1, 1000, 0], # Carta's Scarlet Pearl
]

# TODO: wz edit pet-vacs string to change names
# and have 2 different pvacs one for 1 day and a 2nd for 7 days.

dp_game_changers = [
    [5680047, 1, 100, 1],  # Pvac // 1 Day (time-sensitive)
    [5680047, 1, 600, 1],  # Pvac // 7 Day (time-sensitive)
    [1202236, 1, 8000, 0],  # Frenzy Totem
    [4034803, 1, 1000, 0],  # Name Change Coupon
    [2435383, 1, 4000, 0], # Pendant Slot Permanent Coupon
	[1122303, 1, 1000, 0], # Hellia Necklace
	[1132183, 1, 1000, 0], # Avenger Quiver Belt
	[1152101, 1, 1000, 0], # Doom Shoulder
	[1113171, 1, 1000, 0]  # Grin Ring
]


dp_surprise_box = [
    [5068300, 1, 250, 0], # Pet Box
	[2435163, 1, 300, 0], # Random Damage Skin
	[5190013, 1, 1000, 0] # Open Pet Shop Skill
]

# ===============================================

main_menu = sm.options("Trade in Vote Points", "Trade in Donation Points")

# Options for vote point menu
vp_menu = sm.options("Exp / Drop Coupons", "Cosmetics", "Game Changers", "Pet Items")
# Options for donation point menu
dp_menu = sm.options("Exp / Drop Coupons", "Cosmetics", "Game Changers", "Surprise Box")

# for sub-menu item options
option = ""
def showOptions(text, items, duration):
    option = text + "\r\n#b"
    for x in range (len(items)):
        name = items[x][0]
        qty  = items[x][1]
        cost = items[x][2]
        if duration:
            option += "#L" + str(x) + "##i" + str(name) + "# #z" + str(name) + "# (" + str(qty) + " Min)" + " (" + str(cost) + " Points)" + "#l \r\n"
        else:
            option += "#L" + str(x) + "##i" + str(name) + "# #z" + str(name) + "# (" + str(qty) + ")" + " (" + str(cost) + " Points)" + "#l \r\n"

    return sm.sendNext(option)

def exchange(opt, items, duration, donation):
    name = items[opt][0]
    qty  = items[opt][1]
    cost = items[opt][2]
    timed = items[opt][3]

    currency = sm.getVotePoints()
    currencyName = "vote points"
    if donation:
        currency = sm.getDonationPoints()
        currencyName = "donation points"

    durOrQty = ""
    if duration:
        durOrQty = "(#b" + str(qty) + " min#k)"
    else:
        durOrQty = "(#b" + str(qty) + "#kx)"

    timeMsg = ""
    if timed == 1:
        timeMsg = "\r\n\r\n(#rThis is a time-sensitive item, duration until expire will start as soon as item is in your inventory!!#k)"

    if sm.sendAskYesNo("You currently have #b" + str(currency) + " " + currencyName + "#k.\r\nAre you sure you want the following item(s)?:\r\n " + durOrQty + " of #b#z " + str(name) + "##k #i" + str(name) + "# for #r" + str(cost) + "#k " + currencyName + "?" + timeMsg):
            if currency >= cost:
                if sm.canHold(name):
                    if duration:
                        if timed == 1: # is time sensitive
                            sm.giveItemWithExpireDate(name, 1, False, qty)
                        else:
                            sm.giveItem(name, 1)
                    else:
                        if timed == 1:
                            sm.giveItemWithExpireDate(name, 1, False, qty)
                        else:
                            sm.giveItem(name, qty)

                    if donation: # is donation points
                        sm.deductDonationPoints(cost)
                    else:
                        sm.deductVotePoints(cost)
                    sm.sendSayOkay("You have obtained " + durOrQty + " #b#z" + str(name) + "##k for #r" + str(cost) + "#k " + currencyName + ".")
                else:
                    sm.sendNext("Please make sure you have enough space in your inventory")
            else:
                sm.sendNext("You don't have enough " + currencyName + ". You need #r" + str(cost) + "#k " + currencyName + ".")


def showAndExchange(msg, items, has_duration, donation):
     selection = showOptions(msg, items, has_duration)
     exchange(selection, items, has_duration, donation)

# =========================== Vote Points =========================================================

def votePointOptions():
    type = False
    prompt = "You currently have #b" + str(sm.getVotePoints()) + " vote points#k.\r\nWhat would you like to buy with your vote points?\r\n\r\n(#dYou can obtain vote points by voting for us every 12 hours through our website or discord#k!)\r\n"
    selection = sm.sendNext(prompt + "#b" + vp_menu + "#k")

    if selection == 0:
        showAndExchange("What would you like from the Exp / Drop coupon shop?", vp_exp, True, type) # items have have a duration
    elif selection == 1:
        showAndExchange("What would you like from the Cosmetics shop?", vp_cosmetics, False, type) # items don't have a duration
    elif selection == 2:
        showAndExchange("What would you like from the Game Changers shop?", vp_game_changers, True, type)
    elif selection == 3:
        showAndExchange("What would you like from the Pet shop?", vp_pet_shop, False, type)


# =========================== Donation Points ======================================================

def donationPointOptions():
    type = True # is DP
    prompt = "You currently have #b" + str(sm.getDonationPoints()) + " donation points#k.\r\nWhat would you like to buy with your donation points?\r\n\r\n(#dYou can obtain donation points by visiting our website and purchasing them by clicking on the store#k.)\r\n"
    selection = sm.sendNext(prompt + "#b" + dp_menu + "#k")

    if selection == 0:
        showAndExchange("What would you like from the Exp / Drop coupon shop?", dp_exp, True, type) # items have have a duration
    elif selection == 1:
        showAndExchange("What would you like from the Cosmetics shop?", dp_cosmetics, False, type) # items don't have a duration
    elif selection == 2:
        showAndExchange("What would you like from the Game Changers shop?", dp_game_changers, True, type)
    elif selection == 3:
        showAndExchange("What would you like from the Surprise Box shop?", dp_surprise_box, False, type)

# ===================================================================================================

selection = sm.sendNext("Hey! What would you have me do?\r\n#b" + main_menu + "#k")
if selection:
    donationPointOptions()
else:
	votePointOptions()