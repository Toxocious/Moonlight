SKIPPER = 1096000

sm.removeEscapeButton()

sm.setSpeakerID(SKIPPER)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face03")
sm.sendNext("So, why do you want to go to Maple Island, anyway? Not many people head that way these days. Judging by your clothes, you're not a tourist, either.")

sm.setPlayerAsSpeaker()
sm.sendSay("I'm going to Maple Island for training. After that, I'm headed to Victoria Island to become a great adventurer! ...That's how it works, right?")

sm.setSpeakerID(SKIPPER)
sm.sendSay("It sure does! Maple Island is a great place to train, since there are no dangerous monsters there. Plus, you'll meet plenty of newbies like yourself to make friends with. Once you're comfortable with the basics, Victoria Island is where you learn to shine. And then, when you're ready, there's a big, wide world out there for you to explore! Ahh, how I envy you!")

sm.setPlayerAsSpeaker()
sm.sendSay("Heh, I can't wait! I'm gonna train really hard, and learn to take down all of the most powerful monsters. I've been studying to be a great Explorer. I'm completely prepared!")

sm.setSpeakerID(SKIPPER)
sm.sendSay("What a great attitude you have! That's the kind of thing that will help you succeed. Of course, you can't ever be sure of what will happen in the future. Just remember, #beverything happens for a reason#k. ")
sm.sendSay("Hey...did you hear something? Huh, I just had the weirdest feeling. I know there are no monsters out here on the ocean, but you might want to be careful anyway. ")

sm.ballonMsg("What are you talking about? I don't feel anything...")
sm.forcedInput(2)
sm.curNodeEventEnd(True)