
# Quest: Blind and Battered 2 (57409)
# Author: Tiger

KANETSUGU = 9130022
HAKU = 9130081

if sm.hasQuestCompleted(57408) and chr.getLevel() >= 17: # Blind and Battered 1
    sm.hideNpcByTemplateId(KANETSUGU, False)
    sm.removeEscapeButton()

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendNext("Looks like we got the same mission. Motonari must have meant for us to compete!")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I don't think that's what he sa...")

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendSay("I got 40 already. Not bad for a new world with messed up physics.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("...")

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    if sm.sendAskAccept("Hey! There's another enemy. Excuse me, Kanna. I've got more work to do!"):
        sm.startQuest(57409)  #  Blind and Battered 2

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendNext("Don't let him get to you. Forty enemies is ridiculous. No one expects you to get that many.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("What's that supposed to mean?")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("Maybe you're just not cut out for this world.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("Whose side are you on, Haku?")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("Yours, of course. Do you think I'd ride around with you all day if I didn't want you to win?")
