# id 34300 ([Lachelein] Festival of Dreams), field 450003000
sm.startQuest(parentID)
sm.setSpeakerType(3)
sm.setParam(2)
sm.sendSayOkay("There are humans in the Arcane River? I should talk to them.")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003225) # Rabbit Mask
sm.sendNext("I'm happy, HAPPY I tell you!")
sm.createQuestWithQRValue(parentID, "NpcSpeech=30032251")
sm.setInnerOverrideSpeakerTemplateID(3003226) # Cat Mask
sm.sendNext("Fireworks, dancing, the sound of rushing water... I'm so excited!")
sm.createQuestWithQRValue(parentID, "NpcSpeech=30032251/30032262")
sm.setInnerOverrideSpeakerTemplateID(3003227) # Flutist Mask
sm.sendNext("Ha ha ha. Come, celebrate with me!")
sm.setParam(2)
sm.sendSay("This place is strange... Why is everyone wearing masks?")
sm.createQuestWithQRValue(parentID, "NpcSpeech=30032251/30032262/30032273")
