# id 37162 ([Elodin] Mood Lighting), field 101082000
sm.setSpeakerID(1501001) # Ruenna the Fairy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendNext("Did you get everything?")
sm.sendSay("This should be enough for me to turn the lights back on.")
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(False, 0, 0, 0, 0, 1000)
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)
sm.lockInGameUI(False, True)
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendNext("She's the one who said the forest's light was better.")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("I can hear you.")
sm.setParam(3)
sm.sendSay("......")
