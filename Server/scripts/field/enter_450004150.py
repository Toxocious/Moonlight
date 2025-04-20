# Boss Lucid Field Script

DREAMING_LUCID = 8880140 # Stage 1 Lucid
BOSS_HP = 1000000000

sm.spawnMob(DREAMING_LUCID, 1015, 48, False, BOSS_HP)
sm.spawnMob(8880166, 1015, 48, False, 1) # Tree Stem

sm.waitForMobDeath(DREAMING_LUCID)
sm.removeMobByTemplateId(8880166) # tree Stem