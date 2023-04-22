
# Quest: First Contact (57420)
# Author: Tiger

KINO_KONOKO = 9110002

if sm.hasQuestCompleted(57419) and chr.getLevel() >= 23: # Native Help
    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("Could you point me to the nearest shogun? I'm a skilled Spirit Walker and I would like to offer my services.")

    sm.setSpeakerID(KINO_KONOKO)
    sm.setBoxChat()
    sm.sendSay("Shogun? I think you mean...Empress Cygnus? She's in Ereve. I don't know that she'll appreciate you spirit walking near Shinsoo though.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Excuse my ignorance, but what is Ereve?")

    sm.setSpeakerID(KINO_KONOKO)
    sm.setBoxChat()
    sm.sendSay("Ereve is an island, silly.")
    sm.sendSay("Are you sure you're from Maple World?")
    sm.sendSay("I'm just kidding. Besides that, I've heard Edelstein is nice this time of year. Oh, wait. You weren't asking about vacation areas were you? You need to sharpen your sword or something?")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Maple World. So that's what they call it.")
    sm.sendSay("Thank you for your help. I will go to your Empress.")

    sm.completeQuest(57420)  #  First Contact
