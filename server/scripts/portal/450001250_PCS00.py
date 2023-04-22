# id 2 (PCS00), field 450001250
if sm.hasQuestCompleted(34120):
    sm.warp(450002021, 1)
else:
    sm.progressMessageFont(3, 20, 20, 0, "You must be Lv. 210 or higher to enter this area.")
    sm.warp(450001250)
