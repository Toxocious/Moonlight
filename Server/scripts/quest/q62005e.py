# id 62005 ([Shaolin Temple] Demon B Gone), field 701220000
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9310046) # Zheung Guan
sm.sendNext("So, how'd everything go?")
sm.setSpeakerType(4)
sm.setSpeakerID(9310046) # Zheung Guan
sm.setParam(57)
sm.sendSay("You said the demons were going to disappear but MORE of them appeared! I almost died! ")
sm.setSpeakerType(3)
sm.setParam(37)
sm.sendSay("Yeah, that's what I was afraid of. I'll alter some ingredients... Maybe add in some extra bananas...")
sm.setSpeakerType(4)
sm.setParam(57)
sm.sendSay("(You don't want to be the one to test the new potion after what just happened. Maybe it's time to go talk to the #b#p9310053##k...)")
sm.completeQuestNoCheck(parentID)
sm.playExclSoundWithDownBGM("Field.img/masteryBook/EnchantSuccess", 100)
