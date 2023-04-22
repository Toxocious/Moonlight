# Created by MechAviv
# Quest ID :: 25530
# Sojourn's End

sm.setSpeakerID(1032205)
sm.sendNext("Don't be so hard on yourself, okay? It wasn't your fault...")


sm.setSpeakerID(1032206)
sm.flipDialogue()
sm.sendSay("Really? Then who was it that blew up our house?!!")


sm.setSpeakerID(1032205)
sm.setPlayerAsSpeaker()
sm.sendSay("I am afraid it is my responsibility, #p1032206#. I have hidden myself these past years in a life I had only dreamt of, but if my power were to harm you again...")


sm.setSpeakerID(1032205)
sm.sendSay("Don't try to make this about me! We can find a way around this. Don't leave...")


sm.setSpeakerID(1032205)
sm.setPlayerAsSpeaker()
sm.sendSay("I must. If I do not drive the darkness from my heart, there will be no future for us. I will return when my quest is complete.")


sm.setSpeakerID(1032205)
sm.sendSay("You don't have to do this...")


sm.setSpeakerID(1032205)
sm.setPlayerAsSpeaker()
sm.sendSay("I swear, it is the only course of action that will keep us safe. Penny will protect you while I am away.")


sm.setSpeakerID(1032206)
sm.flipDialogue()
sm.sendSay("That's what I do! I'll scare everybody off, meow!")


sm.setSpeakerID(1032205)
sm.setPlayerAsSpeaker()
sm.sendSay("(Why do I keep thinking about the time I left Harmony? This will be different!)\r\nYou had better. I... will see you both soon.")

sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(480)