# author @Manu13
# Description: Script GMS-Like to receive the Pearl Maple Armor Set

from net.swordie.ms.scripts import ScriptUtil
su = ScriptUtil()
ITEMS = [
    (1003864, "Pearl Maple Hat"),  # 01003864
    (1052613, "Pearl Maple Suit"),  # 01052613 (overall)
    (1102563, "Pearl Maple Cape"),  # 01102563
    (1012377, "Pearl Maple Gum"),  # 01012377
    (1122253, "Pearl Maple Pendant"),  # 01122253
    (1132229, "Pearl Maple Buckle"),  # 01132229 (belt)
]


def _format_list(entries):
    text = ""
    for e in entries:
        text += " - " + e + "\r\n"
    return text


def use():
    # 1) check duplicates
    already_owned = []
    for iid, name in ITEMS:
        if sm.hasItem(iid):
            already_owned.append(name)
    if already_owned:
        sm.sendSayOkay(
            "You already own the following item(s):\r\n" +
            _format_list(already_owned) +
            "\r\nPlease clear them first before opening this box."
        )
        sm.dispose()
        return

    # 2) check inventory capacity
    cannot_hold = []
    for iid, name in ITEMS:
        if not sm.canHold(iid):
            cannot_hold.append(name)
    if cannot_hold:
        sm.sendSayOkay(
            "You do not have enough inventory space for:\r\n" +
            _format_list(cannot_hold) +
            "\r\nPlease make space and try again."
        )
        sm.dispose()
        return

    # 3) confirmation
    all_items_text = ""
    for id, name in ITEMS:
        all_items_text += " - " + su.getItemFull(id) + "\r\n"
    if not sm.sendAskYesNo(
            "Open this box and receive the following items?\r\n\r\n" + all_items_text
    ):
        sm.dispose()
        return

    # 4) deliver all items
    for iid, _ in ITEMS:
        sm.giveItem(iid, 1)

    # 5) consume the box
    sm.consumeItem()

    sm.sendSayOkay("Enjoy your Pearl Maple Armor Set!")
    sm.dispose()


use()
