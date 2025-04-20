# Created by MechAviv
# ID :: [102000003] | # ID :: [103000003]
# Perion : Warriors' Sanctuary | Kerning City : Hideout
if "1" not in sm.getQRValue(2849) and chr.getJob() == 0:
    sm.createQuestWithQRValue(2849, "1")
