# Quest [Familiar] Roro and me

RORO_AND_ME_QUEST = 64997

RORO = 9133403
MYSTERIOUS_MAN = 9133400


def talkChar(message):
    sm.setPlayerAsSpeaker()
    sm.sendNext(message)
    sm.resetParam()


def talkRoro(message):
    sm.setSpeakerID(RORO)
    sm.sendNext(message)


def talkMysteriousMan(message):
    sm.setSpeakerID(MYSTERIOUS_MAN)
    sm.sendNext(message)


sm.lockInGameUI(True)

talkRoro("Rorororo...")
talkRoro("AHEM! Rorororororororo!!")
talkChar("(There's something in your pocket. You're not sure what it is, but it sure is making a lot of noise...)")
talkChar("(You reach into your pocket to retrieve the mysterious object.)")
talkRoro("Ahhh! Finally! I thought you were gonna leave me to suffocate in your stinky, sticky pocket forever!")
talkChar("My pocket isn't stinky! Anyway, where did you come from?!")
talkRoro(
    "Allow me to introduce myself. I'm Roro! Half AI supercomputer, half Familiar Codex, I'm the best partner a Familiar collector could ask for!")
talkChar("I don't need a partner...")
talkRoro("Who said I was talking about YOU? Anyway, I'm getting a call from my actual partner. Go on, pick up!")
talkChar("Pick up what?")
talkRoro("Did I not mention that I'm also a video phone? Just press the button on my hand!")
talkMysteriousMan(
    "Greetings, Chosen One! I am sure you're brimming with questions, but time is short, so listen closely.")
talkChar("(Now those are some weird threads. This guy must not be from around here.)")
talkMysteriousMan(
    "I am a humble researcher, I gather data on monsters from throughout the multiverse an develop new user for their many abilities.")
talkMysteriousMan(
    "As you can probably tell, I'm calling you from another world. I've scoured many realms in search of a Chosen One like you.")
talkChar("Hold up. Why would a hotshot alien like you be interested in the monsters from my world?")
talkMysteriousMan(
    "My speciality is Familiars, and the monsters in your world are... let's say that they're specially suited for my line of work.")
talkChar("Ooookay. What do you mean by 'Chosen One', though?")
talkMysteriousMan("...")
talkMysteriousMan("You're the one that I've chosen. Now, would you like to hear my proposal, Chosen One?")
talkChar("You sound completely insane, but that's ever sopped me before. Go ahead.")
talkMysteriousMan(
    "I'd like to offer a deal. You help me with my research. In return, I'll help you become even stronger.")
talkMysteriousMan("What do you say? I promise you won't regret it.")
talkChar("(Something about this guy seems off, but who are you to turn down a chance to get stronger?)")
talkChar("Fine, I'll work with you.")
talkMysteriousMan("I knew you would. I shall have my assistant call you son with further details. We'll be in touch.")
sm.completeQuestNoRewards(64997)
sm.lockInGameUI(False)
sm.dispose()
