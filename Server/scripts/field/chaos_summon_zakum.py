#field.spawnMob(8800102, -54, 86, False, 999999999)

for i in range(8):
    field.spawnMob(8800103 + i, -54, 86, False, 999999999)

breakLoop = False
while not breakLoop and field is not None and field.getChars().size() > 0:
    breakLoop = True
    for i in range(8):
        if field.getLifeByTemplateId(8800103 + i) is not None:
            breakLoop = False
    time.sleep(1)