# id 64114 ([MONAD: The First Omen] Elva's Role), field 867202300
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
sm.sendNext("#h0#, you're back. Did you get the ingredients?")
sm.setParam(57)
sm.sendSay("#bI sure did. Here you go!")
sm.setParam(37)
sm.sendSay("Eyeful antennae... one, two, three... Optusa leaves... It's all there!")
res = sm.sendAskYesNo("Thank you. Oh, and did you bring me my notes? You can keep them if you like, I made copies.")
sm.completeQuestNoCheck(parentID)
