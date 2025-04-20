
# Quest: Dark Times (57407)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57406) and chr.getLevel() >= 16: # Adaptation 3
    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("Well...I just had a big meal. You can't expect me to perform on a full stomach. Besides, shouldn't we be finding out where master is? And Princess Sakuno? Nobunaga's plans for her probably don't align with ours.")
    sm.sendSay("Could Nobunaga have come to this world too? It's not out of the question. I need to start finding some answers.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("You'll need your real skills back too. It's no fun watching you fight anymore.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I'll get there. I don't care if I have to kill 5,000 enemies.")

    sm.completeQuest(57407)  #  Dark Times
    sm.giveItem(2000001, 100) # Orange Potion x 100
    sm.giveItem(2000003, 200) # Blue Potion x 100
