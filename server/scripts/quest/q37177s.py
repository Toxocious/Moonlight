# id 37177 ([Elodin] Practice Always Makes Perfect), field 101084400
sm.setSpeakerID(1501007) # Baby Bird
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendNext("All I need is refreshing water and sweet flowers!")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("How are things coming along?")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("Ruenna!!")
sm.setInnerOverrideSpeakerTemplateID(1501015) # Shimmer Songbird
sm.sendSay("Welcome, Ruenna! I'm so grateful to you for taking care of my baby.")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("That was your baby bird?!")
sm.setInnerOverrideSpeakerTemplateID(1501015) # Shimmer Songbird
sm.sendSay("Yes! I was so worried about him! But you made sure he came home safe and sound.")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("She made me treats and everything!")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("I'm just surprised. Can't say I see much of a family resemblance, to be honest.")
sm.setInnerOverrideSpeakerTemplateID(1501015) # Shimmer Songbird
sm.sendSay("I suppose you're right about that, at least for now. But he'll be quite the flashy fowl once he's grown.")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("I want to grow up right now!")
sm.setInnerOverrideSpeakerTemplateID(1501015) # Shimmer Songbird
sm.sendSay("You'll be grown soon enough. Ruenna, if it wasn't for you...")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("It was nothing. I was just taking care of the forest.")
sm.setInnerOverrideSpeakerTemplateID(1501010) # Baby Bird
sm.sendSay("Oh! You're here just in time to hear me sing!")
sm.setInnerOverrideSpeakerTemplateID(1501013) # Ruenna the Fairy
sm.sendSay("Let's hear it then!")
sm.setParam(2)
sm.sendSay("Uhh...")
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(1000)
sm.onLayer(1500, "00", 0, 0, 12, "Effect/Direction21.img/Elodin/sing/0", 4, True, -1, False)
sm.sendDelay(3000)
sm.setParam(5)
sm.sendNext("I thought you got lessons...")
sm.sendSay("So much for a good night's sleep.")
sm.setParam(3)
sm.sendSay("At least his tempo is more consistent now.")
sm.setParam(5)
sm.sendSay("...")
sm.sendDelay(3000)
sm.offLayer(500, "00", False)
sm.sendDelay(2000)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)
sm.lockInGameUI(False, True)
sm.startQuest(parentID)
sm.createQuestWithQRValue(37150, "00=h0;01=h0;02=h0;03=h0;04=h1;07=h1;08=h0")
sm.createQuestWithQRValue(37150, "00=h0;01=h1;02=h0;03=h0;04=h1;07=h1;08=h0")
sm.warp(101000000)
