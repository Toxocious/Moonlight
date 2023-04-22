
# Quest: Aura's Origin 1 (57414)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57413) and chr.getLevel() >= 20: # Commanding Aura
    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("I'm exhausted. How does Kenshin take out hundreds of soldiers at a time? I didn't find the energy source either. Maybe it's not here.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("No. Trust me, it's here.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("How can you be so sure?")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Kanna, I'm a fox.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("...Regardless, the energy was much stronger here than at Momijigaoka. ")

    sm.completeQuest(57414)  #  Aura's Origin 1
