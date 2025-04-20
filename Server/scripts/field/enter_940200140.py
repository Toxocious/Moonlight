# id 940200140 (Hidden Street : Conference Pavilion), field 940200140
sm.lockInGameUI(True, False)
sm.hideUser(True)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 0)
sm.zoomCamera(0, 1000, 0, -310, 10)
sm.spawnNpc(3003272, -1220, 80)
sm.showNpcSpecialActionByTemplateId(3003272, "summon", 0)
sm.sendDelay(500)
sm.blind(False, 0, 0, 0, 0, 1500)
sm.sendDelay(1000)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1540452) # Claudine
sm.sendNext("I know what our scout saw! The Cygnus Knights betrayed us! They betrayed the Alliance, and all of Maple World!")
sm.setInnerOverrideSpeakerTemplateID(1540450) # Cygnus
sm.sendSay("Please calm down. We all know what was witnessed beyond the Gate to the Future was a fabrication created by the Black Mage!")
sm.setInnerOverrideSpeakerTemplateID(1540838) # Neinheart
sm.sendSay("I would never lead the Knights into such an obvious trap!")
sm.setInnerOverrideSpeakerTemplateID(1540452) # Claudine
sm.sendSay("...You say that now with the benefit of hindsight. Your arrogance won't save us from what could be!")
sm.setInnerOverrideSpeakerTemplateID(1540838) # Neinheart
sm.sendSay("Do you trust the enemy's illusion over your own allies?!")
sm.setInnerOverrideSpeakerTemplateID(1540453) # Athena Pierce
sm.sendSay("We can't allow the enemy to divide us. Please stop fighting!")
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3003272) # Lucid
sm.sendNext("#face2#Heh, the Alliance is wavering just as he foresaw. Everything is going according to plan.")
sm.sendSay("#face2#Even though they realized it was an illusion, the seeds of doubt have been planted in their minds.")
sm.sendSay("#face2#Under the care of the nightmare that is about to unfold, those seeds will bloom.")
sm.zoomCamera(2000, 1000, 2000, -310, -200)
sm.sendDelay(2000)
sm.blind(True, 255, 0, 0, 0, 250)
sm.sendDelay(250)
sm.hideUser(False)
sm.lockInGameUI(False, True)
sm.warp(940200141)
