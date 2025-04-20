# 222020000 - Ludi tower: Helios Tower <Library> (CoK 3rd job portal)
if not sm.hasQuest(20881) and sm.isComplete(20880): # 3rd job quest
    sm.chat("Only knights looking to job advance to the third job may enter here.")
else:
    sm.warpInstanceIn(922030400, 0, False)
