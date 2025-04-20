# Created by MechAviv
# Map ID :: 807100102
# Honnou-ji : Honnou-ji Alleyway

if sm.hasMobsInField():
    sm.chatScript("Defeat all the enemy Spirit Walkers!")
else:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.showFieldEffect("JPKanna/magicCircle2", 0)
    sm.sendDelay(7000)


    sm.setSpeakerID(9131010)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendNext("It seems like the others have succeeded as well. The barrier is collapsing.")


    sm.setSpeakerID(9131010)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendSay("I need to hurry to the basement and take care of the altar.")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.removeSkill(40021183)
    sm.removeSkill(40021184)
    sm.removeSkill(40021185)
    sm.removeSkill(40021186)
    # Unhandled Message [47] Packet: 2F 02 00 00 00 A0 5C 08 00 00 00 00 00 24 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0F 00 6D 6F 62 5F 6B 69 6C 6C 3D 31 32 37 30 35 31 58 68 08 00 00 00 00 00 27 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0F 00 6D 6F 62 5F 6B 69 6C 6C 3D 31 32 37 30 35 31
    sm.warp(807100103, 1)