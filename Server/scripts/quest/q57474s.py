# Quest: Family Seal (57474)
# Author: Tiger

TSUCHIMIKADO_HARUAKI = 9130010

if sm.hasQuestCompleted(57434) and chr.getLevel() >= 27: # Deepening Suspicion
    sm.removeEscapeButton()

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendNext("Kanna, return to Momijigaoka the moment you receive this letter. We have discovered Princess Sakuno's whereabouts.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    if sm.sendAskAccept("The princess! I must return to Momijigaoka immediately."):
        sm.startQuest(57474)  #  Family Seal
        sm.giveSkill(40021227) # Return to the Five Planets

        sm.sendNext("#e#bUse #s40021227# #q40021227##k#n to move to #m807000000#.")
        # nav -> Momijigaoka : Momijigaoka (807000000)
