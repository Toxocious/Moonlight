# [Shaoling Temple] Intercepted Messages

ELDER_JUNG = 9310049
WISE_CHIEF_PRIEST = 9310053

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("You bear a message from #b#p"+ str(ELDER_JUNG) +"##k?")

sm.sendNext("So, demons have spread as far as Mount Song Hamlet... Something must be done...")
sm.completeQuest(parentID)
sm.consumeItem(4034635)
sm.giveExp(1515339)