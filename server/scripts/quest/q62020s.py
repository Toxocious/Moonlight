# [Shaolin Temple] Say My Name 3

GOBLIN_DEMON = 9310578
BLUE_DEMON = 9310577
NINE_TAILED_FOX = 9310579
selectName = ["Aharon", "Baruch", "Binyamin"]


sm.removeEscapeButton()
sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("Wll, I've put on the mask and noth-- Hey... What is this feeling coming over me? Humans... HUMANS ARE GREAT!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("I can't believe that worked. Anyway, #b#p"+ str(GOBLIN_DEMON) +"##k, a human sympathiser like you deserves a good #rNickname#k.")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("What's a #rNickname#k?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("It's a name that I can use to address you. Just like my name is #r#h0##k.")

sm.sendNext("I want YOU to have a name. That way I don't have to use your demon name, which is really just the name of your species.")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("If it's a human thing, I'm in!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
text = "Hmm, what name would suit you best? I think you'd be perfect as a...\r\n"
for i in range(len(selectName)):
    text += "#L"+ str(i) +"#"+ selectName[i] +"\r\n"
selection = sm.sendNext(text)

sm.createQuestWithQRValue(62018, selectName[selection])

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("Hey, that sounds good! "+ sm.getQRValue(62018) +" the almost-human demon. Perfect!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Cool, I'll call you #r"+ sm.getQRValue(62018) +" from now on!")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("I feel like a whole new person!")

sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(1010226)


sm.warpInstanceIn(701220350)
sm.lockInGameUI(True, False)
sm.sendDelay(1000)

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("I suppose some humans are all right.")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("Oh, yes. I've learned so much about them.")

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("So, #b#h0##k. You said you wanted to check the bookshelf?\r\n"
            "You're free to do so whenever you like.")

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("I love holding books and pretending to read them. It makes me look cute!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Hey, I was wondering... What are you guys doing here, anyway? In the human world, I mean.")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("My memory about it is pretty hazy... I woke up one day and found myself here.")

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("Something scooped me up from my snuggly home and dropped me here. I think I heard someone talking... A human.")

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("Demons are born of human passion and imagination.\r\n"
            "Our entire world is fueled by it.")

sm.sendNext("The fervor with which you worship your gods... "
            "Even that shapes our world. but when you do evil in the name of your gods, it warps up.")

sm.sendNext("Because of humanity's corruption, \r\n"
            "OUR world has become horribly malformed.")

sm.sendNext("The only conclusion I can draw is that a human \r\n"
            "with special powers summoned us here for some evil purpose...")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("But who?")

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("Maybe they'll come find us soon! We're like their babies, sorta!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(A demon world formed by human passions? A person who can summon demons? "
            "It's a lot to swallow, but the evidence is kind of undeniable. "
            "Who could be responsible for all of this?")

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("Anyway, go on and search the bookshelf now.")

sm.warpInstanceOut(701220350)
sm.lockInGameUI(False)