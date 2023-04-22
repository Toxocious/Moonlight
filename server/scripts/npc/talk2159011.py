# 931000001
sm.setSpeakerID(2159011)

if not ("exp1=1" in sm.getQRValue(23007) and "exp2=1" in sm.getQRValue(23007) and "exp3=1" in sm.getQRValue(23007) and "exp4=1" in sm.getQRValue(23007)):
    sm.sendSayOkay("#bFind your hiding friends before continuing.#k")
    sm.dispose()

if sm.sendAskYesNo("#b(What a suspicious hole. Maybe Von is hiding inside. Peek inside?)#k"):
    sm.giveExp(35)
    sm.warp(931000010, 0)

else:
    sm.sendSayOkay("#b(Even Von wouldn't hide here, right?)#k")
