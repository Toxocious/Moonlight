# 223010100

WATER_TRADE = 23121

if sm.hasQuest(WATER_TRADE):
    sm.warpInstanceIn(931000420, 1, False)
    sm.setInstanceTime(10*60)
    sm.chatScript("Thieves have attacked! Defeat all the thieves and then go see Ace the Pilot.")
    # sm.waitForMobDeath()
    # sm.chatScript("You've taken out all the thieves. Talk to Ace.")
    sm.dispose()

if sm.hasQuest(23023) or sm.hasQuest(23024) or sm.hasQuest(23025):  # 2nd job advancement for Resistance
    sm.warpInstanceIn(931000100, 0)
    sm.setInstanceTime(10*60)
    sm.dispose()

if sm.hasQuest(23162):
    sm.warpInstanceIn(931000101, 0)
    sm.setInstanceTime(10*60)
    sm.chatScript("The Schiller has appeared. Let's talk to him.")
    sm.chat("The Schiller has appeared. Let's talk to him.")
    sm.dispose()

sm.warp(310000010, 1)
