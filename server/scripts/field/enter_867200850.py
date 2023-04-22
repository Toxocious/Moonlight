# id 867200850 (Abrup Basin : Abandoned Village Camp), field 867200850
sm.startQuest(64166)
sm.showNpcSpecialActionByTemplateId(9400582, "summon", 0)
sm.setSpeakerType(3)
sm.setParam(57)
sm.setColor(1)
sm.sendNext("#bLet's hand out firewood and meat to the people. ")
sm.completeQuestNoCheck(64037)
sm.startQuest(64038)
sm.createQuestWithQRValue(64038, "NpcSpeech=94005831")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendNext("#face0#Thank you, #h0#! I would've gone with you if you'd told me earlier. It's a bit embarrassing to just accept all this without having done anything.")
sm.setInnerOverrideSpeakerTemplateID(9400582) # Cayne
sm.sendSay("#face1#Don't worry, just eat. I brought back the most anyway.")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#Really? Thank you! ")
sm.createQuestWithQRValue(64038, "NpcSpeech=94005831/94005912")
sm.setInnerOverrideSpeakerTemplateID(9400591) # Shulla
sm.sendNext("Thank you, #h0#. Hawalu, come say thank you.")
sm.setInnerOverrideSpeakerTemplateID(9400593) # Hawalu
sm.sendSay("Thank you!")
sm.lockInGameUI(True, False)
sm.showEffect("Effect/OnUserEff.img/emotion/love", 0, 0, 0, 0, 141649, 0, 0)
sm.lockInGameUI(False, True)
sm.createQuestWithQRValue(64038, "NpcSpeech=94005831/94005912/94005803")
sm.createQuestWithQRValue(64038, "chk=1;NpcSpeech=94005831/94005912/94005803")
sm.warp(867200851)
