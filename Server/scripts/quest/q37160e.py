# id 37160 ([Elodin] Ruenna's Feathered Nuisance), field 101082000
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(500)
sm.forcedFlip(True)
sm.sendDelay(500)
sm.blind(True, 150, 0, 0, 0, 1300)
sm.sendDelay(1000)
sm.forcedFlip(True)
sm.sendDelay(500)
sm.forcedAction(10, 0)
sm.playSound("Sound/Reactor.img/2002001/0/Hit", 200)
sm.sendDelay(1000)
sm.playSound("Sound/SoundEff.img/Elodin/scream_close", 200)
sm.setSpeakerID(1501003) # Baby Bird
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendNext("Squawk!")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("Oh! I thought you were a wimp, not a bully.")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("Why did you hit me?!")
sm.setParam(3)
sm.sendSay("Stop making that terrible racket!")
sm.createQuestWithQRValue(37150, "00=h0;01=h1;02=h0;03=h2")
sm.sendDelay(1000)
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendNext("How dare you hit my bird?! You! A complete stranger! In #emy#n house! How uncivilized!")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("Maybe I should go...")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("And you! I was trying to sleep! I ought to put you in a cage and cover you with a sheet like some ordinary parakeet!")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("*sniffle*")
sm.setParam(3)
sm.sendSay("Why haven't you?")
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("As annoying as it can be sometimes, I'm a friend of the forest. I don't want to treat my friends poorly.")
sm.setParam(3)
sm.sendSay("......")
sm.sendDelay(1000)
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)
sm.lockInGameUI(False, True)
sm.completeQuestNoCheck(parentID)
