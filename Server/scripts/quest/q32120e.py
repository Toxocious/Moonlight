# id 32120 ([Ellinel Fairy Academy] Dr. Betty's Measures), field 101072000
sm.setSpeakerID(1500001) # Headmistress Ivana
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1500001) # Headmistress Ivana
sm.sendNext("Welcome back. Did the girls in Ellinia help you?")
sm.setParam(2)
sm.sendSay("(You show them Dr. Betty's device.)")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1500002) # Faculty Head Kalayan
sm.sendSay("Are you suggesting we befoul our forest with this filthy, foul item from the corrupted human civilization? Never!")
sm.setInnerOverrideSpeakerTemplateID(1500009) # Rowen the Fairy
sm.sendSay("There's no other option at this point, Faculty Head Kalayan.")
sm.setInnerOverrideSpeakerTemplateID(1500008) # Arwen the Fairy
sm.sendSay("Rowen's right. We have to find those children!")
sm.setInnerOverrideSpeakerTemplateID(1500001) # Headmistress Ivana
sm.sendSay("I cannot say that I am fond of the idea, but we have no other options.")
sm.setInnerOverrideSpeakerTemplateID(1500002) # Faculty Head Kalayan
sm.sendSay("Fine. But this is on YOUR wings if it pollutes our forest...")
sm.setInnerOverrideSpeakerTemplateID(1500000) # Cootie the Really Small
sm.sendSay("Everyone, please stay quiet for a minute. I'm going to turn it on.")
sm.lockInGameUI(True, True)
sm.sendDelay(500)
sm.changeBGM("Bgm34.img/TheFairyForest", 0, 0)
sm.setParam(5)
sm.sendNext("......")
sm.sendSay("Wow, I can hear the whole forest!")
sm.sendDelay(2000)
sm.sendDelay(2000)
sm.sendNext("???")
sm.sendDelay(2000)
sm.sendDelay(2000)
sm.setInnerOverrideSpeakerTemplateID(1500002) # Faculty Head Kalayan
sm.sendNext("What's wrong with this thing? Why is it only recording useless noises?")
sm.setInnerOverrideSpeakerTemplateID(1500009) # Rowen the Fairy
sm.sendSay("Shh... Be quiet.")
sm.sendDelay(3000)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(1500001) # Headmistress Ivana
sm.sendNext("That voice!")
sm.setInnerOverrideSpeakerTemplateID(1500000) # Cootie the Really Small
sm.sendSay("It's coming from out back!")
sm.setInnerOverrideSpeakerTemplateID(1500002) # Faculty Head Kalayan
sm.sendSay("Be patient, children! I will save you right now!")
sm.setInnerOverrideSpeakerTemplateID(1500009) # Rowen the Fairy
sm.sendSay("Arwen, we should help.")
sm.setInnerOverrideSpeakerTemplateID(1500001) # Headmistress Ivana
sm.sendSay("Everyone, please wait!")
sm.changeBGM("Bgm34.img/TheFairyAcademy", 0, 0)
sm.lockInGameUI(False, True)
sm.completeQuestNoCheck(parentID)
