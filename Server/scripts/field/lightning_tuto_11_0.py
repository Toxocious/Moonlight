LANIA = 1032201
PENNY = 1032202

sm.lockInGameUI(True)
sm.showFieldEffect("lightning/screenMsg/1")
sm.forcedInput(0)

sm.spawnNpc(LANIA, 230, -130)
sm.showNpcSpecialActionByTemplateId(LANIA, "summon", 0)

sm.spawnNpc(PENNY, 234, -400)
sm.showNpcSpecialActionByTemplateId(PENNY, "summon", 0)
sm.sendDelay(2000)

sm.removeEscapeButton()
sm.setSpeakerID(LANIA)
sm.sendNext("You remember everything I said, right?")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Fresh milk, three servings of pork, fishing bait, and...")

sm.setSpeakerID(LANIA)
sm.sendSay("Thread! I need thread. We're going to be freezing if I don't have time to knit us scarves and socks.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Of course, of course. Red and white thread, yes. I apologize. My headaches...")
sm.sendSay("(My head aches more with each passing day, as if a fire were burning in my mind...)")

sm.setSpeakerID(PENNY)
sm.sendSay("Mrow! Need me to scratch the shopping list into your arm?")

sm.setSpeakerID(LANIA)
sm.sendSay("Don't be mean, Penny! And you! Don't forget my stuff this time. I don't want my feet to freeze off!")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I will return with all you need, Lania. I swear it.")

sm.setSpeakerID(LANIA)
sm.sendSay("I'll walk you out. Penny, you guard the house, okay?")

sm.setSpeakerID(PENNY)
sm.sendSay("Mrow! Lania this, Lania that! What about little old me?")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("When I return, I swear I will catch you the king of all fish, Penny.")

sm.setSpeakerID(PENNY)
sm.sendSay("Really?! I mean... you can't sway me that easily. Just... it better be a really fat one.")

sm.setSpeakerID(LANIA)
sm.sendSay("I'm sure it'll be the biggest fish in the lake, Penny. Let's go, Luminous!")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Y-yes. Let's. (My chest... It feels so tight...)")

sm.forcedInput(1)
sm.moveNpcByTemplateId(LANIA, True, 400, 100)