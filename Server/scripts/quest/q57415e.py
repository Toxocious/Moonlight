
# Quest: Aura's Origin 2 (57415)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57414) and chr.getLevel() >= 21: # Aura's Origin 1
    if sm.hasItem(4033274): # Mysterious Foxtail
        sm.removeEscapeButton()

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendNext("Yes! I was right. Do you feel it?")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("Look at that. Energy coming from a foxtail. Now do you believe in my mystical powers?")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("This isn't your tail is it?")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("Kanna, look at my tail. Bushy, magnificent, fully intact... Does it really look like that thing came from me? Besides, my tail is green... isn't it? I can't tell, I'm colorblind.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("You... seriously? Let's just get this back to the base to study it.")

        sm.consumeItem(4033274) #  Mysterious Foxtail
        sm.completeQuest(57415)  #  Aura's Origin 2

        # Start Navigation to -> Momijigaoka : Momijigaoka (807000000)
