# id 62015 ([Shaolin Temple] Nine-Tailed Fox's One True Love), field 701220350
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9310579) # Nine-Tailed Fox
sm.sendNext("Did you deliver my letter? What did my beloved say?")
sm.setSpeakerType(4)
sm.setSpeakerID(9310579) # Nine-Tailed Fox
sm.setParam(57)
sm.sendSay(" (You can't tell her the truth without putting your eyeballs at risk, \r\nso you quickly come up with a lie.)")
sm.sendSay("He cried after reading your letter.\r\n He said the others in town suspect something, so they've been watching him non-stop.\r\n He said you'd be in danger if you approach, \r\nso you must stay far far away, for your own sake. ")
sm.setSpeakerType(3)
sm.setParam(37)
sm.sendSay("He... He really said that? Next time I see him, \r\nI'm going to... I'm going to... \r\n#rI'm going to hug him from behind!#k He cares about me so much!")
sm.setSpeakerType(4)
sm.setParam(57)
sm.sendSay(" (.........)")
sm.completeQuestNoCheck(parentID)
sm.playExclSoundWithDownBGM("Field.img/masteryBook/EnchantSuccess", 100)
