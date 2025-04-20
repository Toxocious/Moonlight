# Vita | Dangerous Hide-and-Seek : Suspicious Laboratory
if "vel00=1" not in sm.getQRValue(23007):
    sm.sendNext("Stay back!")
    sm.sendSay("How did you get here? This place is prohibited!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Who's talking? Where are you?!")

    sm.resetParam()
    sm.sendSay("Look up.")
    sm.addQRValue(23007, "vel00=1")
    sm.reservedEffect("Effect/Direction4.img/Resistance/ClickVel")
elif "vel00=2" not in sm.getQRValue(23007):
    sm.sendNext("My name is #bVita#k. I'm one of #rDoctor Gelimer's#k test subjects. But that's not important right now. You have to get out of here before someone sees you!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Wait, what are you talking about? Someone's doing experiments on you?! And who's Gelimer?")

    sm.resetParam()
    sm.sendSay("You've never heard of Doctor Gelimer, the Black Wings' mad scientist? This is his lab, where he conducts experiments...on people.")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Experiments...on people? Are you serious?")

    sm.resetParam()
    sm.sendSay("Yes! And if he catches you here, he won't be merciful. Get out of here! Quickly!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("What? But what about you?!")

    sm.resetParam()
    sm.sendSay("Shhh! Did you hear that? Someone's coming! It's got to be Doctor Gelimer! Oh no!")

    sm.addQRValue(23007, "vel00=2")
    sm.warp(931000011, 0)
