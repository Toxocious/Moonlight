# 914000300
LOST_KID = 1209006

sm.setSpeakerID(LOST_KID)
if sm.sendAskAccept("*Sniff sniff* I was so scared... Please take me to Athena Pierce."):
    sm.startQuest(parentID)
    sm.warp(914000500, 1)
else:
    sm.sendNext("*Sob* Aran has declined my request!")
    sm.dispose()