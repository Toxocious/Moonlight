sm.setSpeakerID(1013000)
sm.sendNext("I'm finally here! *inhales* Ah, this must be air I'm breathing. And that, that must be the sun! And that, a tree! And that, a plant! And that, a flower! Woohahahaha! This is incredible! This is much better than I imagined the world to be while I was trapped inside the egg. And you... Are you my master? Hm, I pictured you differently.")

sm.setPlayerAsSpeaker()
sm.sendSay("#bWhoooooa, it talks!")

sm.setSpeakerID(1013000)
sm.sendSay("My master is strange. I guess I can't do anything about it now, since the pact has been made. *sigh* Well, good to meet you. We'll be seeing a lot of each other.")

sm.setPlayerAsSpeaker()
sm.sendSay("#bEh? What do you mean? We'll be seeing a lot of each other? What pact?")

sm.setSpeakerID(1013000)
sm.sendSay("What do you mean what do I mean?! You woke me from the Egg. You're my master! So of course it's your responsibility to take care of me and train me and help me become a strong Dragon. Obviously!")

sm.setPlayerAsSpeaker()
sm.sendSay("#bWhaaat? A Dragon? You're a Dragon?! I don't get it... Why am I your master? What are you talking about?")

sm.setSpeakerID(1013000)
sm.sendSay("What are YOU talking about? Your spirit made a pact with my spirit! We're pretty much the same person now. Do I really have to explain? As a result, you've become my master. We're bound by the pact. You can't change your mind... The pact cannot be broken.")

sm.setPlayerAsSpeaker()
sm.sendSay("#bWait, wait, wait. Let me get this straight. You're saying I have no choice but to help you?")

sm.setSpeakerID(1013000)
sm.sendSay("Yuuup! Heeeey...! What's with the face? You...don't want to be my master?")

sm.setPlayerAsSpeaker()
sm.sendSay("#bNo... It's not that... I just don't know if I'm ready for a pet.")

sm.setSpeakerID(1013000)
sm.sendSay("A p-p-pet?! Did you just call me a pet?! How dare... Why, I'm a Dragon! The strongest being in the world!")

sm.setPlayerAsSpeaker()
sm.sendSay("#b...#b(You stare at him skeptically. He looks like a lizard. A puny little one, at that.)#k")

sm.setSpeakerID(1013000)
if sm.sendAskAccept("Why are you looking at me like that?! Just watch! See what I can do with my power. Ready?"):
    if not sm.hasQuest(parentID):
        sm.startQuest(parentID)

    sm.removeEscapeButton()
    sm.sendNext("Attack the #r#o1210100##k! It's time to awaken my true power and prove myself as a dragon! Chaaaarge!")
    sm.sendSay("HOLD IT! Before we rush in, did you already distribute your AP? Magic is affected by #bINT and LUK#k! So make sure you distribute your AP, and put on some #bMagician gear#k before you rush into battle!")
    sm.sendSayImage("UI/tutorial/evan/11/0")
    sm.dispose()
else:
    sm.sendNext("You don't believe me? Grrrrr, you're getting me mad!")
    sm.dispose()