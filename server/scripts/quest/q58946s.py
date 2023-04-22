# id 58946 ([Hieizan Temple] Fill the Yellow Jar), field 811000014
sm.setSpeakerID(9130107) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("The last one is the yellow jar, heh.  ")
sm.sendSay("Thank you for all your hard work, but could you please help me just ONCE more?")
res = sm.sendAskYesNo("I promise, I SWEAR this is the last one! After this, the alliance men can truly rest...")
sm.startQuest(parentID)
sm.sendNext("The third jar will fill up after you take down 200 #o9450033:# monsters of #m811000017:#...")
sm.sendSay("Woo, I know. I also wonder how so many alliance soldiers ended up like this.")
sm.sendPrev("This is the last round, so cheer up! I know you can do it.")
