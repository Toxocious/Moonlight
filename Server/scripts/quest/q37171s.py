# id 37171 ([Elodin] Music Teacher), field 101082000
sm.setSpeakerID(1501001) # Ruenna the Fairy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendNext("I wonder what could have made a bird of Elodin cry...")
sm.sendSay("Oh? She said she'd teach him how to sing? Wonderful!")
sm.sendSay("So now you have to take the baby bird there in person?")
res = sm.sendAskYesNo("Then the two of you could go there together!")
sm.setParam(5)
sm.sendNext("Come back safely!")
sm.createQuestWithQRValue(37150, "00=h0;01=h1;02=h0;03=h0;04=h1")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("I'm so excited! Maybe she can teach me how to sing properly, so Ruenna will like listening to me!")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("Don't get ahead of yourself now...")
sm.setParam(3)
sm.sendSay("Why don't we get going? It's a long trip.")
sm.startQuest(parentID)
sm.warp(101084400)
