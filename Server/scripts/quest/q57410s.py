
# Quest: Blind and Battered 3 (57410)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57409) and chr.getLevel() >= 18: # Blind and Battered 2
    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("This isn't enough. I have to be more thorough. Every scout left standing is a liability.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("You're doing fine, Kanna. Kanetsugu's been at this longer than you.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I didn't ask about Kanetsugu! He's not even worth my time. He's obviously lying about his numbers anyway. Do you really think he's faster than a Spirit Walker?")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("I'll exercise my right to remain silent.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("That's a first... How about you continue doing that while I search deeper in the battlefield for the higher-ranked soldiers. If I defeat them, the other scouts might turn back.")
    sm.startQuest(57410)  #  Blind and Battered 3
    # Need to Start Navigation to -> Momijigaoka : Maple Hill Road 2 (807010100)
