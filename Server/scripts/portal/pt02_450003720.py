# id 2 (pt02), field 450003720
if sm.hasQuestCompleted(34302):
    sm.warp(450003100)

else:
    sm.setSpeakerType(3)
    sm.setParam(2)
    sm.sendSayOkay("(I should try and get through that fog on the right.)")
