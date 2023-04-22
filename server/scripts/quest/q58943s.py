# id 58943 ([Hieizan Temple] Strange Alliance Men -2-), field 811000014
sm.setSpeakerID(9130107) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("Do you think that this will help turn the soldiers back to normal? ")
sm.setParam(16)
sm.sendSay("What's this?")
sm.setParam(4)
sm.sendSay("Before I passed out, one of the soldiers gave me this note.")
sm.setParam(16)
sm.sendSay("(You open the note.) 'The Oda Spirit Walker Jars can hold innocent souls. Ask someone to do that for our souls.' ")
sm.sendSay("...Welp, I guess there isn't actually a way to turn them back.")
sm.setParam(4)
res = sm.sendAskYesNo("I saw an Oda Spirit Walker carrying a suspicious jar. I think that might be what they are referring to.")
sm.startQuest(parentID)
sm.sendNext("They were such good people... I'm sad that this is the only thing we can do.")
sm.sendPrev("Whatever the case, I think we should get the jars first.")
