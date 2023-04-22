# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001120
# Hideout  :: Training Room 2
JAY = 1531001

sm.setSpeakerID(JAY)
sm.sendNext("Kinesis, I've got a present for you. Thought it would be nice to celebrate your first real upgrade.")

if sm.sendAskYesNo("Check it out, it's a bag! This will help you carry more stuff without having to resort to a fanny-pack or something."):
    # INV grow 1-4
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.sendSayOkay("Keep it up, K. Not that you need me to tell you that.")
else:
    sm.sendSayOkay("I know you'll change your mind. Let me know when you do.")