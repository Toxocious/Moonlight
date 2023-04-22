# Ulrika - Dangerous Hide-and-Seek : Neglected Rocky Mountain (931000001)
if "exp2=1" not in sm.getQRValue(23007):
    sm.sendNext("Haha, you found me. Guess I should've found a better hiding spot.")
    sm.sendSay("Have you found Jun and Von yet? Von's going to be pretty hard to find. Better keep your eyes open.\r\n\r\n\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 5 exp")
    sm.giveExp(5)
    sm.addQRValue(23007, "exp2=1")
else:
    sm.sendNext("Have you found Jun and Von yet? Von's going to be pretty hard to find. Better keep your eyes open.")
