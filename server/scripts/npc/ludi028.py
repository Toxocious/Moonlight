# Weaver (2040032) | Ludibrium Pet Walkway


selection = sm.sendNext("Do you have any business with me?#b\r\n"
            "#L0#Please tell me about this place.#l\r\n"
            "#L1#I'm here through a word from Mar the Fairy...#l")

if selection == 0:
    if sm.hasItem(4031128):
        sm.sendNext("Jump over obstacles with your pet, and take that letter to my brother Trainer Neru. "
                    "Give him the letter and something good is going to happen to your pet.")
    else:
        if sm.sendAskYesNo("This is the road where you can go take a walk with your pet. "
                        "You can just walk around with it, or you can train your pet to go through the obstacles here. "
                        "If you aren't too close with your pet yet, that may present a problem and he will not follow your command as much... "
                        "\r\nSo, what do you think? Wanna train your pet?"):
            sm.giveItem(4031128)
            sm.sendSayOkay("Ok, here's the letter. "
                           "He wouldn't know I sent you if you just went there straight, "
                           "so go through the obstacles with your pet, go to the very top, and then talk to Trainer Neru to give him the letter. "
                           "It won't be hard if you pay attention to your pet while going through obstacles. "
                           "Good luck!")
elif selection == 1:
    sm.sendSayOkay("Hey, are you sure you've met #bMar the Fairy#k? Don't lie to me if you've never met her before because it's obvious. That wasn't even a good lie!!")