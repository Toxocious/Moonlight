# Quest: Dark Times (57407)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57406) and chr.getLevel() >= 16: # Adaptation 3
    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("I endured Motonari's training, and his attitude, but my strength is still at an all-time low.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Well, that's the best you can do for now. I must say, I don't feel the whole 'different rules' thing everyone's been talking about. I feel fine.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Is that so? Let me see you walk, then.")

    sm.startQuest(57407)  #  Dark Times
