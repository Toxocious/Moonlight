
# Quest: Aura's Origin 1 (57414)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57413) and chr.getLevel() >= 20: # Commanding Aura
    sm.removeEscapeButton()

    if sm.getFieldID() != 807020000: # Momijigaoka : Momijigahara 1
        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendNext("I must hurry to #b#m807020000##k.")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("#b#m807020000##k is right over there.")

        # TODO: Show direction to -> # Momijigaoka : Momijigahara 1 (807020000)
    else:
        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendNext("This is Momijigahara. Oda's Army is already building a camp. They move faster than I imagined. Monotari's intuition may have saved us.")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("The energy's stronger here. Don't you just wanna lick it?")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("Oda's army must have brought something with them to generate this much energy. I have to find the source. I won't stop until I've defeated 50 soldiers. One of them must carry the energy.")

        sm.startQuest(57414)  #  Aura's Origin 1
