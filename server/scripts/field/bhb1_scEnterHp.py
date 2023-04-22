# Black Heaven Inside: Core (350060160)  |  Stage 1 Lotus Boss  |  Used to show Lotus (stage1) HP as well as warp party into the next map upon Lotus' death

BLACK_HEAVEN_CORE = 8240103 # Stage 1

sm.showHP(BLACK_HEAVEN_CORE)

sm.waitForMobDeath(BLACK_HEAVEN_CORE)

sm.invokeAfterDelay(7000, "warp", 350060180, 0) # Warp player(s) into the next map after 7000ms delay
