

# Quest: Blind and Battered 4 (57411)
# Author: Tiger

UESUGI_KENSHIN = 9130009
HAKU = 9130081

if sm.hasQuestCompleted(57410) and chr.getLevel() >= 19: # Blind and Battered 3
    sm.removeEscapeButton()

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    if sm.sendAskYesNo("Kanna! I felt your energy on the battlefield."):
        sm.sendNext("Did you satisfy your lust for blood?")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("Um... My lust for blood can never be satisfied!")

        sm.setSpeakerID(UESUGI_KENSHIN)
        sm.setBoxChat()
        sm.sendSay("Excellent. You'd make the perfect pupil! Much better than that wretched Kanetsugu... I kept track until the 200th enemy, but they fell faster than I could count toward the end.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("Kenshin. You're amazing.")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("You'd like anyone who favored you over Kanetsugu.")

        sm.setSpeakerID(UESUGI_KENSHIN)
        sm.setBoxChat()
        sm.sendSay("You seem to know what's going on here. All I remember is a flash of light and a long fall that ended with me stuck in the trees again.")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("(Did she just say 'again'?)")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("We've set up a base nearby called Momijigaoka. I'll explain everything I know when we get there.")

        sm.completeQuest(57411)  #  Blind and Battered 4
        sm.warp(807000000, 2) # Momijigaoka : Momijigaoka
