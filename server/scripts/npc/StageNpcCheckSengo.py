# Oda Nobunaga | Event Hall

SchoolDojo = 744000020

if sm.getFieldID() == SchoolDojo:

    selection = sm.sendNext("Are you ready to leave this place?\r\n#b"
                            "#L0#Leave this place.#l\r\n")

    if selection == 0: # Leave this place
        sm.warp(910000000) # Sengoku High

elif sm.sendAskYesNo("You dare wish to challenge my school?\r\n#bDo you wish to be teleported to Sengoku High?"):
     sm.warp(744000020)
