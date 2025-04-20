
# Quest: Honnou-ji Infiltration 1 (57436)
# Author: Tiger

TSUCHIMIKADO_HARUAKI = 9130010
TAKEDA = 9130000
MOURI = 9130006
UESUGI_KENSHIN = 9130009
NAOE_KANESTSUGU = 9130022

if sm.hasQuestCompleted(57435) and chr.getLevel() >= 28: # An Eye on Honnou-ji
    sm.removeEscapeButton()

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendNext("I was just going to call it Honnou-ji Infiltration... What does everyone think about entering through the western Honnou-ji walls?")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("Why not attack from the east? Our foes won't expect us to strike at the same place we did during the original Honnou-ji Raid.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("Oh. Hold on a second. Ngh. Nnngaaah. The hair agrees. East wall it is. A tactical approach.")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("I say we blast through the wall and take the place by storm instead of slinking around like rats.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("That sounds like a perfect plan if we all want to die a miserable death at the hands of Oda's Army.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("The valor is appreciated, Kenshin, but Shingen's plan is more practical. We enter through the east wall.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("Only one question remains: who infiltrates?")


    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I'll go.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("No. We couldn't afford to lose you. Let Kenshin go.")


    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("This mission is all about infiltration. Do you really think Kenshin can keep quiet long enough to find Princess Sakuno? I'm nimble, I'm quiet, and I don't wear armor.")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("You're right. If I see the enemy, I'm afraid my sword might move of its own accord. You are a brave warrior, Kanna.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("Well, no need to split hairs if we have a volunteer!")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("Take Kanetsugu with you. He can watch your back. Kanetsugu! See to it that Kanna returns unscathed.")

    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    sm.sendSay("Yes, Kenshin! While I draw breath, Kanna will be safe.")
    sm.sendSay("Kanna, I'll go first. I will await your arrival at the eastern Honnou-ji walls. Come when you're ready.")
    sm.sendSay("Kenshin, the thought of your beauty will propel my blade.")

    sm.startQuest(57436)  #  Honnou-ji Infiltration 1
