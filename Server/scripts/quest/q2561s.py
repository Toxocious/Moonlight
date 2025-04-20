MONKEY = 1096003

sm.setSpeakerID(MONKEY)
sm.sendNext("Ook, ook! Oook! Ook! Ook! Ook?!")

sm.setPlayerAsSpeaker()
sm.sendSay("I remember...I was on my way to Maple Island, to become an Explorer... What happened? What's going on?")

sm.setSpeakerID(MONKEY)
sm.sendSay("Oook! Ook! Ook! Oooook!")

sm.setPlayerAsSpeaker()
sm.sendSay("I was talking to the captain, and admiring the scenery, and... Balrog! Balrog attacked the ship! So... Did I fall overboard? Then, why am I alive? I know #bI can swim#k, but can I swim while unconscious? Maybe I can. Maybe I'm a natural swimmer!")

sm.setSpeakerID(MONKEY)
sm.sendSay("Ooook! Ook! Ook! (Huh, a little monkey...tapping its foot angrily. Actually, when I first woke up, that monkey was the only thing I saw...)")

sm.setPlayerAsSpeaker()
sm.sendSay("Huh? Why are you waving your arms like that? Are you trying to tell me something? (The monkey took an apple out of the nearby chest. It looks delicious. But, what is he trying to tell you?)\r\n\r\n#i2010000#")

sm.setSpeakerID(MONKEY)
if sm.sendAskAccept("Ook ook! Om nom nom! (The monkey looks frustrated that you don't understand him. He pretends to eat the apple. Wait, does he want YOU to eat it? That must be it! What a nice monkey.)"):
    sm.giveItem(2010000)
    sm.startQuest(parentID)
    sm.setPlayerAsSpeaker()
    sm.sendNext("(You have received a delicious-looking apple. You should eat it. Now...how do you open your Inventory? Was it the #bI#k key...?)")
    sm.avatarOriented("UI/tutorial.img/cannon/2")
else:
    sm.setPlayerAsSpeaker()
    sm.sendNext("The thing is, I don't like apples... Sorry, but no thanks.")
    sm.dispose()