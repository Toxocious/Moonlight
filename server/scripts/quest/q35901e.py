# id 35901 (Find Altar Key 2), field 910090302
sm.createQuestWithQRValue(63369, "chk=2;day=0")
sm.createQuestWithQRValue(63369, "chk=2;day=1")
sm.completeQuestNoCheck(63360)
sm.setSpeakerType(3)
sm.setParam(548)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face0##g'A temperamental dancer / best viewed with distant gaze,'\r\n'In crimson does it pirouette / in black its footfall stays.'#k")
sm.sendSay("#face1#Oooh, ancient poetry. Which is ALSO a riddle. Score! Now that we've got the basic rhyme scheme, the next verse would go like so...")
sm.sendSay("#face0##b(You deciphered the ancient script on the box. It's written with a figurative lilt, as ancient verse is wont to do, but after mulling over it a while, you think you have a good idea what it's referring to.)#k")
sm.completeQuestNoCheck(parentID)
sm.startQuest(11620)
