# id 64028 ([MONAD: The First Omen] Role of a Chief), field 867200400
sm.lockInGameUI(True, False)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendNext("#face0#Why are you working on your house? We have no time to waste! ")
sm.setInnerOverrideSpeakerTemplateID(9400617) # Resident
sm.sendSay("Didn't you say we'd be coming back...? That's why... ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#You can make repairs when you return. Right now, we have to leave before the snowstorm reaches us! ")
sm.sendDelay(1000)
sm.showEffect("Effect/OnUserEff.img/emotion/shade", 2000, 0, 0, 0, 17104721, 0, 0)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(9400617) # Resident
sm.sendNext("But I have nothing to pack... And no family to tend to. ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#Then help the others prepare. We have to work together. ")
sm.setInnerOverrideSpeakerTemplateID(9400617) # Resident
sm.sendSay("Chief Kan... I don't want to leave.")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#There are no exceptions. All Kaptafel villagers must evacuate to the town across the river. Every single one. ")
sm.setInnerOverrideSpeakerTemplateID(9400617) # Resident
sm.sendSay("I'm not saying I will stay. I want to explain... I don't agree with your decision, Chief Kan, but I will comply.")
sm.sendSay("It pains me to do this, to abandon my home. So I thought, if I fixed it up a little, that would help me cope with the loss. But now you're saying I can't even do that... ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#It cannot be helped. I can't risk others following your example and wasting time. If you don't want to help, rest up for the journey ahead. ")
sm.setInnerOverrideSpeakerTemplateID(9400617) # Resident
sm.sendSay("Do you really have to take it so far? ")
sm.sendSay("...I had no idea you were so cold. ")
sm.sendDelay(1000)
sm.flipNpcByTemplateId(9400616, False)
sm.moveNpcByTemplateId(9400616, False, 100, 100)
sm.sendDelay(2000)
sm.sendDelay(1000)
sm.setParam(57)
sm.sendNext("#bChief Kan. ")
sm.flipNpcByTemplateId(9400587, True)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendNext("#face1##h0#... ")
sm.setParam(57)
sm.sendSay("#b... ")
sm.setParam(37)
sm.sendSay("#face1#I know what you're thinking. You think I am too harsh. ")
sm.sendSay("#face1#But I don't have a choice. I'm responsible for these people. For everyone. If even one ignores my orders, everything falls apart. ")
sm.sendSay("#face1#Do not mind me... I'm just doing what I can as chief of this poor town. ")
sm.setParam(57)
sm.sendSay("#bChief Kan, this has to hurt you too, right? ")
sm.setParam(37)
sm.sendSay("#face1#...There is no time for me to feel. ")
sm.flipNpcByTemplateId(9400587, False)
sm.sendDelay(300)
sm.moveNpcByTemplateId(9400587, False, 100, 50)
sm.sendDelay(300)
sm.completeQuestNoCheck(parentID)
sm.lockInGameUI(False, True)
