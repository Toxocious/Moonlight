
# Quest: Picking up the Pieces (57418)
# Author: Tiger

MOURI = 9130006
TAKEDA = 9130000
UESUGI_KENSHIN = 9130009
TSUCHIMIKADO_HARUAKI = 9130010

if sm.hasQuestCompleted(57417) and chr.getLevel() >= 22: # Foxtail Mystery
        sm.removeEscapeButton()

        sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
        sm.setBoxChat()
        sm.sendNext("I'm sure you all have questions about what brought us here.")

        sm.setSpeakerID(UESUGI_KENSHIN)
        sm.setBoxChat()
        sm.sendSay("Please, Haruaki, enlighten us. Don't spare the details.")

        sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
        sm.setBoxChat()
        sm.sendSay("I'll start with the true purpose of Oda Nobunaga's ritual.")
        sm.sendSay("Nobunaga sought to conjure the Sixth Sky Demon Lord from the Demon Skies. Just as this world is different from Japan, so are the Demon Skies. And just as we are now in contact with this world, it is possible to make contact with the Demon Skies through an ancient ritual.")
        sm.sendSay("When Nobunaga discovered the Demon Skies, he devised a plan to create a portal from Japan and bring the Sixth Sky Demon Lord into our world.")
        sm.sendSay("This is why I launched the mission at Honnou-ji. Thanks to your efforts, Nobunaga's ritual was interrupted.")
        sm.sendSay("However, what happened next, none could have foreseen. The gate to the Demon Skies warped when we cut short the ritual, and we were transported here instead.")

        sm.setSpeakerID(TAKEDA)
        sm.setBoxChat()
        sm.sendSay("This place is bad, but something tells me the Demon Skies would have been worse.")

        sm.setSpeakerID(UESUGI_KENSHIN)
        sm.setBoxChat()
        sm.sendSay("Whatever world Nobunaga chooses to inhabit, we'll be there to meet him with swords drawn.")

        sm.setSpeakerID(MOURI)
        sm.setBoxChat()
        sm.sendSay("But before we draw swords, we must recover our skills. We're all weak. The alliance must shine with the light of a thousand suns before we face Nobunaga.")

        sm.setSpeakerID(TAKEDA)
        sm.setBoxChat()
        sm.sendSay("I don't know about everyone else, but my hair's at full power.")

        sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
        sm.setBoxChat()
        sm.sendSay("We'll create the strongest alliance this world has ever seen.")
        sm.sendSay("Kanna, I'd like to talk to you privately.")

        sm.startQuest(57418)  #  Picking up the Pieces
