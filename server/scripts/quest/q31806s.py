#Securing the Front Lines | Magnus Prequest

sm.setSpeakerID(3001000)
sm.sendNext("Hey there. Glad to see you made it here with all your limbs. I called you out here to help me lock down the front.")
sm.setPlayerAsSpeaker()
sm.sendNext("I didn't think all those battle hounds were stopping supplies from getting through.")
sm.setSpeakerID(3001000)
sm.sendNext("You got that right. We need to open up the supply lines if we want to make a difference. That means an attack on the #b#m401010010##k, where all those #r#o2400109##k monsters are holed up.")
if sm.sendAskYesNo("It's tough to get through them with their High HP and defense. but I'm sure you'll figure out a way to take down their shields. \r\n \r\n #b#e(Press Accept to move automatically. You will have to forfeit the quest and restart if you fail.)#k#n"):
    sm.sendNext("It shouldn't be too tough for you. You'll take care of business easy.")
    sm.startQuest(31806)
    sm.warpInstanceIn(401070100)
    sm.dispose()
else:
    sm.dispose()
