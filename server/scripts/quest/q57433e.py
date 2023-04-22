
# Quest: Fact or Fiction (57433)
# Author: Tiger

ALCASTER = 2020005

if sm.hasQuestCompleted(57429) and chr.getLevel() >= 26: # To Each His Own
    sm.removeEscapeButton()

    sm.setSpeakerID(ALCASTER)
    sm.setBoxChat()
    if sm.sendAskYesNo("Did you finish your investigation?"):
        sm.completeQuest(57433)  #  Fact or Fiction

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendNext("Yes. Nobunaga was indeed involved. He used spells to provoke the monsters from afar. He must know I'm tracking him.")
        sm.sendSay("I also found rabbit-like people in black garb gathered around Nobunaga's men. Who could they be?")

        sm.setSpeakerID(ALCASTER)
        sm.setBoxChat()
        sm.sendSay("That's the Black Wings. Your friends attract a rough crowd. Nothing good can come of this alliance.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("I must bring word to Momijigaoka. Thank you for all your help.")

        sm.setSpeakerID(ALCASTER)
        sm.setBoxChat()
        sm.sendSay("I should be thanking you! Farewell.")
