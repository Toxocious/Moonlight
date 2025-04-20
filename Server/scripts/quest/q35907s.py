# id 35907 (Karuppa), field 100051000
sm.setSpeakerID(1013302) # Mascarpo
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
res = sm.sendAskAccept("#face0#Unfortunately, I can't twist the villagers' stubby arms into coming out. Maybe you should have a chat with the chief over there. He usually knows what to do.")
sm.setSpeakerType(3)
sm.sendNext("#face0#If you're on friendly terms with the chief, the others will start opening up to you too. That's why he's who you want to schmooze with first.")
sm.startQuest(parentID)
