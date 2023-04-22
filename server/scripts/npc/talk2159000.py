JUN = 2159000
VON = 2159002

sm.setSpeakerID(JUN)
sm.sendNext("I'm glad you made it. Safety in numbers, right? I feel like we're being watched... Shouldn't we think about heading back? The grown-ups in town say the mines aren't safe...")

sm.setSpeakerID(VON)
sm.flipDialogue()
sm.sendPrev("Sheesh, why are you such a scaredy cat? We've come all this way! We should at least do something before we go back.")