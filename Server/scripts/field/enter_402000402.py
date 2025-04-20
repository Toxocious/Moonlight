# id 402000402 (null), field 402000402
if not sm.hasHadQuest(34940):
    sm.lockInGameUI(True, False)
    sm.removeAdditionalEffect()
    sm.zoomCamera(0, 1000, 0, -494, 0)
    sm.changeBGM("Bgm00.img/Silence", 0, 0)
    sm.forcedAction(25, 999999)
    sm.sendDelay(1000)
    sm.createFieldTextEffect("#fnﾳﾪﾴﾮﾰ￭ﾵ￱ ExtraBold##fs18#A Little Later, Savage Terminal", 100, 1000, 6, -50, -50, 1, 4, 0, 0, 0)
    sm.zoomCamera(3000, 1000, 3000, -494, 668)
    sm.sendDelay(3500)
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.setColor(1)
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendNext("#face1#Where is this? It's so familiar... I guess that means I've still got my memories.")
    sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
    sm.sendSay("#face0#You're awake! We escaped and made it here to Savage Terminal.")
    sm.forcedAction(2, 999999)
    sm.sendDelay(1000)
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendNext("#face1#Ugh...")
    sm.sendDelay(1000)
    sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
    sm.sendNext("#face0#The moment we lost your signal, Ark, the monsters came at us full force.")
    sm.sendSay("#face2#We barely managed to get the crystal going and had to guess at how to operate the airship.")
    sm.blind(True, 255, 0, 0, 0, 500)
    sm.sendDelay(500)
    sm.sendDelay(1000)
    sm.onLayer(500, "d0", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/12/0", 4, True, -1, False)
    sm.setInnerOverrideSpeakerTemplateID(3001508) # Zippy
    sm.sendNext("#face2#It was a really close call, but we made it!")
    sm.sendSay("#face3#It was so scary!")
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendSay("#face2#!?")
    sm.setInnerOverrideSpeakerTemplateID(3001508) # Zippy
    sm.sendSay("#face3#It felt like the planet's life energy was being drawn right out of the land...")
    sm.sendSay("#face2#Thank you for protecting all of us, Ark.")
    sm.sendDelay(1000)
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendNext("#face8#(So they were just there to steal more power from the planet. But why?)")
    sm.offLayer(300, "d0", False)
    sm.sendDelay(1000)
    sm.blind(True, 255, 0, 0, 0, 0)
    sm.sendDelay(1200)
    sm.blind(False, 0, 0, 0, 0, 1000)
    sm.sendDelay(1400)
    sm.setInnerOverrideSpeakerTemplateID(3001509) # Salvo
    sm.sendNext("#face3#Yeah, thanks! It's not exactly the paradise we're looking for, but this place is pretty great!")
    sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
    sm.sendSay("#face4#Yeah, just look at all the amazing scrap here that I can use for my research!")
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendSay("#face2#So, what's this paradise you're looking for anyway?")
    sm.setInnerOverrideSpeakerTemplateID(3001509) # Salvo
    sm.sendSay("#face4#Oh, right! We never really explained, did we?")
    sm.setInnerOverrideSpeakerTemplateID(3001508) # Zippy
    sm.sendSay("#face0#It's less about where it is and more about what it is.")
    sm.sendSay("#face0#It's a place where we don't have to worry.")
    sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
    sm.sendSay("#face4#Somewhere we can go treasure hunting every day!")
    sm.setInnerOverrideSpeakerTemplateID(3001509) # Salvo
    sm.sendSay("#face2#It's a place where people understand my music!")
    sm.setInnerOverrideSpeakerTemplateID(3001513) # Grit
    sm.sendSay("#face0#And it has lots of hot springs!")
    sm.setInnerOverrideSpeakerTemplateID(3001512) # Digs
    sm.sendSay("#face0#Yeah! And lots of puffy clouds too!")
    sm.sendDelay(1000)
    sm.setInnerOverrideSpeakerTemplateID(3001511) # Mar
    sm.sendNext("#face2#And a shiny, sparkly night sky!")
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendSay("#face9#Haha, that sounds pretty great. I'd love to see it someday.")
    sm.sendDelay(1000)
    sm.setInnerOverrideSpeakerTemplateID(3001508) # Zippy
    sm.sendNext("#face0#What are you going to do, Ark?")
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendSay("#face3#There's something I still need to take care of.")
    sm.sendSay("#face3#I'm going to gather more information here and leave.")
    sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
    sm.sendSay("#face0#But we only just got here! And we like you!")
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendSay("#face0#Don't worry. I've still got some time here. And if you need my help at all before I go, I'd be happy to.")
    sm.changeBGM("Bgm47.img/HuntingGround", 0, 0)
    sm.createQuestWithQRValue(34940, "dir=2;enter=1;exp=1")
    sm.showFadeTransition(0, 1000, 3000)
    sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
    sm.moveCamera(True, 0, 0, 0)
    sm.sendDelay(300)
    sm.removeOverlapScreen(1000)
    sm.moveCamera(True, 0, 0, 0)
    sm.lockInGameUI(False, True)
    sm.createQuestWithQRValue(34995, "00=h1;01=h0;10=h0;02=h0;11=h0;12=h0;04=h0;13=h0;05=h0;14=h0;23=h0;06=h0;15=h0;24=h0;07=h0;16=h0;17=h0;18=h1;09=h0")
    sm.createQuestWithQRValue(34995, "00=h1;01=h0;10=h0;02=h0;11=h0;12=h0;04=h0;13=h0;05=h0;14=h0;23=h0;06=h0;15=h0;24=h0;07=h0;16=h0;17=h0;18=h1;09=h0;19=h1")
    sm.createQuestWithQRValue(34995, "00=h1;01=h0;10=h0;20=h1;02=h0;11=h0;12=h0;04=h0;13=h0;05=h0;14=h0;23=h0;06=h0;15=h0;24=h0;07=h0;16=h0;17=h0;18=h1;09=h0;19=h1")
elif not sm.hasHadQuest(34943):
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.setColor(1)
    sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
    sm.sendNext("#face0#(Everyone is gone, and there's nothing left to do here. Maybe I should take a look around.)")
    sm.createQuestWithQRValue(34943, "dir=1;exp=1")
