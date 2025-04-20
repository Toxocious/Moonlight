
# Quest: Foxtail Mystery (57417)
# Author: Tiger

MOURI = 9130006
TSUCHIMIKADO_HARUAKI = 9130010

if sm.hasQuestCompleted(57415) and chr.getLevel() >= 21: # Aura's Origin 2
        sm.removeEscapeButton()

        sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
        sm.setBoxChat()
        sm.sendNext("Hmm? Why the surprised looks? Kanna! I've been meaning to speak with you. Good job at Honnou-ji. You must have completed your mission with flying colors. That's my Spirit Walker.")

        sm.setSpeakerID(MOURI)
        sm.setBoxChat()
        sm.sendSay("Haruaki... How did you inhabit the foxtail?")

        sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
        sm.setBoxChat()
        sm.sendSay("It's quite a long story. I arrived here before any of you. During the raid on Honnou-ji, I was somehow transported here at the same time as Nobunaga and his men.")
        sm.sendSay("I disguised myself as a number of foxtails to avoid being discovered by Oda's Army, but his soldiers took a liking to me and brought me straight into their camp.")
        sm.sendSay("If it wasn't for Kanna, I would be probably be part of a lovely coat by now.")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("You know what they say: when in doubt, act as a fox would.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("That's not a saying, Haku.")

        sm.completeQuest(57417)  #  Foxtail Mystery
