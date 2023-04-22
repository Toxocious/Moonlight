# id 940202019 (null), field 940202019
sm.createQuestWithQRValue(34801, "019=1;hunt1=1;exp=1")
sm.lockInGameUI(True, False)
sm.hideUser(True)
sm.changeBGM("Bgm00.img/Silence", 0, 0)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(500)
sm.sendDelay(2000)
sm.createFieldTextEffect("#fnﾳﾪﾴﾮﾰ￭ﾵ￱ ExtraBold##fs18#Meanwhile, in Savage Terminal...", 100, 2000, 6, -50, -50, 1, 4, 0, 0, 0)
sm.changeBGM("Bgm47.img/HuntingGround", 0, 0)
sm.spawnNpc(3001309, 172, 764)
sm.showNpcSpecialActionByTemplateId(3001309, "summon", 0)
sm.spawnNpc(3001313, -628, 764)
sm.showNpcSpecialActionByTemplateId(3001313, "summon", 0)
sm.moveNpcByTemplateId(3001309, True, 200, 80)
sm.moveNpcByTemplateId(3001313, False, 190, 80)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(2000)
sm.spawnNpc(3001301, -238, 764)
sm.showNpcSpecialActionByTemplateId(3001301, "summon", 0)
sm.playSound("Sound/Skill.img/152001004/Use", 100)
sm.showNpcSpecialActionByTemplateId(3001301, "appear", 0)
sm.sendDelay(1500)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendNext("#face0#See anything unusual during the patrol?")
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendNext("#face0#The High Flora wouldn't deign to come here. We're safely hidden.")
sm.setInnerOverrideSpeakerTemplateID(3001313) # Curly
sm.sendSay("#face0#We should still be cautious! I overheard a conversation earlier about someone meeting one of the High Flora here in Savage Terminal.")
sm.flipNpcByTemplateId(3001301, True)
sm.sendDelay(100)
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendNext("#face3#Is that true?!")
sm.setInnerOverrideSpeakerTemplateID(3001313) # Curly
sm.sendSay("#face0#Well, they said parts of the wings were mechanical.")
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendSay("#face0#Mechanical wings? Then that means...")
sm.setInnerOverrideSpeakerTemplateID(3001313) # Curly
sm.sendSay("#face0#Yes. Someone mistook one of us for a High Flora.")
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendSay("#face0#Someone left the sanctuary illegally? Why have I not heard about this before?")
sm.sendSay("#face1#The protection of the Sanctuary is paramount!")
sm.setInnerOverrideSpeakerTemplateID(3001313) # Curly
sm.sendSay("#face1#I told you as soon as I heard anything. It's why we're here now!")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face3#Sh! We can't draw attention to ourselves while we're exposed.")
sm.flipNpcByTemplateId(3001301, False)
sm.sendDelay(100)
sm.sendNext("#face3#Darius, please investigate this and make sure we're still protected.")
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendSay("#face4#Of course.")
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendSay("#face0##fs21#Lady Agate!")
sm.showEffect("Effect/OnUserEff.img/emotion/whatl", 0, 0, 0, 0, 80612623, 0, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/whatl", 0, 0, -20, 0, 80612394, 0, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/what", 0, 0, -10, 0, 80612393, 0, 0)
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face0#Someone from Sanctuary.")
sm.zoomCamera(1500, 1500, 1500, -178, 764)
sm.blind(True, 200, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendNext("#face0#A kid just left Sanctuary!")
sm.sendDelay(1000)
sm.speechBalloon(False, 0, 0, "!", 1000, 1, 0, 0, 0, 4, 3001301, 4600225)
sm.speechBalloon(False, 0, 0, "!", 1000, 1, 0, 0, 0, 4, 3001309, 4600225)
sm.speechBalloon(False, 0, 0, "!", 1000, 1, 0, 0, 0, 4, 3001313, 4600225)
sm.flipNpcByTemplateId(3001313, False)
sm.sendDelay(100)
sm.setInnerOverrideSpeakerTemplateID(3001313) # Curly
sm.sendNext("#face1#How did he get past security?")
sm.flipNpcByTemplateId(3001301, False)
sm.sendDelay(100)
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendNext("#face0#We contacted you because this is such a delicate situation.")
sm.sendSay("#face0#We've confirmed the one who left is #bIllium#k. We need to find him immediately.")
sm.blind(False, 0, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.flipNpcByTemplateId(3001313, False)
sm.sendDelay(100)
sm.setInnerOverrideSpeakerTemplateID(3001313) # Curly
sm.sendNext("#face0#Illium!? That engineer?")
sm.flipNpcByTemplateId(3001301, False)
sm.sendDelay(100)
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendNext("#face4#I knew that kid was bound to be trouble someday.")
sm.sendSay("#face0#He could expose the Verdant Flora and put all our lives in danger.")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face0#What's done is done.")
sm.sendSay("#face0#I'll find the child and return. Wait for me back inside the Sanctuary.")
sm.showNpcSpecialActionByTemplateId(3001301, "disappear", 0)
sm.sendDelay(750)
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendNext("#face1#It is unwise for Lady Agate to go alone.")
sm.setInnerOverrideSpeakerTemplateID(3001313) # Curly
sm.sendSay("#face0#You heard her. Let's get out of here.")
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.moveCamera(True, 0, 0, 0)
sm.hideUser(False)
sm.lockInGameUI(False, True)
sm.warp(940202020)
