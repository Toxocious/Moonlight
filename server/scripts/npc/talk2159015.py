# Cutie - Dangerous Hide-and-Seek : Neglected Rocky Mountain (931000001)
if "exp3=1" not in sm.getQRValue(23007):
    sm.sendNext("Aw shucks. You found me. Wow, you're really good at this game!\r\n\r\n\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 3 exp")
    sm.giveExp(3)
    sm.addQRValue(23007, "exp3=1")
else:
    sm.sendNext("Hehehe... I should have hidden somewhere else.")
