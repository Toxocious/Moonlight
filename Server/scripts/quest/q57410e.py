
# Quest: Blind and Battered 3 (57410)
# Author: Tiger

UESUGI_KENSHIN = 9130009
HAKU = 9130081

if sm.hasQuestCompleted(57409) and chr.getLevel() >= 18: # Blind and Battered 2
    sm.removeEscapeButton()

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendNext("Who's that?")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("!!!!!!!!!!!!!!!!!!!!!!!!!")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("I see why Kanetsugu is so enamored. A true goddess of the battlefield.")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    if sm.sendAskAccept("You're not an enemy soldier..."):
        sm.completeQuest(57410)  #  Blind and Battered 3
