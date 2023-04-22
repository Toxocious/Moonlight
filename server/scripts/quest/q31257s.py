# id 31257 ([Crimsonheart] Soldiers of Crimsonwood), field 301000000
sm.setSpeakerID(2134012) # Ridley
res = sm.sendAskYesNo("Did you rescue all those poor demon kids?")
sm.sendNext("I have to tell you, I was worried when you first showed up. You're not exactly the same kind of warrior we're used to seeing around these parts!")
res = sm.sendNext("I'm guessing you got a look at the soldiers in the upper keep? They're a lot like the ones we saw back in Crimsonwood Keep, only a lot meaner.\r\n\r\n#b#L0#What happened to them?#l")
sm.setParam(1)
sm.sendNext("People change during war. Naricain had ways of corrupting even the best of us.")
sm.sendSay("I'm not sure... None of those things should be walking around after the beatings they got at Crimsonwood, but now they're back and tougher than before. That's some kinda dark magic, if you ask me.")
sm.startQuest(parentID)
sm.completeQuestNoCheck(parentID)
