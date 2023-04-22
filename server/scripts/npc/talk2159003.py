# Jun - Dangerous Hide-and-Seek : Neglected Rocky Mountain (931000001)
if "exp1=1" not in sm.getQRValue(23007):
    sm.sendNext("Eep! You found me.")
    sm.sendSay("Eh, I wanted to go further into the wagon, but my head wouldn't fit.")
    sm.sendSay("Did you find Ulrika and Von yet? Von is really, really good at hiding.\r\n\r\n\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 5 exp")
    sm.giveExp(5)
    sm.addQRValue(23007, "exp1=1")
else:
    sm.sendNext("Did you find Ulrika and Von yet? Von is really, really good at hiding.")
