# id 2962 ([Gold Beach] Submarine Dreams), field 120041100
sm.setSpeakerID(1082203) # Tofu
res = sm.sendAskYesNo("I did see some Black Slime, but I don't know if what I saw was what I think it was. I suppose I can tell you about it, if that would get you to stop talking to me.")
sm.sendNext("I'll need some time to get my mind on the right track. I was sitting by the water, rubbing oil on my belly in the light of the moon, when I saw something move...")
sm.startQuest(parentID)
sm.showFieldEffect("goldBeach/submarine", 0)
sm.warp(120041200)
