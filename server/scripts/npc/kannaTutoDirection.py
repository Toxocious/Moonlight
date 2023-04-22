# Created by MechAviv
# Map ID :: 807100111
# NPC ID :: 9131011
# Altar
if True:#sm.getFieldID() == 807100111:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.showFieldEffect("JPKanna/magicCircle1", 0)
    sm.sendDelay(7000)


    sm.setSpeakerID(9131011)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendNext("The barrier is weakening. It must have worked.")


    sm.setSpeakerID(9131011)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendSay("Now I just have to destroy the altar in the basement.")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(807100112, 0)
