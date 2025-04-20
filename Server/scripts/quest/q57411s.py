
# Quest: Blind and Battered 4 (57411)
# Author: Tiger

UESUGI_KENSHIN = 9130009
HAKU = 9130081

if sm.hasQuestCompleted(57410) and chr.getLevel() >= 19: # Blind and Battered 3
    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("I'm so glad you're all right, Kenshin. Kanetsugu would have died of grief if something had happened to you. Listen, we're in a strange world, so your skills aren't...")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("You listen to me. There are enemies about and I have a sword in my hand. This is the battlefield, not a tea house. Do you know what happens on a battlefield? Battle. ")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("She's fast.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("She isn't even fazed by this place. How does she do it? I'd better go after the Advanced Scouts here. How many will it take to impress her? Fifty?")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Don't tell me you're thinking about competing against that goddess of war. She's ten times better than Kanetsugu!")

    sm.startQuest(57411)  #  Blind and Battered 4
