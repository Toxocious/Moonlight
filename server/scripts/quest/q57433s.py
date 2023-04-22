
# Quest: Fact or Fiction (57433)
# Author: Tiger

ALCASTER = 2020005

if sm.hasQuestCompleted(57429) and chr.getLevel() >= 26: # To Each His Own
    sm.removeEscapeButton()

    sm.setSpeakerID(ALCASTER)
    sm.setBoxChat()
    sm.sendNext("I'm sorry I waited so long to tell you, but the monsters had to be taken care of. Your friends are preparing for some sort of ritual. One of them has an outfit just like yours, only black.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Could it be? Mori Ranmaru?")

    sm.setSpeakerID(ALCASTER)
    sm.setBoxChat()
    if sm.sendAskAccept("There's no telling how long they'll be there. I suggest you hurry."):
        sm.startQuest(57433)  #  Fact or Fiction
