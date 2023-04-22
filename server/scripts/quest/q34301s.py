# id 34301 ([Lachelein] City of Dreams and Illusions), field 450003100
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003209) # Gray Mask
sm.sendNext("Protective Mask, you sure are fearless for one so young. You were almost in big trouble.")
sm.setInnerOverrideSpeakerTemplateID(3003201) # Protective Mask
sm.sendSay("I couldn't leave that person at her mercy.")
sm.setInnerOverrideSpeakerTemplateID(3003209) # Gray Mask
sm.sendSay("Indeed. You sure have a lot of guts to get so close to her, stranger. Of course maybe you don't know any better... Well, you're safe now.")
sm.setParam(2)
sm.sendSay("Who was that woman?")
sm.setParam(4)
sm.sendSay("Her name is #b'Lucid'#k. Her power is unlike anything I've ever seen. She has the ability to manipulate dreams. In fact, #bLachelein#k is really just one great big prison plucked from her own dreams. And we're the prisoners. ")
sm.setParam(2)
sm.sendSay("I see. So that's why they call it the Dreaming City... ")
sm.setParam(4)
sm.sendSay("Hah. For us, it's a city of nightmares.")
sm.completeQuestNoCheck(parentID)
sm.warp(450003720)
