BELLE = 2159314
ELEX = 2159312
BRIGHTON = 2159313
CLAUDINE = 2159315
J_AGENT = 2159344
BLACK_JACK = 2159345
CHECKY = 2159316
FERDI = 2159311

sm.lockInGameUI(True)
sm.curNodeEventEnd(True)

sm.removeEscapeButton()
sm.setSpeakerID(BELLE)
sm.sendNext("Y-you really have wings.")

sm.setSpeakerID(ELEX)
sm.sendSay("Who are you? Did the Black Wings send you as a spy? Actually, that wouldn't make sense...")

sm.setSpeakerID(BRIGHTON)
sm.sendSay("Keep your guard up. We still don't know what's going on.")

sm.setSpeakerID(CLAUDINE)
sm.sendSay("Who are you? What's your relationship with the Black Wings?")

sm.setPlayerAsSpeaker()
sm.sendSay("I have no idea who these Black Wings are. I've never heard of them. What do you want to know about me? I'm...not even sure where to begin...")

sm.setSpeakerID(J_AGENT)
sm.sendSay("Well, let's start with your name, your organization, your background... And, if you don't mind, I'd like to know about those wings on your back.")

sm.setPlayerAsSpeaker()
sm.sendSay("My name is #h0#. I am not currently part of any organization...though I was once one of the Black Mage's Commanders. I rebelled against him, and we fought, but he defeated me. When I awoke, I saw what the man in the hat described. Oh, and I was born with these wings. My father was a demon.")

sm.setSpeakerID(CLAUDINE)
sm.sendSay("Wait, wait, wait. You were a Commander under the Black Mage? How? He's been sealed for hundreds of years?")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 2000)
sm.sendDelay(600)

sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 1500, -90, -150)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 1500, 210, -150)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 1500, 100, -150)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 1500, -180, -100)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 1500, -260, -50)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 1500, 270, -50)
sm.sendDelay(1500)

sm.setSpeakerID(BELLE)
sm.sendNext("Sounds like someone has a screw loose to me. The experiments do that sometimes. One subject thought she was a violin.")

sm.setPlayerAsSpeaker()
sm.sendSay("(Hundreds of years ago? What are they talking about? But...this place is so strange. How long have I been asleep? And...could the Heroes have sealed the Black Mage?)")

sm.setSpeakerID(BELLE)
sm.sendSay("This makes no sense. What do you think, #p2159345#?")

sm.setSpeakerID(BLACK_JACK)
sm.sendSay("It's no lie. That doesn't mean our guest isn't just crazy, though.")

sm.setSpeakerID(CHECKY)
sm.sendSay("I'm with Black Jack on this one. Either our guest is crazy...or it's all true.")

sm.setSpeakerID(CLAUDINE)
sm.sendSay("If that's true, then our guest is from hundreds of years ago, back before the Black Mage was sealed. Wait, if you were a Commander, why did you rebel?")

sm.setPlayerAsSpeaker()
sm.sendSay("That is a personal matter. Now, since I answered your questions, you answer mine. Who are you people? And who are the Black Wings?")

sm.setSpeakerID(J_AGENT)
sm.sendSay("Like I said before, we're the Resistance. We're a group formed in secret to protect our home, the city of Edelstein, from the Black Wings. ")
sm.sendSay("Those nasty folks that were using you like a battery were the Black Wings. They invaded Edelstein a while back, and have been draining energy from the city. We don't know why, but we do know that they're working for the Black Mage.")

sm.setPlayerAsSpeaker()
sm.sendSay("They follow the Black Mage? I thought you said he was sealed.")

sm.setSpeakerID(ELEX)
sm.sendSay("He is. We think they're trying to find a way to release him again. And, to be fair, there have been a lot of recent events that hint that it could happen.")

sm.setPlayerAsSpeaker()
sm.sendSay("The Black Mage is returning? That's excellent news...")
sm.sendSay("That means I can still have my revenge!")

sm.setSpeakerID(BRIGHTON)
sm.sendSay("Okay...you're kinda crazy, but I can see we're on the same side.")

sm.startQuest(23279)
sm.setSpeakerID(FERDI)
sm.sendSay("If you want revenge on the Black Mage, why don't you join us?")

sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1500, -90, -150)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1500, 210, -150)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1500, 100, -150)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1500, -180, -100)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1500, -260, -50)
sm.showEffectOnPosition("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1500, 270, -50)

sm.setSpeakerID(CLAUDINE)
sm.sendSay("Headmaster, what are you saying...?")

sm.setSpeakerID(BRIGHTON)
sm.sendSay("Are you crazy? This is obviously a trap! And even if it isn't, we'd be fools to trust a Commander of the Black Mage!")

sm.setSpeakerID(FERDI)
sm.sendSay("Well... I'm happy to see everyone together on this! Ha!")
sm.sendSay("I trust #p2159345#'s judgment, and besides, we can use all the help we can get. Even if our new friend #bused to be#k a Commander, this is clearly no longer the case.")

sm.setSpeakerID(ELEX)
sm.sendSay("Besides, better to have the Commander here with us than with the Black Wings.")

sm.setSpeakerID(FERDI)
sm.sendSay("We can always use more members. As long as our goals are the same, we can work together.")

sm.setPlayerAsSpeaker()
sm.sendSay("W-wait, what's going on? I'm still trying to catch up to the story here!")

sm.setSpeakerID(BELLE)
sm.sendSay("There's no need to catch up. The decision has been made. If you want to fight the Black Mage, you'll have to go through the Black Wings, and you'll run into them the moment you leave this place. We have common enemies. Let's work together to bring them down!")

sm.setSpeakerID(CHECKY)
sm.sendSay("Caution is good. I don't expect you to fully trust us yet. We can work on that as we take the Black Wings apart, piece by piece.")

sm.setPlayerAsSpeaker()
sm.sendSay("True... Very well. I will join you, for now.")
sm.sendSay("I realize this is overdue, but...allow me to thank you for saving me.")

sm.setSpeakerID(J_AGENT)
sm.sendSay("You're quite welcome. Hearing that is a relief...I've never been betrayed by someone who thanked me.")

sm.setPlayerAsSpeaker()
sm.sendSay("I am loyal to those who are loyal to me.")

sm.setSpeakerID(FERDI)
sm.sendSay("Works for me. All right, make yourself at home. Our secret base is your secret base, and all that.")

sm.createQuestWithQRValue(23209, "1", False)
sm.completeQuest(23279)
sm.deleteQuest(23279)
sm.lockInGameUI(False)
sm.warpInstanceIn(931050040, 0)