# id 2431768 (Schoolboys' Note), field 101072200
sm.createQuestWithQRValue(32158, "male=1")
sm.setSpeakerType(3)
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(1500029) # Schoolboys' Note
sm.sendNext("#b#eI've hidden my secrets under the bookshelves. Don't get caught snooping around!#n#k")
sm.setParam(17)
sm.sendSay("Secrets under the bookshelves? I'd better go ask #bCootie#k about these.")
sm.startQuest(32133)
sm.startQuest(32134)
sm.setQRValue(32133, "1")
sm.updateQRValue(32133, False)
sm.consumeItem()