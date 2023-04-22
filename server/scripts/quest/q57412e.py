
# Quest: Echigo's Dragon (57412)
# Author: Tiger

TAKEDA = 9130000
UESUGI_KENSHIN = 9130009
HAKU = 9130081
KANETSUGU = 9130022
MOURI = 9130006

if sm.hasQuestCompleted(57411) and chr.getLevel() >= 19: # Blind and Battered 4
    sm.removeEscapeButton()

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendNext("I shall explain the extraordinary event that brought us here.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("(Motonari explained what happened to Takeda and the four people of Uesugi. He also revealed details about this strange world.)")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("So Nobunaga is here. I guess even Shingen's unflattering outburst held a tiny shred of truth.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("I've got more truth in my...")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("Silence! Put your childish differences aside and work together. Your arrival here is fortiutous, but Nobunaga will have nothing left to attack if you keep up this squabbling. We need to build our alliance until it is truly unstoppable!")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("Sounds like a lot of running around. The youngsters will have to pull their weight on this one. Yukimura, good luck!")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("Kanetsugu, make sure you don't lag behind Shingen's rookie!")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("With a team like this, we'll find the master and the princess in no time.")

    sm.completeQuest(57412)  #  Echigo's Dragon
