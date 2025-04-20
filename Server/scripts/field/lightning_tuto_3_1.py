ARAN = 2159356

sm.removeEscapeButton()
sm.setSpeakerID(ARAN)
sm.sendNext("I figured you'd have your hands full right now, but here you are goofing around!")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Aran! You're wounded! Where are Mercedes and Freud?")

sm.setSpeakerID(ARAN)
sm.sendSay("Ah, it's nothing. Those two got a head start on me. They might even be fighting the Black Mage right now...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Can you go on?")

sm.setSpeakerID(ARAN)
sm.sendSay("Aww, are you worried about me? I'm fine! Just... get in there, okay? You don't want to let Mercedes and Freud hog all the glory. I'll stay here and fend off anybody trying to get in...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("...Be careful.")

sm.forcedInput(2)
sm.curNodeEventEnd(True)