# 914000200
if not "normal=o" in sm.getQRValue(21002):
    sm.showFieldEffect("aran/tutorialGuide1")
    sm.systemMessage("To use a Regular Attack on monsters, press the Ctrl key.")
    sm.addQRValue(21002, "normal=o")