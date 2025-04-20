# id 58967 ([Hieizan Temple] The Demon King Returns), field 811000029
sm.lockInGameUI(True, True)
sm.setSpeakerID(9130116) # Princess No
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(9130122) # Dark Voice
sm.sendSay("Was it you who summoned me...")
sm.sendSay("...You're quite weak to summon one such as myself.   ")
sm.sendSay("I will not accept such a weak body... But I will reward you for summoning me... Muahaha.  ")
sm.sendSay("My wickedness shall strengthen your body.   ")
sm.sendDelay(2900)
sm.reservedEffect(False, 0, 0, "Effect/DirectionJP3.img/sengoku/Scene0")
sm.completeQuestNoCheck(parentID)
sm.startQuest(58949)
sm.createQuestWithQRValue(58974, "m035=clear;dr1=clear;dr2=clear;m037=clear;m038=clear;m039=clear")
sm.lockInGameUI(False, True)
sm.createQuestWithQRValue(18418, "B=35643")
