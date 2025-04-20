# id 34803 (Combat Training), field 402000527
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001303) # Professor Andrada
sm.sendNext("#face0#Now that everyone seems to have learned the basics, let's get into full-scale combat training.")
sm.setParam(37)
sm.sendSay("#face0#Proceed to the combat training room and eliminate the#r practice robots#k.")
sm.setSpeakerType(4)
sm.setSpeakerID(3001333) # Professor Andrada
res = sm.sendAskAccept("#face0#I'll consider your day's coursework complete when you have defeated #b20#k.")
sm.startQuest(parentID)
sm.setSpeakerType(3)
sm.sendNext("#face0#If you're ready, enter the combat training room behind me and eliminate the #rpractice robots#k.")
