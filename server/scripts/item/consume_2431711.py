# Cygnus Soul
souls = [2591088, 2591179, 2591180, 2591181, 2591182, 2591183, 2591184, 2591185, 2591186]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431711, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431711, 10)
    sm.giveItem(soul)