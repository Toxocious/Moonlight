# 140000000
LILIN = 1201000

sm.setSpeakerID(LILIN)
sm.sendNext("Alright, I've done enough explaining for now. Let's move on to the next stage. What's the next stage, you ask? I just told you. Train as hard as you can until you become strong enough to defeat the Black Mage with a single blow.")
sm.sendSay("You may have been a hero in the past, but that was hundreds of years ago. Even if it weren't for the curse of the Black Mage, all those years you spent frozen in time have stiffened your body. You must loosen up a bit and slowly regain your agility. How do you do that, you ask?")

if sm.sendAskAccept("Don't you know that you must first master the fundamentals? So the wise thing to do is to begin with #bBasic Training#k. Oh, of course, I forgot that you lost your memory. Well, that's why I'm here. You'll just have to experience it yourself. Shall we begin?"):
    sm.startQuest(parentID)

    sm.removeEscapeButton()
    sm.sendNext("The population of Rien may be mostly Penguins, but even this island has monsters. You'll find #o0100131#s if you go to #b#m140020000##k, located on the right side of the town. Please defeat #r10 of those #o0100131#s#k. I'm sure you'll have no trouble defeating the #o0100131#s that even the slowest penguins here can defeat.")
else:
    sm.sendNext("What are you so hesitant about? You're a hero! You gotta strike while the iron is hot! Come on, let's do this!")