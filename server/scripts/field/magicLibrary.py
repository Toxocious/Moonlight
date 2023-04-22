# Created by MechAviv
# ID :: [101000010]
# Ellinia : Magic Library
if sm.hasQuest(25559):
    sm.completeQuest(25559)
elif not "1" in sm.getQRValue(2790) and chr.getLevel() == 10:
    sm.createQuestWithQRValue(2790, "1")