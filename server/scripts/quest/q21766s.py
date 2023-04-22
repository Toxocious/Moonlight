# Created by MechAviv
# Quest ID :: 21766
# Suspicious John

sm.setSpeakerID(1002001)
sm.flipSpeaker()
sm.sendNext("Hey! Can you do me a favor? #p20000# seems a bit strange these days...")


sm.setSpeakerID(1002001)
sm.setPlayerAsSpeaker()
sm.sendSay("What's wrong with #b#p20000#?#k")


sm.setSpeakerID(1002001)
sm.flipSpeaker()
sm.sendSay("He used to scowl and whine about his arthritis until just recently, but he's suddenly become all happy and smiley!!")


sm.setSpeakerID(1002001)
sm.setPlayerAsSpeaker()
sm.sendSay("#bWhat's wrong with that?? Maybe his arthritis has gotten better...#k")


sm.setSpeakerID(1002001)
sm.flipSpeaker()
sm.sendSay("That's exactly what I'm talking about. How is it possible that he's gotten better all of a sudden? On top of that, he carries around a wooden box and chuckles to himself nonstop!")


sm.setSpeakerID(1002001)
if sm.sendAskAccept("I have a feeling there is a secret behind that wooden box. Could you stealthily look into the wooden box next to #p20000#? "):
    sm.startQuest(21766)
    sm.setSpeakerID(1002001)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("#bFine, I'll look into the wooden box and see what I can find.#k")

    
    sm.setSpeakerID(1002001)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.sendSay("Okay. This should answer the mystery of why #p20000# has been acting strange. Inspect the wooden box located next to #p20000# and let me know what you find.")


    sm.setSpeakerID(1002001)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.sendPrev("You know where #p20000# is, right? It's to the right of here. The box will be somewhere near John.")
else:
    sm.setSpeakerID(1002001)
    sm.sendNext("#p20000# is acting really strange lately!")

