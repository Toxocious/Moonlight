# id 58929 ([Hieizan Temple] The Lost Alliance 1), field 811000014
sm.setSpeakerID(9130107) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("The alliance men? Last I saw, the Spirit Walkers were giving them some weird potions and casting spells, then they turned strange. What could have happened?")
sm.sendSay("Please save them. They were so good to me, almost like fathers.")
res = sm.sendAskYesNo("I saw the Oda Spirit Walkers nearby. Maybe we can get some information from them.")
sm.startQuest(parentID)
sm.sendSayOkay("Thank you for your help! I think defeating about 100 Oda Spirit Walkers will give you enough leads.")
