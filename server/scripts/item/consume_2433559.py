# Vampire Blood

sm.setSpeakerID(9000138)

buffs = [2023464, 2023465, 2023466, 2023467, 2023468]
rand = sm.getRandomIntBelow(len(buffs))

if not sm.canHold(2023464):
    sm.sendSayOkay("Please make sure you have room in your inventory.")

else:
    buff = buffs[rand]
    sm.consumeItem(2433559)
    sm.giveItem(buff)