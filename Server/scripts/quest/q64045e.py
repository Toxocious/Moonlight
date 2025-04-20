# id 64045 ([MONAD: The First Omen] High Quality Meat), field 867201000
sm.lockInGameUI(True, False)
sm.completeQuestNoCheck(parentID)
sm.sendDelay(500)
sm.flipNpcByTemplateId(9400587, False)
sm.sendDelay(500)
sm.moveNpcByTemplateId(9400587, False, 110, 50)
sm.flipNpcByTemplateId(9400584, False)
sm.flipNpcByTemplateId(9400580, False)
sm.sendDelay(2000)
sm.playSound("Sound/PL_MONAD.img/EP1/ACT2/open", 128)
sm.sendDelay(250)
sm.spawnNpc(9400597, 858, -45)
sm.showNpcSpecialActionByTemplateId(9400597, "summon", 0)
sm.sendDelay(1000)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendNext("#face0#Hmm, hmm. Let's see what we have here... ")
sm.sendSay("#face0#Not bad, not bad... I suppose your people do have a reputation for hunting. Now... Say, why are Chief Kan's eyes swollen? ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#...Will you take us in now? ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Ah, not so fast! We have matters to discuss first. ")
sm.sendDelay(1000)
sm.playSound("Sound/PL_MONAD.img/EP1/ACT2/close", 128)
sm.sendDelay(500)
sm.flipNpcByTemplateId(9400589, False)
sm.moveNpcByTemplateId(9400589, False, 850, 100)
sm.setInnerOverrideSpeakerTemplateID(9400582) # Cayne
sm.sendNext("#face0#I don't like the sound of this. ")
sm.setInnerOverrideSpeakerTemplateID(9400580) # Alika
sm.sendSay("#face4#What a disappointing person... ")
sm.setParam(57)
sm.sendSay("#bWe need shelter before night falls... ")
sm.sendDelay(5000)
sm.playSound("Sound/PL_MONAD.img/EP1/ACT2/open", 128)
sm.sendDelay(250)
sm.spawnNpc(9400597, 858, -45)
sm.showNpcSpecialActionByTemplateId(9400597, "summon", 0)
sm.sendDelay(1000)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendNext("#face0#Now then, we don't seem to have much space for you lot to sleep. ")
sm.sendSay("#face0#If you don't want to freeze tonight, you'll need something to keep you warm, won't you? ")
sm.sendSay("#face0#Gather Werebeast Furs for your people. Once that's done, I'll let you in. ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#...What do you think you're doing? ")
sm.sendSay("#face0#What happened to common decency? How can you sleep at night, using suffering refugees to do your chores?!")
sm.flipNpcByTemplateId(9400580, False)
sm.moveNpcByTemplateId(9400580, False, 270, 80)
sm.sendDelay(2000)
sm.setInnerOverrideSpeakerTemplateID(9400580) # Alika
sm.sendNext("#face4#Chief Kan, I completely agree but I don't think we can afford to lose our tempers right now. ")
sm.sendSay("#face4#A common struggle might not be enough to restore a strained relationship. If you truly want things to be different, we should prove how different we are now. ")
sm.sendSay("#face4#If you lose your temper now... ")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Alika is right. ")
sm.sendSay("#face0#We must take responsibility for our dealings. Blaming the other side accomplishes nothing. ")
sm.sendSay("#face0#They act as they always have. If we do the same, nothing will change. ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Ahem! ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#... ")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Honored chief of Svarti... You do intend to take us in, yes? ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Why, that should be obvious by now! Why else would I waste my time with these dealings? ")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Understood. We'll bring back the Werebeast Fur, as promised. ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Hmm, hmm. Indeed. ")
sm.sendDelay(1000)
sm.playSound("Sound/PL_MONAD.img/EP1/ACT2/close", 128)
sm.sendDelay(500)
sm.flipNpcByTemplateId(9400589, True)
sm.sendDelay(500)
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendNext("#face0#... ")
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendSay("#face1#Hahh... ")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Since the knights have already done so much, I ask that you take this time to rest. I will go myself. ")
sm.setParam(57)
sm.sendSay("#bI'm coming with you! ")
sm.sendDelay(1000)
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.startQuest(64047)
sm.lockInGameUI(False, True)
sm.warp(867201160)
