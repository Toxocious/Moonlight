# id 402000633 (null), field 402000633
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 0)
sm.spawnNpc(3001512, -745, -150)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, -657, -150)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(1200)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1400)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001512) # Digs
sm.sendNext("#face0#Ferret, Ark!! You're okay!")
sm.setInnerOverrideSpeakerTemplateID(3001513) # Grit
sm.sendSay("#face0#I don't remember anything after the fall. I just woke up here.")
sm.setInnerOverrideSpeakerTemplateID(3001512) # Digs
sm.sendSay("#face0#The exit's straight above us.")
sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
sm.sendSay("#face0#We've got to find Salvo before we can leave.")
sm.sendSay("#face4#Of course he's the one that's missing.")
sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
sm.sendSay("#face4#Calm down. We'll find him.")
sm.createQuestWithQRValue(34933, "dir=1;exp=1")
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)
sm.lockInGameUI(False, True)
