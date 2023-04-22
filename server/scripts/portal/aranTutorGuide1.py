# 914000210
if not "chain=o" in sm.getQRValue(21002):
    sm.showFieldEffect("aran/tutorialGuide1")
    sm.systemMessage("You can use Consecutive Attacks by pressing the Ctrl key multiple times.")
    sm.addQRValue(21002, "chain=o")