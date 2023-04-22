# 914000220
if not "cmd=o" in sm.getQRValue(21002):
    sm.showFieldEffect("aran/tutorialGuide3")
    sm.systemMessage("You can use a Command Attack by pressing both the arrow key and the attack key after a Consecutive Attack.")
    sm.addQRValue(21002, "cmd=o")