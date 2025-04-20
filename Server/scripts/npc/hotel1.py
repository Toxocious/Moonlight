# Hotel Receptionist (1061100) | Sleepywood Hotel


array = [#  Name,  MesoCost,   Map ID
        ["Regular", 499, 105000011],
        ["VIP", 999, 105000012]
    ]

sm.sendNext("Welcome. We're the Sleepywood Hotel. "
            "Our hotel works hard to serve you the best at all times. "
            "If you are tired and worn out from hunting, how about a relaxing stay at our hotel?")


selection = sm.sendNext("We offer two kinds of rooms for our service. "
            "Please choose the one of your liking.\r\n#b"
            "#L0#"+ array[0][0] +" sauna (" + str(array[0][1]) + " mesos)#l\r\n"
            "#L1#"+ array[1][0] +" sauna (" + str(array[1][1]) + " mesos)#l")

if selection == 0:
    response = sm.sendAskYesNo("You have chosen the regular sauna. \r\n"
                    "Your HP and MP will recover fast and you can even purchase some items there. "
                    "Are you sure you want to go in?")
elif selection == 1:
    response = sm.sendAskYesNo("You've chosen the VIP sauna. \r\n"
                    "Your HP and MP will recover even faster than that of the regular sauna and you can even find a special item in there. "
                    "Are you sure you want to go in?")

if sm.getMesos() < array[selection][1]:
    sm.sendSayOkay("I'm sorry. It looks like you don't have enough mesos. It will cost you at least " + str(array[selection][1]) + " mesos to stay at our "+ array[selection][0] +" sauna.")
else:
    sm.warp(array[selection][2], 0)
    sm.deductMesos(array[selection][1])
