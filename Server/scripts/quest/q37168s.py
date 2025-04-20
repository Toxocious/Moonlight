# id 37168 ([Elodin] Shimmer Songbird), field 101084400
sm.createQuestWithQRValue(37150, "00=h0;01=h1;02=h0;03=h2;04=h1")
sm.setSpeakerID(1501004) # Shimmer Songbird
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1501015) # Shimmer Songbird
sm.sendNext("It's been a while since Elodin's had visitors. What brings you here?")
sm.setParam(2)
sm.sendSay("I have a request from Ruenna.")
sm.setParam(4)
sm.sendSay("Ah, Ruenna. Such a nice fairy. And she truly loves Elodin.")
sm.sendSay("I miss her visits.")
sm.setParam(2)
sm.sendSay("Well, she needs your help.")
sm.setParam(4)
sm.sendSay("Ah, yes! A little bird told me-- cough\r\nA little bird told me she's gotten herself into some trouble lately.")
sm.startQuest(parentID)
sm.completeQuestNoCheck(parentID)
