import random
maps = [921160350, 921160340, 921160330, 921160400, 921160300, 921160320, 921160310]
if sm.hasMobsInField():
    sm.dispose()
fieldId = random.choice(maps)
while fieldId == sm.getFieldID():
    fieldId = random.choice(maps)
if fieldId == 921160400:
    for partyMember in sm.getParty().getMembers():
        sm.warp(fieldId)
    sm.dispose()
sm.warp(fieldId)
