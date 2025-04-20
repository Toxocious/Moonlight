# id 64064 ([MONAD: The First Omen] Soldier's Dilemma), field 867201100
sm.lockInGameUI(True, False)
sm.flipNpcByTemplateId(9400620, False)
sm.flipNpcByTemplateId(9400621, False)
sm.flipNpcByTemplateId(9400622, False)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400620) # Simmons
sm.sendNext("#h0#! You're back. I hope it wasn't any trouble. ")
sm.setParam(57)
sm.sendSay("#bNo problem at all. You don't need to worry. ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400622) # Williams
sm.sendSay("We were finally able to rest up, thanks to you. Honestly, this is the best rest I've gotten since we came to Abrup. ")
sm.sendSay("I'd never say we have it easy, but the knights... it seems like they don't even get to sleep. Gillie especially takes so much on herself to help us out. She's the best! ")
sm.setInnerOverrideSpeakerTemplateID(9400621) # Jenkins
sm.sendSay("That's right. We may just be soldiers now, but we're all working hard to become knights one day! ")
sm.spawnNpc(9400623, 280, -10)
sm.showNpcSpecialActionByTemplateId(9400623, "summon", 0)
sm.spawnNpc(9400581, 220, -10)
sm.showNpcSpecialActionByTemplateId(9400581, "summon", 0)
sm.sendDelay(250)
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendNext("#face0#What are you all doing here? ")
sm.setInnerOverrideSpeakerTemplateID(9400620) # Simmons
sm.sendSay("Di-dispatch Commander! ")
sm.moveNpcByTemplateId(9400581, True, 300, 100)
sm.sendDelay(250)
sm.moveNpcByTemplateId(9400623, True, 300, 100)
sm.sendDelay(2000)
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendNext("#face0#What? You asked for help?! ")
sm.sendSay("#face0#How dare you give your sacred duty to another... How can you expect to be trusted with the safety of others now? ")
sm.setInnerOverrideSpeakerTemplateID(9400620) # Simmons
sm.sendSay("Sorry, sir. ")
sm.setParam(57)
sm.sendSay("#bButler, I insisted. I wanted to help them... ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendSay("#face0#There is no need to speak for soldiers. They answer for their actions. To your posts, now! ")
sm.setInnerOverrideSpeakerTemplateID(9400620) # Simmons
sm.sendSay("Yes, Dispatch Commander, sir! ")
sm.flipNpcByTemplateId(9400620, True)
sm.flipNpcByTemplateId(9400621, True)
sm.moveNpcByTemplateId(9400620, True, 500, 150)
sm.moveNpcByTemplateId(9400621, True, 500, 150)
sm.moveNpcByTemplateId(9400622, False, 300, 150)
sm.lockInGameUI(False, True)
sm.completeQuestNoCheck(parentID)
sm.showNpcSpecialActionByTemplateId(9400622, "summon", 0)
