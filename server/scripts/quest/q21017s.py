# 140000000
LILIN = 1201000

sm.setSpeakerID(LILIN)
sm.flipSpeaker()
sm.sendNext("It seems like you're warmed up now. This is when rigorous training can really help you build a strong foundation. Let's proceed with the Basic Training, shall we?")
sm.sendSay("Go defeat some #r#o0100133#s#k in #b#m140020200##k this time. I think about  #r20#k should do it. Go on ahead and... Hm? Do you have something you'd like to say?")

sm.setPlayerAsSpeaker()
sm.sendSay("Isn't the number getting bigger and bigger?")

sm.setSpeakerID(LILIN)
sm.flipSpeaker()
sm.sendSay("Of course it is. What, are you not happy with 20? Would you like to defeat 100 of them instead? Oh, how about 999 of them? Someone in Sleepywood would be able to do it easily. After all, we are training...")

sm.setPlayerAsSpeaker()
sm.sendSay("Oh no, no, no. Twenty is plenty.")

sm.setSpeakerID(LILIN)
if sm.sendAskAccept("You don't have to be so modest. I understand your desire to quickly become the hero you once were. This sort of attitude is what makes you a hero."):
    sm.startQuest(parentID)

    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(If you keep listening, you might end up having to take down 999 of them.)#k")

    sm.setSpeakerID(LILIN)
    sm.flipSpeaker()
    sm.sendSay("Please go ahead and slay 20 #o0100133#s.")
else:
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(You declined out of fear, but it's not like you can run away like this. Take a big breath, calm down, and try again.)#k")
    sm.dispose()