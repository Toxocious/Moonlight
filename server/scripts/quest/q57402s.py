# Quest: Fresh Start (57402)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57401) and chr.getLevel() >= 11: # Quest: Zipangu's World
    sm.removeEscapeButton()
    
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("An extraordinary event... talk of another world... Nothing makes sense.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("The crazy guy had a point. This certainly isn't Japan. There isn't a blade of grass I don't recognize in Japan.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Could interrupting the ritual have caused this? Maybe we were too late after all. What do you think happened to Haruaki and Princess Sakuno?")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("No need to blame yourself. You did what you could. Wake me up next time you need to stop a ritual in time. You know that's my thing. For now, we should check out that new base Kanetsugu mentioned. They might have food. Or comfy beds.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I hope Haruaki and the Princess are safe.")

    sm.startQuest(57402)  #  Fresh Start
    # TODO: Start Navigation to -> Momijigaoka : Momijigaoka Entrance (807040100)
