# Magician 4th Job NPC Warp Script

MANON_PREV_MAP = 240020400
GRIFFEY_PREV_MAP = 240020100
MANONS_DARK_FOREST = 924000200
DARK_GRIFFEY_FOREST = 924000201


sm.setSpeakerID(parentID)
sm.sendNext("Is it in you to become a hero? The only way to find out is for you to take action...")
if not sm.hasQuest(1453):
    sm.dispose()

selection = sm.sendNext("If you agree to take this test, I will send you to Manon and Girffey. Of course, if you are capable of visit Manon Forest or Griffey Forest on your own, plese feel free to do so. What would you like to do?\r\n\r\n#L0##bPlease send me to Manon Forest.\r\n#L1#Please send me to Griffey Forest.#l\r\n#L2#It's nothing. I will go there on my own.#l#n")
if selection == 0:
    sm.sendNext("Would you like to go to Manon Forest? I will send you there. Come back if you can't find the other monster on your own.")
    sm.warpInstanceIn(MANONS_DARK_FOREST, False)
elif selection == 1:
    sm.sendNext("Would you like to go to Griffey Forest? I will send you there. Come back if you can't find the other monster on your own.")
    sm.warpInstanceIn(DARK_GRIFFEY_FOREST, False)
