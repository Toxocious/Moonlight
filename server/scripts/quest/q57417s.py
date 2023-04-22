
# Quest: Foxtail Mystery (57417)
# Author: Tiger

MOURI = 9130006
TAKEDA = 9130000
UESUGI_KENSHIN = 9130009

if sm.hasQuestCompleted(57415) and chr.getLevel() >= 21: # Aura's Origin 2
        sm.removeEscapeButton()

        sm.setSpeakerID(MOURI)
        sm.setBoxChat()
        sm.sendNext("Good work, Kanna.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("I felt a mysterious energy coming from Momijigahara and couldn't resist seeking it out. I apologize.")

        sm.setSpeakerID(MOURI)
        sm.setBoxChat()
        sm.sendSay("No need to apologize. You begin to show promise, Kanna. I would not have expected such success from the bumbling dog that came to me to train. Keep it up and you may yet prove useful.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("Thank you. Motonari. I found this on the battlefield. It emits an unusual energy. I was hoping you might recognize it.")

        sm.setSpeakerID(MOURI)
        sm.setBoxChat()
        sm.sendSay("Ah, a white foxtail. This is strange. I've seen this before. Warriors from the raid returned with similar tails.")
        sm.sendSay("Kenshin, Shingen. Do either of you have any idea what power this object holds?")

        sm.setSpeakerID(UESUGI_KENSHIN)
        sm.setBoxChat()
        sm.sendSay("Unless that thing's a lot sharper than it looks, I don't see the danger.")

        sm.setSpeakerID(TAKEDA)
        sm.setBoxChat()
        sm.sendSay("It's so silky and voluminous. It must be incredibly powerful.")

        sm.setSpeakerID(MOURI)
        sm.setBoxChat()
        if sm.sendAskAccept("So you know nothing. Then we'll have to gather up all the foxtails we can find and experiment."):
            sm.startQuest(57417)  #  Foxtail Mystery
