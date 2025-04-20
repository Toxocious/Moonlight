
# Quest: Echigo's Dragon (57412)
# Author: Tiger

TAKEDA = 9130000
UESUGI_KENSHIN = 9130009
HAKU = 9130081
KANETSUGU = 9130022
MOURI = 9130006

if sm.hasQuestCompleted(57411) and chr.getLevel() >= 19: # Blind and Battered 4
    sm.removeEscapeButton()

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendNext("This is the new stronghold? Simple, practical, elegant... This is the work of Motonari.")

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendSay("Kanna, you won't believe my new record... K-Kenshin? Kenshin! You're safe! Oh my... thank... I can't... your beautiful face...")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Now that's love.")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("Pull yourself together, Kanetsugu. Of course I'm safe. Kanna and I made short work of the situation in the woods.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("Oh... Kenshin. Guess Nobunaga's not the only dirty swamp rat that made it through.")

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendSay("How dare you, Shingen!")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("This I like.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("I see the new lap dog is working out well. Barks on command too.")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("He knows honor and loyalty. More than I could ever say for you.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("Kenshin. Shingen. Your quarrel stops now.")

    sm.startQuest(57412)  #  Echigo's Dragon
