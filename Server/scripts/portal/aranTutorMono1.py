# 914000000
if not "mo2=o" in sm.getQRValue(21002):
    sm.playSound("Aran/balloon")
    sm.avatarOriented("Effect/OnUserEff.img/guideEffect/aranTutorial/legendBalloon2")
    sm.addQRValue(21002, "mo2=o")