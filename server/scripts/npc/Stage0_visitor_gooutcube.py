# OSSS Researcher (9250155) | Eastern Besieged Henesys

DamageTest = 910080010

if sm.getFieldID() == DamageTest:
    selection = sm.sendNext("Are you ready to leave this place?\r\n#b"
                "#L0#I'd like to test my damage.#l\r\n"
                "#L1#Leave this place.#l\r\n")

    if selection == 0: # I'd like to test my damage.
        sm.sendSayOkay("Not Coded Yet.")

    elif selection == 1: # Leave this place
        sm.warpInstanceOut(925020001) # Dojo Hall

elif sm.sendAskYesNo("Would you like to test your damage?"):
        sm.warpInstanceIn(910080010) # Eastern Besieged Henesys
