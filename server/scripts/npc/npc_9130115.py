# id 9130115 (100-Generation Blacksmith), field 801000000
sm.setSpeakerID(9130115) # 100-Generation Blacksmith
sm.sendNext("Hello, hello! My family has practiced the art of smithing for 100 generations. How may I help you?")
sm.setParam(2)
sm.sendSay("I hear you can fix this #i4034138##t4034138#.")
sm.setParam(0)
sm.sendSay("Is this...? I never thought I would see it myself. The final page of our legacy blacksmithing guide is about this...")
sm.setParam(2)
sm.sendSay("Is something wrong?")
sm.setParam(0)
res = sm.sendNext("Hm? No, no... Well, it's just that it's been tough for a blacksmith around here lately. I'll have to charge a pretty hefty price. 1,000,000 mesos will see it done in a jiffy. \r\n#b\r\n#L0# Pay 1,000,000 to fix it. #l\r\n#L1# Doesn't seem worth the money. #l")
sm.sendNext("#b*THUNK THINK* Okay, it's done.#k")
sm.sendPrev("It's almost done!")
sm.warp(807000000)
