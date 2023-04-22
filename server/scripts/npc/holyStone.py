# Holy Stone - Holy Ground at the Snowfield (3rd job)

questIDs = [1431, 1432, 1433, 1435, 1436, 1437, 1439, 1440, 1442, 1443, 1445, 1446, 1447, 1448]

hasQuest = False
for qid in questIDs:
    if sm.hasQuest(qid):
        hasQuest = True
        break
if hasQuest:
    if sm.sendAskYesNo("#b(A mysterious energy surrounds this stone. Do you want to investigate?)"):
        sm.warpInstanceIn(910540000, 0)
else:
    sm.sendSayOkay("#b(A mysterious energy surrounds this stone)#k")

