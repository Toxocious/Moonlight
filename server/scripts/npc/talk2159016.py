# Fattie - Dangerous Hide-and-Seek : Neglected Rocky Mountain (931000001)
if "exp4=1" not in sm.getQRValue(23007):
    sm.sendNext("D'oh! You found me. But I'm tiny! Are you a professional at this game or something?\r\n\r\n\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 3 exp")
    sm.giveExp(3)
    sm.addQRValue(23007, "exp4=1")
else:
    sm.sendNext("Drats. Might as well eat another piece of candy.")
