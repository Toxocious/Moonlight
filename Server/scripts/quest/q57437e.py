
# Quest: Honnou-ji Infiltration 2 (57437)
# Author: Tiger

NAOE_KANESTSUGU = 9130022
PRINCESS_SAKUNO = 9130021

if sm.hasQuestCompleted(57436) and chr.getLevel() >= 29: # Honnou-ji Infiltration 1
    sm.removeEscapeButton()

    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    sm.sendNext("The princess is safe!")

    sm.setSpeakerID(PRINCESS_SAKUNO)
    sm.setBoxChat()
    sm.sendSay("Thank you for your valiant efforts. I was growing tired of this prison of boredom. Nobunaga doesn't know how to treat a guest.")

    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    if sm.sendAskAccept("Please save your thanks for when we are safe, your highness. My spirit would burn in eternal shame should any harm befall you before then."):
        sm.completeQuest(57437)  #  Honnou-ji Infiltration 2
        # Start Navigation to -> Momijigaoka : Momijigaoka (807000000)
