# id 402000521 (null), field 402000521
if not sm.hasHadQuest(34801):
    sm.lockInGameUI(True, False)
    sm.removeAdditionalEffect()
    sm.blind(True, 255, 0, 0, 0, 0)
    sm.sendDelay(300)
    sm.zoomCamera(0, 1000, 0, 0, -1500)
    sm.sendDelay(300)
    sm.spawnNpc(3001301, 93, 0)
    sm.showNpcSpecialActionByTemplateId(3001301, "summon", 0)
    sm.spawnNpc(3001300, -80, -92)
    sm.showNpcSpecialActionByTemplateId(3001300, "summon", 0)
    sm.zoomCamera(8000, 1000, 8000, 0, -70)
    sm.blind(False, 0, 0, 0, 0, 1000)
    sm.sendDelay(2000)
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.setColor(1)
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendNext("#face2#Wow! This place is huge!")
    sm.sendDelay(900)
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendNext("#face0#Sir, this is amazing! Many only dream of such a unique opportunity to attend the Crystal Academy. It's the best school for learning anything you want to know about mytocrystal magic.")
    sm.sendDelay(900)
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendNext("#face3#Ex...")
    sm.sendDelay(300)
    sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
    sm.sendNext("#face1#It's as he says. Students of the Crystal Academy are naturally talented with mytocrystals.")
    sm.sendSay("#face0#Illium, you do not fall into that category.")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face8#I know...")
    sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
    sm.sendSay("#face0#But yesterday's incident demonstrated that you may have a chance yet, unstable as your magic is.")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face0#Really?")
    sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
    sm.sendSay("#face0#I'm not certain yet. But if I am correct, and you are able to wield magic...")
    sm.sendSay("#face0#Well, I brought you here so that you can learn how to use your magic safely.")
    sm.sendSay("#face1#Class is starting. Here's a course schedule and map.")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#I have now saved the map to memory. You can open it with a hotkey.")
    sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
    sm.sendSay("#face1#Perhaps you should let your little friend guide you. Good luck!")
    sm.avatarOriented("UI/tutorial.img/illium/worldMap")
    sm.showNpcSpecialActionByTemplateId(3001301, "disappear", 0)
    sm.sendDelay(750)
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendNext("#face8#I can use magic?")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#The first class will begin soon. You should hurry.")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face4#Ah! I can't be late for my first class!")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#I'm detecting powerful magic from that column of crystals.\r\nIt will boost your jumps, so you can reach the higher floors.")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face0##bJump to move, jump to move#k... Okay...")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#I believe you can travel further if you use the #bCrystalline Wings#k skill\r\nafter you jump.")
    sm.createQuestWithQRValue(34801, "019=1;guide=1;hunt1=1;gate=1;020=1;021=1;exp=1")
    sm.setLevel(10)
    sm.setMaxHp(413)
    sm.setHp(413)
    sm.setMaxMp(381)
    sm.setMp(381)
    sm.moveCamera(True, 0, 0, 0)
    sm.lockInGameUI(False, True)
elif not sm.hasHadQuest(34806):
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.setColor(1)
    sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
    sm.sendNext("#face1#You did well today! Goodbye!")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face0#See you tomorrow!")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#Sir, you have completed today's schedule.")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face0#In that case, maybe I should go to the library for my assignment.")
    sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
    sm.sendSay("#face2#Help! Someone save me!")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face0#What's going on?")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#The sound seems to me coming from the classroom to your left. Would you like to go there now?")
    sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
    sm.sendSay("#face0#Yeah. Let's hurry.")
    sm.createQuestWithQRValue(34806, "scene=1;d=1;train=1;item1=1;item2=1;exp=1")
    sm.warp(402000532)