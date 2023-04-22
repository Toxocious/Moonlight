# Created by MechAviv
# Quest ID :: 25578
# The Sanctum of Harmony

sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("For your sake, you'd better deliver.")


sm.setSpeakerID(1032204)
sm.flipDialogue()
sm.setSpeakerType(3)
sm.sendSay("The path to Aurora is clear. All one must do is open his mind...")


sm.setSpeakerID(1032204)
sm.flipDialogue()
sm.setSpeakerType(3)
sm.sendSay("...The path to Aurora is guarded by four paradoxes: <Cold Flame, Hot Ice, Destructive Creation, and eyes that see darkness>#k.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("So, I must solve these paradoxes to reach Aurora. If I still had Light magic, I could get there easily. But the power of Dark is a worthy trade...")

sm.startQuest(25578)
sm.completeQuest(25578)
sm.giveExp(2100)
