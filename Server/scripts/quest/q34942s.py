# id 34942 (Separate Ways 2), field 402000402
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001509) # Salvo
sm.sendNext("#face3#This is a wonderful place with lots of things to eeeat! \r\nIt's the perfect chance to get some tasty treeeats!")
sm.setSpeakerType(4)
sm.setSpeakerID(3001419) # Salvo
res = sm.sendAskAccept("#face2#The #i4036351# #b#t4036351##k you can get from #o2400308# monsters in #rNutria Gutter 2#k seems to be quite a delicacy! Can you bring about #b30#k?")
sm.setSpeakerType(3)
sm.sendNext("#face2#If we dry and preserve it, we'll have food to last us for ages. See you soon!")
sm.startQuest(parentID)
