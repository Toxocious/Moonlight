# id 9310597 (Zarak), field 701220000
sm.setSpeakerType(3)
sm.setParam(56)
sm.setColor(1)
res = sm.sendNext("Talk to Zarak.\r\n#L1#What's a demon orb?#l\r\n#L0#Tell me more about collecting demon orbs.#l\r\n#L2#I'll help. Count me in!#l")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9310597) # Zarak
sm.sendSayOkay("I'm studying how demons are formed. Demon orbs are tightly condensed balls of demonic energy. I'm hoping they'll reveal the background and emotions of the demons you get them from.")
