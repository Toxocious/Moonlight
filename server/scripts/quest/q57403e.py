
# Quest: Powerless (57403)
# Author: Tiger

MOURI = 9130006

if sm.hasQuestCompleted(57402) and chr.getLevel() >= 12: # Fresh Start
    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("What's this? My legs feel like jelly. And I can't feel an ounce of energy flowing through me.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("You only noticed that now? Perhaps you're too slow to be of help after all.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("You don't feel the energy because it's not here. This place is completely different than Japan.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("How am I supposed to regain my strength?")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("Ah, the first sensible sentence out of your mouth! You train, my dear.")

    sm.completeQuest(57403)  #  Powerless
