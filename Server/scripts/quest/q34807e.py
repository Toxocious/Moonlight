# id 34807 (Dean's Lost Stuff), field 402000532
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendNext("#face1#You actually found my stuff! Thanks so much!")
sm.sendSay("#face1#I had no idea you were such a nice guy! A little weird, but nice!")
sm.sendSay("#face3#See you next time!")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(parentID, "exp=1;d2=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/26")
sm.startQuest(11620)
sm.lockInGameUI(True, False)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#Sir...")
sm.sendSay("#face0#Someone has tampered with the practice robots. No one at the Crystal Academy is safe.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#...We should tell the Headmistress when we see her tomorrow.")
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.lockInGameUI(False, True)
sm.warp(402000526)
