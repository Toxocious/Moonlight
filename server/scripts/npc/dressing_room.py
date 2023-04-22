# Dressing Room NPC

equip_type = 0
equip_page = 0
page_limit = 100
expire_time = 5

# flags
flag_main = 1
flag_browse = 1
flag_search = 0
# select category
# select specific page
# go back to categories

# name, buy_cost, rent_cost
categories = [
    ["Hats",                 2, 0],
    ["Tops",                 2, 0],
    ["Bottoms",              2, 0],
    ["Overalls",             2, 0],
    ["Shoes",                2, 0],
    ["Gloves",               2, 0],
    ["Capes",                2, 0],
    ["Weapons",              2, 0],
    ["Face Accessories",     2, 0],
    ["Eye Accessories",      2, 0],
    ["Rings",                2, 0]
]

selected_item = 0

def prompt_main():
    text = "#eYou currently have #b" + str(sm.getVotePoints()) +" Vote Points#n\r\nPlease select a category\r\n"
    count = 0
    for category in categories:
        if count == 4:
          text += "\n#b#L" + repr(count) + "#" + str(category[0]) + "#l"
          text += "\t\t\t\t\t\t\t#e#r#L999#Search for an item#l#n\r\n"
        else:
          text += "\n#b#L" + repr(count) + "#" + str(category[0]) + "#l\r\n"
        count += 1
    return text

def prompt_equips(type, page, limit):
    equip_tuple = sm.getDressingRoomEquips(type, page, limit)

    size = equip_tuple.getLeft()
    equips = equip_tuple.getRight()

    fromIndex = page
    toIndex = page + limit

    if (toIndex > size):
        toIndex = size

    text = "Displaying " + repr(fromIndex) + " - " + repr(toIndex) + " of " + repr(size) + " " + categories[type][0] + " \r\n"
    for equip in equips:
        item_id = equip.getItemId()
        text += "#b#L" + repr(item_id) + "##v" + repr(item_id) + "# #z"  + repr(item_id) + "##l#k\r\n"
    text += "\r\n"

    if page != 0:
        text += "#b#L9998#Previous Page\r\n"

    if (toIndex) < size:
        text += "#b#L9999#Next Page#l\r\n"

    text += "#b#L10000#Back to Categories#l"

    return text

def prompt_options(item_id):
    selected_item = item_id
    text = (
        "You've selected #v{0}# #b#z{0}##k.\r\n\r\n"
        "Please select what you would like to do:\r\n"
        "#L1#Buy for #b{1}#k VP#l\r\n"
        "#L2#Rent for #b{2}#k VP#l\r\n\r\n"
        "#L0#Go back to the list#l"
    ).format(
        item_id,
        categories[sm.getDressingRoomEquipType(item_id)][1],
        categories[sm.getDressingRoomEquipType(item_id)][2]
    )

    return text

def prompt_search():
    text = (
    "Please enter the name of the item you want to search for:"
    )

    return text

def message_done(item_id, cost, rent):
    buy_text = "bought" if not rent else "rented"
    text = (
        "You've {0} #v{1}# #b#z{1}##k.\r\n\r\n"
        "#b{2}#k Vote Points will be deducted from your account.\r\n\r\n"
    ).format(buy_text, item_id, cost)

    if rent:
        text += "The item will expire in #b{0} minutes#k.".format(expire_time)

    return text


while flag_main == 1:
    selection_main = sm.sendNext(prompt_main())

    if selection_main != 999:
        flag_browse = 1
        while flag_browse == 1:
            selection_equips = sm.sendNext(prompt_equips(selection_main, equip_page, page_limit))
            if selection_equips == 9998: # previous
                equip_page -= page_limit
            elif selection_equips == 9999: # next
                equip_page += page_limit
            elif selection_equips == 10000: # exit
                flag_browse = 0
            else:
                flag_browse = 0
                selection_options = sm.sendNext(prompt_options(selection_equips))
                item = selection_equips
                if selection_options == 0: # exit - return to list
                    flag_browse = 1
                elif selection_options != 0:
                    cost = categories[selection_main][selection_options]
                    if sm.getVotePoints() < cost:
                        sm.sendNext("You do not have enough vote points!")
                    else:
                        if selection_options == 1: # buy
                            sm.giveItem(item)
                            sm.deductVotePoints(cost)
                            sm.sendNext(message_done(item, cost, False))
                            flag_main = 0 # exit npc
                        elif selection_options == 2: # rent
                            sm.giveItemWithExpireDate(item, 1, False, 5)
                            sm.deductVotePoints(cost)
                            sm.sendNext(message_done(item, cost, True))
                            flag_main = 0 # exit npc
    elif selection_main == 999:
        flag_search = 1
        search_result = sm.sendAskText(prompt_search(), "", 0, 12)
        search_tuple = sm.getDressingRoomEquipsSearch(search_result)

        size = search_tuple.getLeft()
        equips = search_tuple.getRight()

        text_list = "Displaying {0} results for query '{1}'.\r\n".format(size, search_result)
        for id, string in equips.items():
            text_list += "#b#L" + repr(id) + "##v" + repr(id) + "# #z"  + repr(id) + "##l#k\r\n"

        if (size == 0):
            sm.sendNext("There were no results for '{0}'.".format(search_result))
        else:
            selection_equips = sm.sendNext(text_list)

            selection_options = sm.sendNext(prompt_options(selection_equips))
            item = selection_equips
            cost = categories[sm.getDressingRoomEquipType(selection_equips)][1]
            if selection_options == 0: # exit - return to list
                flag_browse = 1
            elif selection_options != 0:
                cost = categories[sm.getDressingRoomEquipType(selection_equips)][selection_options]
                if sm.getVotePoints() < cost:
                    sm.sendNext("You do not have enough vote points!")
                else:
                    if selection_options == 1: # buy
                        sm.giveItem(item)
                        sm.deductVotePoints(cost)
                        sm.sendNext(message_done(item, cost, False))
                        flag_main = 0 # exit npc
                    elif selection_options == 2: # rent
                        sm.giveItemWithExpireDate(item, 1, False, 5)
                        sm.deductVotePoints(cost)
                        sm.sendNext(message_done(item, cost, True))
                        flag_main = 0 # exit npc
