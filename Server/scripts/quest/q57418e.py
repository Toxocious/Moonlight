
# Quest: Picking up the Pieces (57418)
# Author: Tiger

TSUCHIMIKADO_HARUAKI = 9130010

if sm.hasQuestCompleted(57417) and chr.getLevel() >= 22: # Foxtail Mystery
    sm.removeEscapeButton()

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendNext("Princess Sakuno is not among us.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Yes. Unfortunately, we don't know where she is. Nobunaga's forces may have found her first.")

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendSay("Worry not. They need her alive just as much as we do. The ritual requires the blood of her lineage. After the ritual...")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("We won't let them get that far.")

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendSay("I trust in your abilities, Kanna, but do not stretch yourself too thin. I fear the fates of Princess Sakuno and Nobunaga will rest on your shoulders before this is over, and if that time should come, you must be ready.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Princess Sakuno won't have to endure Nobunaga's wretched grasp much longer.")

    sm.completeQuest(57418)  #  Picking up the Pieces
