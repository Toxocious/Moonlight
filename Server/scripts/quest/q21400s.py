# 21400 - [Job Adv] (Lv.100)   Aran
sm.setSpeakerID(1510009)
if sm.sendAskYesNo("How is the training going? I know you're busy, but please come to #bRien#k immediately. The #bMaha#k has started to act weird again... But it's even weirder now. it's different from before. It's... darker than usual."):
    sm.startQuest(parentID)
    sm.sendSayOkay("I have a bad feeling about this. Please come back here. I've never seen or heard Maha like this, but I can sense the suffering it's going through. #bOnly you, the master of Maha, can do something about it#k!")
    sm.dispose()
else:
    sm.dispose()