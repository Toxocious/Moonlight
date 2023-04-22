# id 17671 ([Commerci Republic] San Commerci, I Missed You), field 865030300
sm.setSpeakerID(9390236) # Leon Daniella
sm.sendNext("What'd you find?")
sm.setParam(2)
sm.sendSay("I didn't go too far in, but there's something mean in there. It felt like something was trying to kill me with its mind.")
sm.setParam(0)
res = sm.sendAskYesNo("Whoa, good thing you came back. Let's just get back to my dad in San Commerci and fix this whole thing up.")
sm.startQuest(parentID)
sm.setParam(2)
sm.sendNext("Yeah, let's go back. I think we could all use a rest.")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390205) # Claire Tremier
sm.sendSay("I-I can't face my father. I'll go as far as the city gates...")
sm.setParam(0)
sm.sendSay("Okay, let's go.")
sm.warp(865000000)
