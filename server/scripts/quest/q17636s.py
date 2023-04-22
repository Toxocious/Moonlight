# id 17636 ([Commerci Republic] Who's The Pirate King?), field 865000002
sm.setSpeakerID(9390262) # Leon Daniella
sm.setParam(2)
sm.sendNext("Hey Leon, who's Captain Blood?")
sm.setParam(0)
sm.sendSay("Really? You really don't know #rCaptain Blood#k?")
sm.setParam(2)
sm.sendSay("Well, no. I mean, I'm not from around here, remember...?")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("I guess you ARE new... Would you like to know more?")
sm.sendSay("I'm sure you are aware that Commerci's trade is centered around the sea.")
sm.setParam(2)
sm.sendSay("Yes, I've heard.")
sm.setParam(4)
sm.sendSay("And there's no shortage of pirates in the sea, especially around the Commerci shore. They've been going around in small groups, plundering all sorts of goods.")
sm.sendSay("Commerci has been organizing our convoys and fleets better, but accidents still happen now and then.")
sm.sendSay("Our efforts were effective at first, but the pirates became more organized in response. Now they have one big pirate group, under their powerful leader.")
sm.setParam(2)
sm.sendSay("And that leader is Captain Blood?")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Totally. He's bad news, and needs a righteous butt-kicking.")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("That's...more or less true.")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18418, "B=33260")
