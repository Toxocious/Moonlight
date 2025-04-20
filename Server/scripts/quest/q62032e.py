# id 62032 (Collecting the Red Zhanshi Spirit Demon Orb), field 701220000
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9310597) # Zarak
sm.sendNext("Hey. You have the orb I asked for?")
sm.completeQuestNoCheck(parentID)
sm.setSpeakerType(4)
sm.setSpeakerID(9310597) # Zarak
sm.sendSay("Oh yeah, this'll help with my research. Come to me, demon orb.")
sm.playExclSoundWithDownBGM("Field.img/masteryBook/EnchantSuccess", 100)
