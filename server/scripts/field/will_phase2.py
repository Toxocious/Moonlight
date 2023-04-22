# will_phase2, fieldId 450008250 (The Deep Mirror)
fieldId = field.getId()
NEXT_FIELD = fieldId + 100
WILL = 8880301

sm.spawnMob(WILL, 249, 215, 6300000000000)

killed = False
while not killed:
    mob = sm.waitForMobDeath()
    templateId = mob.getTemplateId()
    if templateId == WILL:
        killed = True

for chr in field.getChars():
    chr.warp(NEXT_FIELD)