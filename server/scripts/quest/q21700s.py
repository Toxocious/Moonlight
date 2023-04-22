# 140000000
LILIN = 1201000

sm.setSpeakerID(LILIN)
sm.flipSpeaker()
sm.sendNext("It seems like you've started to remember things. Your Polearm must have recognized you. This means you are surely #bAran, the wielder of Polearms#k. Is there anything else you remember? Skills you used with the Polearm perhaps? Anything?")

sm.setPlayerAsSpeaker()
sm.sendSay("#b(You tell her that you remember a few skills.)#k")

sm.setSpeakerID(LILIN)
sm.flipSpeaker()
sm.sendSay("That's not a lot, but it's progress. Our focus, then, should be to get you back to the state before you were frozen. You may have lost your memory, but I'm sure it won't take long for you to recover the abilities that your body remembers.")

sm.setPlayerAsSpeaker()
sm.sendSay("How do I recover my abilities?")

sm.setSpeakerID(LILIN)
if sm.sendAskAccept("There is only one way to do that. Train! Train! Train! Train! If you continue to train, your body will instinctively remember its abilities. To help you through the process, I'll introduce you to an instructor."):
    # sm.giveItem(1442077)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)

    sm.sendNext("I thought it'd be better for you to use a familiar weapon for your training, so I gave you a #bPolearm#k. I hope you can handle it. Now, take that weapon and...")
    sm.sendPrev("Hmm. Give me a second. I need to think about what you should do now...")
else:
    sm.sendNext("No? Are you saying you can train on your own? I'm just letting you know that you'll get better results if you train with an instructor. You can't live in this world alone. You must learn to get along with other people.")