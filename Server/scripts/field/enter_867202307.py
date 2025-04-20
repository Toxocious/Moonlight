# id 867202307 (Abrup Basin : Skuas), field 867202307
sm.lockInGameUI(True, False)
sm.spawnNpc(9400597, -270, -80)
sm.showNpcSpecialActionByTemplateId(9400597, "summon", 0)
sm.forcedFlip(True)
sm.spawnNpc(9400632, -450, -80)
sm.showNpcSpecialActionByTemplateId(9400632, "summon", 0)
sm.spawnNpc(9400633, -520, -80)
sm.showNpcSpecialActionByTemplateId(9400633, "summon", 0)
sm.spawnNpc(9400583, -150, -80)
sm.showNpcSpecialActionByTemplateId(9400583, "summon", 0)
sm.spawnNpc(9400585, -100, -80)
sm.showNpcSpecialActionByTemplateId(9400585, "summon", 0)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendNext("#face1#Aughh, I'm so tired! ")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#Good work! ")
sm.setParam(57)
sm.sendSay("#bThat was great! You really got me! ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400585) # Afinas Soldier
sm.sendSay("Us too! ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Really? Eh heh heh! ")
sm.spawnNpc(9400586, 150, -180)
sm.showNpcSpecialActionByTemplateId(9400586, "summon", 0)
sm.spawnNpc(9400601, 220, -180)
sm.showNpcSpecialActionByTemplateId(9400601, "summon", 0)
sm.spawnNpc(9400590, 300, -180)
sm.showNpcSpecialActionByTemplateId(9400590, "summon", 0)
sm.moveNpcByTemplateId(9400586, True, 250, 70)
sm.moveNpcByTemplateId(9400601, True, 250, 70)
sm.moveNpcByTemplateId(9400590, True, 250, 70)
sm.sendDelay(2000)
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendNext("#h0#, I must tell you... Our food supplies are nearly gone. ")
sm.sendSay("I couldn't find Chief Birna, so I'm telling you first. At this rate, we won't last much longer. ")
sm.setParam(57)
sm.sendSay("#bThat's no good. We'd better go hunting and restock before we run out. ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#You should go with them, Chief Gurnardson. The monsters you'll face out there will be good practice after sparring with us. ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#I-is that so? ")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#It's a great opportunity to show off, you know... ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Really? Hmm...")
sm.sendSay("#face0#You say we've about run out of food already? ")
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
sm.sendSay("Yes. With a group of this size, our supplies have... depleted faster than expected. ")
sm.sendSay("Even on the thinnest rations, we only have about two days before we run out. ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Ahem, so... this is a very urgent matter, yes? ")
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
sm.sendSay("I would consider mass starvation rather urgent, yes. ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Well then, we'll take care of the situation! ")
sm.setParam(57)
sm.sendSay("#bReally? ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
sm.sendSay("Ah, that would be wonderful. I don't think I could restock on my own... ")
sm.setInnerOverrideSpeakerTemplateID(9400590) # Slaka
sm.sendSay("#face0#... ")
sm.moveNpcByTemplateId(9400590, True, 200, 70)
sm.sendDelay(2000)
sm.sendNext("#face0#Since Chief Gurnardson isn't familiar with the area, I'll lead the way! ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#...You? ")
sm.setParam(57)
sm.sendSay("#bSlaka, are you sure? I mean, I was going to ask... ")
sm.sendDelay(1000)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400590) # Slaka
sm.sendNext("#face0#Ha, of course you were! No one could handle this task but me, so I have a duty to help you!")
sm.flipNpcByTemplateId(9400590, False)
sm.sendDelay(250)
sm.showEffect("Effect/OnUserEff.img/emotion/love", 2000, 0, 0, 0, 34622892, 0, 0)
sm.sendDelay(2000)
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
sm.sendNext("Everyone... thank you for stepping up. ")
sm.sendDelay(250)
sm.showEffect("Effect/OnUserEff.img/emotion/love", 2000, 0, 0, 0, 34622892, 0, 0)
sm.sendDelay(250)
sm.flipNpcByTemplateId(9400590, True)
sm.sendDelay(250)
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendNext("#face0#N-no, I think our group is perfectly capable already! #h0#? Lady Gillie? You're coming with us, aren't you? ")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#I don't think I can. I have another training session to run. ")
sm.setParam(57)
sm.sendSay("#bI'll be coming with you, of course, but I don't know the area like Slaka. ")
sm.sendSay("#bSince he's both a scout and a hunter, I'd feel much better if Slaka came along. ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400590) # Slaka
sm.sendSay("#face0#Ha, you hear that? Heh heh. Uh, I mean... ")
sm.sendSay("#face0#Did you hear that, Chief Gurnardson? They'll feel MUCH better! ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Mmm... Ahem! ")
sm.setParam(57)
sm.sendSay("#bShall we get started? ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("Good luck out there. And focus on fighting the monsters, not each other! ")
sm.setInnerOverrideSpeakerTemplateID(9400590) # Slaka
sm.sendSay("#face0#Don't you worry! ")
sm.sendSay("#face0#Heh heh, off we go! ")
sm.moveNpcByTemplateId(9400590, True, 300, 100)
sm.sendDelay(1000)
sm.completeQuestNoCheck(64120)
sm.startQuest(64121)
sm.startQuest(64158)
sm.lockInGameUI(False, True)
sm.warp(867202660)
