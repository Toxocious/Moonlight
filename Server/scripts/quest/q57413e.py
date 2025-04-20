
# Quest: Commanding Aura (57413)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57412) and chr.getLevel() >= 20: # Echigo's Dragon
    sm.removeEscapeButton()

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendNext("Kanna, do you feel that energy?")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Yes. It's faint, but familiar. It's coming from somewhere nearby.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("It feels extremely powerful, even at this distance. It's coming from...over there!")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I think Momijigahara lies in that direction. Motonari said Oda's Army appeared there. We have to see for ourselves.")

    sm.completeQuest(57413)  #  Commanding Aura
