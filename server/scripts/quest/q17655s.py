# id 17655 ([Commerci Republic] False Charges), field 865020071
sm.setSpeakerID(9390203) # Gilberto Daniella
sm.setParam(32)
sm.setColor(1)
sm.sendNext("What are you doing here? Causing some sort of... Is that the envoy from the Heaven Empire?!")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Why is that jerk on the ground? I didn't put him on the ground.")
sm.setParam(56)
sm.sendSay("Well... Uh, I... That is...")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(9390206) # Vaughn Tremier
sm.sendSay("Wait!! Is he D-D-DEAD?!")
sm.setParam(32)
sm.sendSay("Is he?")
sm.setParam(36)
sm.sendSay("It was YOU! You killed the envoy! You're a saboteur! The outsider has doomed us all! ")
sm.setParam(56)
sm.sendSay("What?! I didn't kill anyone! I'm against killing. I am anti-kill!")
sm.setParam(36)
sm.sendSay("Quiet! I know it was you! What are you Daniella fools doing?! Are you going to defend this murderer?!")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("#h0# can't be the murderer?! That's crazy, right? You're not the murderer, are you? No, of course not, haha!")
sm.setInnerOverrideSpeakerTemplateID(9390206) # Vaughn Tremier
sm.sendSay("You are siding with a foreigner you barely know?!")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Hey man, you're the one jumping to conclusions. My dad told me that was bad business.")
sm.setInnerOverrideSpeakerTemplateID(9390206) # Vaughn Tremier
sm.sendSay("Oh! I get it! You are in this together, aren't you?! Prime Minister Gilberto! Your son is committing high treason!")
sm.setParam(32)
sm.sendSay("...Arrest them both.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("D-dad?!")
sm.showFieldEffect("Map/EffectBT.img/dawnveil1/Clare", 0)
sm.showFieldEffect("Map/EffectBT.img/dawnveil1/Clare2", 0)
sm.setInnerOverrideSpeakerTemplateID(9390206) # Vaughn Tremier
sm.sendSay("W-What is going on?! My eyes! I've been blinded by these fiends!")
sm.setParam(32)
sm.sendSay("Calm down, Vaughn! It's only a distraction.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(9390204) # Robed Lady
sm.sendSay("Are you fools TRYING to get arrested?")
sm.setParam(56)
sm.sendSay("Oh... uh... not really?")
sm.startQuest(parentID)
sm.warp(865030000)
