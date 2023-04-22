# [Riena Strait] The Heart of a Witch

frostWitchBarbara = 1510008
noraTheExplorer = 1510007

sm.setSpeakerID(frostWitchBarbara)
sm.sendNext("What you still have something to say to me?")

sm.setSpeakerID(noraTheExplorer)
sm.sendNext("Granny, I have a question.")

sm.setSpeakerID(frostWitchBarbara)
sm.sendNext("What do you want to know? Are you curious when this old lady will die? "
            "If you mention about defeating and sort one more time in front of me, boy, "
            "I'll destroy that mouth of yours first.")

sm.setSpeakerID(noraTheExplorer)
sm.sendNext("No.. What I am saying is that the penguins, the malamutes, and the seals here are doubting you. "
            "Is it perhaps due to your magic that the glaciers are melting and the sea level is rising?")

sm.setSpeakerID(frostWitchBarbara)
sm.sendNext("Magic? What is that? Do you eat that?")

sm.setPlayerAsSpeaker()
sm.sendNext("There also is a rumor that she is conducting some experiments with the children.")

sm.setSpeakerID(frostWitchBarbara)
sm.sendNext("WHAT?! Shut your mouth! What makes you think that I would do such a thing to these cute little things?!")

sm.sendNext("These poor kids lost their home and parents as the glacier is melting"
            "If no one takes care of them, its unimaginable how they will turn out!"
            "They were floating on the sea pitifully, and I picked them up and am taking care of them.")

sm.setSpeakerID(noraTheExplorer)
sm.sendNext("You have been raising the children?")

sm.setSpeakerID(frostWitchBarbara)
sm.sendNext("I was just taking care of them temporarily..")

sm.sendNext("It is said, the penguins should grow with the penguins, "
            "and the seals need to grow with the seals. "
            "After curing a baby, and if it looks like it has recovered enough, "
            "I would bring him to his people secretly at night.")

sm.setPlayerAsSpeaker()
sm.sendNext("But why would you do such a thing despite all the misunderstandings?...")

sm.setSpeakerID(frostWitchBarbara)
sm.sendNext("Whew.. That mouth of yours.. How does it help you to talk so much? "
            "If you have time for talking, shut up and do me a favor.")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.dispose()