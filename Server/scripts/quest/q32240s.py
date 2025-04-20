# Created by MechAviv
# Quest ID :: 32240
# The Explorer Book and A Maple Leaf

sm.setSpeakerID(9010010)
sm.flipDialogue()
sm.setSpeakerType(3)
sm.sendNext("I came to give you a gift. It's an #bExplorer Book#k, kinda like a diary. In this, you can record every exciting adventure you'll ever have! And then I can read about it later!")


sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
if sm.sendAskAccept("Do you want the #bExplorer Book#k? You do, right?"):
    sm.createQuestWithQRValue(32360, "1")
    sm.setSpeakerID(9010010)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendNext("Let me see... There's gotta be a book that's perfect for a Warrior like you...")


    sm.startQuest(32240)
    sm.setSpeakerID(9010010)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Found it!  Here. Take a good look at it after I take off.")


    sm.setSpeakerID(9010010)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendPrev("Well, have a blast in your adventures!")
else:
    sm.setSpeakerID(9010010)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendNext("Eh? No? Why? What about your adventures? Your memories? My entertainment?")
