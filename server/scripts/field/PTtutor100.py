# Waist Deck [Phantom Tutorial: Skip] (915000100)

ghostWalk = 20031211
chump = 20031212
shroudWalk = 20031205

MAPLE_ADMINISTRATOR = 2007
medal = 1142375

tutorial = 25000
lumiere = 150000000

sm.setSpeakerID(MAPLE_ADMINISTRATOR)

if sm.sendAskYesNo("Would you like to skip the tutorial and instantly arrive at the #m" + str(lumiere) + "#?"):
    # Swap beginner skills
    sm.removeSkill(ghostWalk)
    sm.removeSkill(chump)
    sm.giveSkill(shroudWalk)

    # Let PTjob1.py handle everything else
    sm.warp(lumiere)