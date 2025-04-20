# id 64044 ([MONAD: The First Omen] Proving Your Worth), field 867201000
sm.lockInGameUI(True, False)
sm.completeQuestNoCheck(parentID)
sm.startQuest(64045)
sm.sendDelay(500)
sm.flipNpcByTemplateId(9400587, False)
sm.sendDelay(500)
sm.moveNpcByTemplateId(9400587, False, 110, 50)
sm.sendDelay(2000)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendNext("#face0#Here, we brought Hellfang meat. This should last quite a while if you slice it thin and store it. ")
sm.playSound("Sound/PL_MONAD.img/EP1/ACT2/open", 128)
sm.sendDelay(250)
sm.spawnNpc(9400597, 858, -45)
sm.showNpcSpecialActionByTemplateId(9400597, "summon", 0)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendNext("#face0#Eh? Hellfang meat? ")
sm.sendSay("#face0#Surely you don't expect us to eat this tough, tasteless meat, right? ")
sm.sendSay("#face0#Ahh! No, of course not. This is your food. Providing for yourself, as expected. Good, good. ")
sm.sendSay("#face0#Since we're on the topic, though... how about bringing back something for the rest of us to eat?")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#... ")
sm.setInnerOverrideSpeakerTemplateID(9400596) # Snowfield Archer
sm.sendSay("... ")
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendSay("#face0#... ")
sm.setInnerOverrideSpeakerTemplateID(9400582) # Cayne
sm.sendSay("#face0#... ")
sm.setInnerOverrideSpeakerTemplateID(9400580) # Alika
sm.sendSay("#face0#... ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#Understood. What of Shrelephant meat? ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Well, that'd be a sight better than Hellfang meat. ")
sm.sendDelay(1000)
sm.playSound("Sound/PL_MONAD.img/EP1/ACT2/close", 128)
sm.sendDelay(500)
sm.sendDelay(1000)
sm.lockInGameUI(False, True)
sm.warp(867201130)
