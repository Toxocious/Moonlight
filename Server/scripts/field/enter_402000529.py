# id 402000529 (null), field 402000529
sm.lockInGameUI(False, True)
sm.setMapTaggedObjectVisible("crystal", False, 0, 0)
if not sm.hasQuestWithValue(34813, "d2=1"):
    sm.lockInGameUI(True, False)
    sm.removeAdditionalEffect()
    sm.blind(True, 255, 0, 0, 0, 0)
    sm.sendDelay(600)
    sm.forcedFlip(True)
    sm.sendDelay(1000)
    sm.blind(False, 0, 0, 0, 0, 1000)
    sm.sendDelay(1500)
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.setColor(1)
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendNext("#face0#Yes! I think we've finally got the basics taken care of.")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#Indeed! Your production time has been significantly reduced.")
    sm.sendSay("#face0#Your are exhibiting quite an aptitude for creating this product.")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face0#I really want to do this statue justice. This year's festival feels so much more meaningful to me.")
    sm.sendSay("#face0#Just thinking about the beautiful floating crystals that light up the Festival of the Gods every year is inspiring.")
    sm.createQuestWithQRValue(34813, "529=1;d=1;m=0;item2=1;exp=1;d2=1")
    sm.lockInGameUI(False, True)
    sm.warp(940202031)
elif not sm.hasQuestWithValue(34813, "031=1"):
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.setColor(1)
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendNext("#face0#This year's festival is going to be special! I'm going to do everything I can to shine brilliantly!")
    sm.createQuestWithQRValue(34813, "529=1;d=1;m=0;item2=1;exp=1;031=1;d2=1")
elif not sm.hasQuestWithValue(34816, "fin=1"):
    sm.setMapTaggedObjectVisible("obj", False, 0, 0)
    sm.lockInGameUI(True, False)
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.setColor(1)
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendNext("#face0#Now that we've attended the festival...")
    sm.lockInGameUI(False, True)
    sm.createQuestWithQRValue(34816, "d=1;fin=1;exp=1;d1=1")