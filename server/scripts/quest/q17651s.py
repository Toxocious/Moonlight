# id 17651 ([Commerci Republic] A Funny Kind of Peace), field 865000002
sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Ah, but the wait is finally over.")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390217) # Tepes
res = sm.sendAskYesNo("The envoy from the Heaven Empire envoy is here. You want me to let him in?")
sm.startQuest(parentID)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9390207) # Zion
sm.sendNext("Hello. I am Zion, and I have been sent as the voice of the Heaven Empire.")
sm.setParam(32)
sm.sendSay("It is a pleasure to receive the Heaven Empire in our humble city. I am Gilberto Daniella, the Prime Minister of the Commerci Republic, and your host. Please tell me what kind of-- ")
sm.setParam(36)
sm.sendSay("We have a proposal for Commerci.")
sm.setParam(32)
sm.sendSay("A proposal? What type of proposal?")
sm.setParam(36)
sm.sendSay("The people of Commerci have long remained in my great lord emperor's favor, paying proper tithe and reverence to his divine right. Yet we have very little formal history. I visit today as an offering of peace.")
sm.setParam(32)
sm.sendSay("I welcome his peace. The people of Commerci are rather fond of our neighbors in the Heaven Empire, and I am certain we can prosper together. What are the terms of your peace?")
