# Quest: Deepening Suspicion (57434)
# Author: Tiger

NAOE_KANESTSUGU = 9130022
HAKU = 9130081

if sm.hasQuestCompleted(57433) and chr.getLevel() >= 26: # Fact or Fiction
    sm.removeEscapeButton()

    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    sm.sendNext("Kanna! How did you fare in El Nath?")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("(You tell Kanetsugu what happened in El Nath.)")

    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    sm.sendSay("I found similar evidence at Aqua Road. Maple World is in trouble.")
    if sm.sendAskAccept("You're on a roll, Kanna. I'll bring word of our findings to Momijigaoka so you can continue to collect information on Nobunaga and the Black Wings. And so I can see Kenshin."):
        sm.completeQuest(57434)  #  Deepening Suspicion

        sm.sendNext("Hold on Kenshin! I'm on my way!")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("I would have taken Shingen over him any day if I were Kenshin.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("He just admires her, Haku.")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("If that's what you want to call it. ")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("Being Kenshin wouldn't be so bad...")
