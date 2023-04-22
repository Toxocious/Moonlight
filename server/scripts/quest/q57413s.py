
# Quest: Commanding Aura (57413)
# Author: Tiger

MOURI = 9130006

if sm.hasQuestCompleted(57412) and chr.getLevel() >= 20: # Echigo's Dragon
    sm.removeEscapeButton()

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendNext("Kanna, our base has been exposed to the enemy. It seems your paltry efforts weren't enough.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("Nobunaga's full army has yet to arrive, but he has plenty of strong soldiers stationed just outside Momijigaoka . Once his main force arrives, he will sweep over Momijigaoka like a tempest.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    if sm.sendAskAccept("We will not wait for the enemy to attack. We will deal the first strike and turn the tides of battle before Nobunaga has the chance. This is your task, Kanna."):
        sm.startQuest(57413)  #  Commanding Aura
        sm.sendNext("Good. Prepare yourself.")
