# id 17534 ([Gollux] The Face of Fear), field 863000100
sm.setSpeakerID(9390120) # Heart Tree Guardian
sm.sendNext("Now, I'll give you a chance to face Gollux in all its power.")
sm.sendSay("I can give you up to 3 Entrance Keys per day.")
res = sm.sendAskYesNo("You now have 0 Entrance Key(s). Do you want all 3 Entrance Keys?")
sm.startQuest(17535)
sm.startQuest(parentID)
sm.completeQuestNoCheck(parentID)
sm.sendSayOkay("Check your inventory.")
