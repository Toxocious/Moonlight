# 21303 - [Job Adv] (Lv.60)   Aran
sm.setSpeakerID(1203001)
sm.sendNext("*Sob sob* #p1203001# is sad. #p1203001# is mad. #p1203001# cries. *Sob sob*")
sm.setPlayerAsSpeaker()
sm.sendNext("Wh...What's wrong?")
sm.setSpeakerID(1203001)
sm.sendNext("#p1203001# made gem. #bGem as red as apple#k. But #rthief#k stole gem. #p1203001# no longer has gem. #p1203001# is sad...")
sm.setPlayerAsSpeaker()
sm.sendNext("A thief stole your red gem?")
sm.setSpeakerID(1203001)
if sm.sendAskYesNo("yes, #p1203001# wants gem back. #p1203001# reward you if you find gem. Catch thief and you get reward."):
    sm.startQuest(parentID)
    sm.sendNext("The thief wen that way! Which way? Hold on...eat with right hand, not left hand... #bLeft#k! He went left! Go left and you find thief.")
    sm.dispose()
else:
    sm.dispose()