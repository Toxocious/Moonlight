WENDELLINE = 2151006

sm.setSpeakerID(WENDELLINE)
if sm.sendAskYesNo("Are you hurt? Allow me to treat you. Treatment is alway free for members of the Resistance."):
    chr.heal(chr.getMaxHP())
    sm.sendSayOkay("There you go. You're fully healed.")
else:
    sm.sendNext("So you don't need treatment?")