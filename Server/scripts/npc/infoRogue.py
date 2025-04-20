# Created by MechAviv
# [Dark Lord]  |  [10203]
# Maple Road : Split Road of Destiny

sm.setSpeakerID(10203)
sm.sendNext("Thieves are a perfect blend of luck, dexterity, and power that are adept at surprise attacks against helpless enemies. A high level of avoidability and speed allows Thieves to attack enemies from various angles.")


sm.setSpeakerID(10203)
if sm.sendAskYesNo("Would you like to experience what it's like to be a Thief?"):
    sm.setTemporarySkillSet(0)

    sm.createQuestWithQRValue(32219, "4000026")
    sm.warp(1020400, 0)
else:
    sm.setSpeakerID(10203)
    sm.sendNext("If you wish to experience what it's like to be a Thief, come see me again.")


