# id 34477 ([Arcana] The Evil Within), field 450005000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003350) # Small Spirit
sm.sendNext("(Sobs) The Spirit Tree has been completely engulfed by the evil aura! I don't understand... We revived the Songblooms! The tree should have been restored. Why is this happening?")
sm.sendSay("Ahh! The evil in the tree... It feels like it's trying to spread!")
sm.setSpeakerType(4)
sm.setSpeakerID(3003306) # Small Spirit
sm.setParam(2)
res = sm.sendAskAccept("#b(There are no other options left. Will you confront the evil infecting the Spirit Tree?)#k")
sm.setParam(3)
sm.sendNext("#b(The Spirit Tree is coursing with evil energy... this can't be good.)#k")
sm.createQuestWithQRValue(34451, "clearB=0;clear=1")
sm.warp(940200260)
