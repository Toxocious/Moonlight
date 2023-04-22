# Philius's Request

PHILIUS = 1033202

sm.setSpeakerID(PHILIUS)
sm.sendNext("Maybe we're the lucky ones. "
            "While we sleep, Maple World will heal from the terrible things the Black Mage has done. "
            "I wonder what kind of world we'll wake up to?")


response = sm.sendAskYesNo("Your Highness, I will dream of a more beautiful world when we awaken...")

if response:
    sm.completeQuest(parentID)
    sm.sendSayOkay("I wish you...sweet dreams...")
    sm.startQuest(24005) # Cursed Slumber
sm.dispose()
