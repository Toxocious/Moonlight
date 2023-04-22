# Shade Into [A New Name]
SILVER = 3002005
MOONBEAM = 3002000
SWIFT_STRIKE = 25001000
VULPES_LEAP = 25001204

sm.setSpeakerID(SILVER)
sm.sendNext("So, your name is Human, right? That's rather silly. You don't see any foxes named Fox, now do you?")

sm.setPlayerAsSpeaker()
sm.sendSay("No, my name is... (What was it that Freud used to call me?)")

sm.setSpeakerID(SILVER)
sm.sendSay("Now, now, don't strain yourself. I doubt you can give yourself a very good name. Here, leave it to me. Hm... You're a stranger we found in the woods... Mystery? Oddity? Ooh, how about Muddle? I always thought Muddle would be a great...")

sm.setPlayerAsSpeaker()
sm.sendSay("No, look, I have my own-")

sm.setSpeakerID(MOONBEAM)
sm.sendSay("Geez, Grandpa! Those are terrible names! We have to name 'em something cool. Besides, I found 'em, so I get to name 'em. Now... You're all dark and mysterious, so I'm gonna call you Shade. How about that? You're the perfect compliment to a Moonbeam!")

sm.setSpeakerID(SILVER)
sm.sendSay("That seems a little...dark. You sure you don't like Muddle?")

sm.setPlayerAsSpeaker()
sm.sendSay("I'll go with Shade.")

sm.setSpeakerID(SILVER)
sm.sendSay("To each his own, I guess... So be it. I recognize you as Shade from this moment forward. That's going to take a little getting used to.")

if sm.sendAskAccept("So Shade, why don't you take a look around the house you'll be staying at? Might need some cleaning, seeing how it's been vacant for years."):
    sm.sendNext("Follow the path to the left and you'll see a house with a large pot. That's the one. Make yourself at home, my tasty friend.")
else:
    sm.sendNext("No, no, it's no problem at all. The owner of the house has been gone for years. Just as well that someone get some use out of it.")
    sm.dispose()

sm.jobAdvance(2500)
sm.setSTR(35)
sm.setDEX(4)
sm.setAP(23)
sm.addMaxHP(150)
sm.addMaxMP(50)
sm.giveItem(3010766)
sm.createQuestWithQRValue(parentID, "", True)
sm.startQuest(parentID)
sm.giveSkill(SWIFT_STRIKE, 0)

sm.setPlayerAsSpeaker()
sm.flipDialogue()
sm.sendSay("(Whew...)")
sm.sendSay("(I'm not nearly as strong as I was before. But it looks like I've still got it, so I should be okay for now.)")
sm.sendSay("(Now, let's head to that 'house'.)")
