# Created by MechAviv
# Map ID :: 749080900
# Alishan : Leshan
if sm.hasQuest(55234):
    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendNext("-ort me... Dang it, they ALWAYS do that!")

    
    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendSay("I don't recognize this place at all. Why is it so hard for me to stay in Maple World?")


    sm.completeQuest(55234)