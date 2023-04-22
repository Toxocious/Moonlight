
# Quest: First Contact (57420)
# Author: Tiger

KINO_KONOKO = 9110002

if sm.hasQuestCompleted(57419) and chr.getLevel() >= 23: # Native Help
    sm.removeEscapeButton()

    sm.setSpeakerID(KINO_KONOKO)
    sm.setBoxChat()
    sm.sendNext("Where are you from? I like your clothes!")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Thank you. I like your... broom.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I come from a faraway, rural area by the coast... An island, actually. This might sound strange, but have you seen anything odd happening around here?")

    sm.setSpeakerID(KINO_KONOKO)
    sm.setBoxChat()
    sm.sendSay("Hmm... Come to think of it, there has been more dirt lately. So much I can't keep up! I'm stuck here sweeping this one spot and it never gets clean.")
    sm.sendSay("Beyond that, everything seems normal. There's a storm near the Mushroom Shrine. That doesn't happen very often.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Nobunaga must not have made contact yet. He doesn't know we're here.")

    sm.startQuest(57420)  #  First Contact
