# id 867202309 (Abrup Basin : Skuas), field 867202309
sm.lockInGameUI(True, False)
sm.forcedFlip(True)
sm.spawnNpc(9400580, 1210, -40)
sm.showNpcSpecialActionByTemplateId(9400580, "summon", 0)
sm.spawnNpc(9400582, 1270, -40)
sm.showNpcSpecialActionByTemplateId(9400582, "summon", 0)
sm.spawnNpc(9400586, 1100, 0)
sm.showNpcSpecialActionByTemplateId(9400586, "summon", 0)
sm.spawnNpc(9400601, 1360, -40)
sm.showNpcSpecialActionByTemplateId(9400601, "summon", 0)
sm.spawnNpc(9400588, 900, 0)
sm.showNpcSpecialActionByTemplateId(9400588, "summon", 0)
sm.spawnNpc(9400587, 850, 0)
sm.showNpcSpecialActionByTemplateId(9400587, "summon", 0)
sm.spawnNpc(9400591, 800, 0)
sm.showNpcSpecialActionByTemplateId(9400591, "summon", 0)
sm.spawnNpc(9400592, 750, 0)
sm.showNpcSpecialActionByTemplateId(9400592, "summon", 0)
sm.spawnNpc(9400617, 700, 0)
sm.showNpcSpecialActionByTemplateId(9400617, "summon", 0)
sm.spawnNpc(9400596, 650, 0)
sm.showNpcSpecialActionByTemplateId(9400596, "summon", 0)
sm.spawnNpc(9400619, 600, 0)
sm.showNpcSpecialActionByTemplateId(9400619, "summon", 0)
sm.spawnNpc(9400618, 550, 0)
sm.showNpcSpecialActionByTemplateId(9400618, "summon", 0)
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(1200)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1400)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendNext("Goodness, is everything alright? What is everyone doing here? ")
sm.setInnerOverrideSpeakerTemplateID(9400588) # Ullan
sm.sendSay("Granny... Sniff... ")
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("Ullan? My child, why are you crying? ")
sm.moveNpcByTemplateId(9400586, True, 50, 50)
sm.sendDelay(500)
sm.sendDelay(1000)
sm.moveNpcByTemplateId(9400588, False, 50, 50)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(9400588) # Ullan
sm.sendNext("Sanaan... I'm so sorry... ")
sm.sendSay("Sorry for forcing you into the forest... and for not stopping you... and for what happened with Blanche... ")
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("... ")
sm.moveNpcByTemplateId(9400587, False, 50, 50)
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face1#Sanaan, I should have told you this a long time ago, but... I'm sorry. Very, truly sorry. ")
sm.sendSay("#face1#I thought I was making the best choice for the peace of the village, but it was an immature and foolish choice. ")
sm.sendSay("#face1#We had so many opportunities to set things right, but none of us bothered to do it. We've owed you this apology for ages. ")
sm.sendSay("#face1#I can't imagine how scared and lonely you and Blanche must have been out in that dark forest... I don't know how to ever make it up to you... ")
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("...That was all so long ago. I've moved on. But thank you for thinking of me and my Blanche. It's never too late to say you're sorry. ")
sm.setInnerOverrideSpeakerTemplateID(9400588) # Ullan
sm.sendSay("B-but what about Blanche? She's gone because we... ")
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("No, child. Dear Ullan, it's not your fault Blanche went missing. I've not once thought of blaming you for that. ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face1#I know it's too late, but... ")
sm.sendSay("#face1#Would you return with us to Kaptafel once this is all over, Sanaan? ")
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("...I appreciate it, but I can't leave that cabin until Blanche returns. ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face1#We've all lost family members. Over the past 6 months, we've searched for them , we've fallen into despair, and some have even tried to forget. ")
sm.sendSay("#face1#But of all of us, it was Aruhi that knew what really happened. We ignored him out of selfishness, but now that we know better, we will resume the search. ")
sm.sendSay("#face1#Sanaan... I want to make sure you see your Blanche again. I want to know you're safe. ")
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("I'll have to think about it. ")
sm.sendSay("Like you said... we have a common goal of finding our loved ones. ")
sm.sendSay("It's something all of us in Abrup have in common. ")
sm.sendSay("At the very least, we should work together more than we did before, eh? ")
sm.setInnerOverrideSpeakerTemplateID(9400588) # Ullan
sm.sendSay("Sanaan... I'm so sorry... ")
sm.sendSay("Sniff... I really, really am!")
sm.setInnerOverrideSpeakerTemplateID(9400586) # Sanaan
sm.sendSay("I know, child, I know. It's alright.")
sm.sendDelay(500)
sm.sendDelay(2000)
sm.setInnerOverrideSpeakerTemplateID(9400582) # Cayne
sm.sendNext("#face0#Chief Kan's getting better at apologizing, isn't he?")
sm.forcedFlip(True)
sm.flipNpcByTemplateId(9400580, False)
sm.setInnerOverrideSpeakerTemplateID(9400580) # Alika
sm.sendSay("#face4#...This isn't the time, Cayne.")
sm.setInnerOverrideSpeakerTemplateID(9400582) # Cayne
sm.sendSay("#face0#Right, of course. Perhaps we should give them some time to savor the moment, mm? We do have plenty on our plate, after all.")
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, 0, 0, 0)
sm.setParam(57)
sm.sendSay("#bOh right, the lost items!")
sm.sendDelay(500)
sm.flipNpcByTemplateId(9400582, False)
sm.flipNpcByTemplateId(9400580, False)
sm.sendDelay(1000)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400580) # Alika
sm.sendNext("#face0#Elva, #h0# went into the forest and located some more lost items. ")
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
sm.sendSay("Ah, I see. Well, you can certainly leave them with me. More than half of the items you left last time made their way to their rightful owners. ")
sm.setInnerOverrideSpeakerTemplateID(9400580) # Alika
sm.sendSay("#face1#Wow, really? That's great news! ")
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
sm.sendSay("Yes... and I noticed that among them was a necklace that belonged to Einar's sister. ")
sm.setParam(57)
sm.sendSay("#bEinar... that's the boy you were making armor for, right? ")
sm.setParam(37)
sm.sendSay("That's right. I was able to give him armor and his precious necklace all at once. It might have been a bit much, though. He cried... a lot. ")
sm.sendDelay(1000)
sm.sendNext("I hope he can turn things around... ")
sm.sendDelay(1000)
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.completeQuestNoCheck(64126)
sm.lockInGameUI(False, True)
sm.warp(867202310)
