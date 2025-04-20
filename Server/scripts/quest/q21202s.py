# 21202 - [Job Adv] (Lv.30)   Aran
sm.setSpeakerID(1203000)
sm.sendNext("Hmm... What's a young person like you doing in this secluded place?")
sm.setPlayerAsSpeaker()
sm.sendNext("I've come to get the best Polearm there is!")
sm.setSpeakerID(1203000)
sm.sendNext("The best Polearm? You should be able to purchase it in some town or other...")
sm.setPlayerAsSpeaker()
sm.sendNext("I hear that you are the best blacksmith in all of Maple World! I want nothing less than a weapon made by you!")
sm.setSpeakerID(1203000)
if sm.sendAskYesNo("I'm too old to make weapons now, but... I do have a Polearm that i made way back when. It's still in excellent shape. But I can't give it to you because that Polearm is extremely sharp, so sharp it could even hurt its master. Do you still want it?"):
    sm.startQuest(parentID)
    sm.sendSayOkay("Well, if you say so... I can't object to that. I'll tell you what. I'll give you a quick test, and if you pass it, the #p1201001# is yours. Head over to the #bTraining Center#k and take on the #r#o9831006##k that are there. your job is to bring back #b30 Sign of Acceptances#k.")
    sm.dispose()
else:
    sm.dispose()