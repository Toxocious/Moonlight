# id 64063 ([MONAD: The First Omen] Hawalu's Song), field 867201100
sm.showNpcSpecialActionByTemplateId(9400591, "summon", 0)
sm.lockInGameUI(True, False)
sm.sendDelay(300)
sm.moveNpcByTemplateId(9400591, True, 40, 50)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400591) # Shulla
sm.sendNext("Hawalu! ")
sm.flipNpcByTemplateId(9400593, False)
sm.sendSay("I'm so sorry, #h0#. Was Hawalu bothering you?")
sm.setParam(57)
sm.sendSay("#bNot at all. In fact, Hawalu was singing to cheer me up.")
sm.showEffect("Effect/OnUserEff.img/emotion/flower", 2000, 0, 0, 0, 33531306, 0, 0)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400593) # Hawalu
sm.sendSay("That's right, Mommy! I sang! ")
sm.setInnerOverrideSpeakerTemplateID(9400591) # Shulla
sm.sendSay("Thank you, #h0#. I know that makes Hawalu happy too.")
sm.lockInGameUI(False, True)
sm.completeQuestNoCheck(parentID)
