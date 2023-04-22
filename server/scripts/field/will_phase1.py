# will_phase1, fieldId 450008150 (Diffraction Hall)
BLUE_WILL = 8880303
PURPLE_WILL = 8880304

sm.spawnMob(BLUE_WILL, 282, 159, 8400000000000) # Blue bar
sm.spawnMob(PURPLE_WILL, 282, -2020, 8400000000000) # Purple bar

NEXT_FIELD = field.getId() + 100
mobs = 2
while mobs > 0:
    mob = sm.waitForMobDeath()
    templateId = mob.getTemplateId()
    if templateId == BLUE_WILL or templateId == PURPLE_WILL:
        mobs -= 1

chr.warp(NEXT_FIELD)