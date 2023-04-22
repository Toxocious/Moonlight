# id 34109 ([Vanishing Journey] Crossing the Extinction Zone), field 450001100
sm.setSpeakerID(3003125) # Rino
sm.sendNext("The Extinction Zone is much more dangerous than any place you've ever encountered.")
sm.sendSay("Never let your guard down, no matter what. If you touch the flames here, your body will vanish forever. There is a safe path through...")
if sm.sendAskAccept("But first promise me that you won't do anything this reckless again, and that you will follow my instructions. Can you do that?"):
    sm.completeQuestNoCheck(parentID)
    sm.sendNext("You can trust in me. I'll guide you and Kao to safety. Let me know when you're ready to go.")
