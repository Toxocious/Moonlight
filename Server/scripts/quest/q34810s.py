# id 34810 (Carnelian's Request), field 402000513
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendNext("#face2#Well... Dean turned all of my #i4036167# #t4036167# items into candy!")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face0#What? That kid's a menace!")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face2#Sniff... yeah.")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face2#That's just...")
sm.sendSay("#face4#mean!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face9#I ended up with some extras. Would you like them?")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face0#You would do that for me?")
res = sm.sendNext("#face0#That would be so helpful... Do you maybe have 5 to spare?\r\n#L0# #bChoice 1 : Give only 1.#l\r\n#L1# #bChoice 2 : Give 4.#l\r\n#L2# #bChoice 3 : Give 7.#l")
sm.sendNext("#face1#Wow, 7? Thanks a lot!")
sm.createQuestWithQRValue(parentID, "give=7")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face1#Want to maybe trade for some of that candy?")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face1#Huh? Oh.\r\nOf course. Here you go.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face1#Wow, thanks!")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face0#Um... you're welcome.")
sm.sendSay("#face1#I REALLY appreciate your help, you two. See you later!")
sm.startQuest(parentID)
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(parentID, "exp=1;give=7")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/31")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#Sir, you need 20 #i4036167# #t4036167# to finish your class.")
sm.createQuestWithQRValue(16700, "count=155;date=20190221")
sm.createQuestWithQRValue(16700, "count=156;date=20190221")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#It's all right. I'll just go back for more.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#You will find more #i4036167# #t4036167# in #m402000514#, #m402000515#, and #m402000516#.")
sm.lockInGameUI(False, True)
