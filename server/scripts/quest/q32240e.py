# Created by MechAviv
# Quest ID :: 32240
# The Explorer Book and A Maple Leaf

sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("An #bExplorer Book#k? So, I can record all my adventures here?")


sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Perfect! I just started my adventure, so I should get started! But, wait...")


sm.giveItem(4460000)
sm.giveExp(170)
sm.completeQuest(32240)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.showFieldEffect("adventureStory/mapleLeaf/0", 0)
sm.sendDelay(1800)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("A Maple Leaf? OH, I remember seeing a huge Maple Tree on Maple Island. How did it follow me here?")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I guess I can keep it in my #bExplorer Book#k to remind me of Maple Island.")
