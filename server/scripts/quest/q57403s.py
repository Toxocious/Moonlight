# Quest: Powerless (57403)
# Author: Tiger

MOURI = 9130006

if sm.hasQuestCompleted(57402) and chr.getLevel() >= 12: # Fresh Start
    sm.removeEscapeButton()

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendNext("Ah, you will...")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I'm Kanna, Tsuchimikado Haruaki's star pupil. I believe we met briefly during the Honnou-ji raid, but I'd like to formally introduce myself.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("You dare invite yourself into Momijigaoka? This is a sacred place, a slice of Japan. My sons and I didn't toil for days to build fortifications and clear this place of enemies just to have a fool disrespectfully blunder their way in.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Excuse me? Kanetsugu sent me here, but obviously I have the wrong person. ")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    if sm.sendAskAccept("You are the one Kanetsugu spoke of, then? No, no. Don't leave. I may have need of your help. But first, show me you can still move normally. The workings of this world are different from that with which we are familiar."):
        sm.startQuest(57403)  #  Powerless
    else:
        sm.setSpeakerID(MOURI)
        sm.setBoxChat()
        sm.sendNext("Try to move around.")
