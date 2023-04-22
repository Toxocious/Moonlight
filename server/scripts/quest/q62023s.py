# id 62023 (Demon Orb Introduction), field 701220000
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9310597) # Zarak
sm.sendNext(".....")
res = sm.sendAskYesNo("You! Stranger! Got a moment?")
sm.sendNext("I've been watching you. I think you'd be the perfect person to help me with my top secret research.")
sm.setSpeakerType(4)
sm.setSpeakerID(9310597) # Zarak
sm.setParam(57)
sm.sendSay("Research? What kind of research?")
sm.setSpeakerType(3)
sm.setParam(37)
sm.sendSay("I'm studying demons. With your combat prowess, I think you can get me the #bdemon orbs#k that I need.")
sm.sendSay("Speak to me again if you're interested. I promise to make it worth your while.")
sm.completeQuestNoCheck(parentID)
sm.playExclSoundWithDownBGM("Field.img/masteryBook/EnchantSuccess", 100)
