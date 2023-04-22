# Created by MechAviv
# Quest ID :: 55234
# [Alishan] Spirit's Whisper

sm.setSpeakerID(9330458)
sm.flipDialoguePlayerAsSpeaker()
if sm.sendAskAccept("Hmm, I thought I heard something... Maybe I should pay more attention."):
    sm.setSpeakerID(9330458)
sm.flipDialoguePlayerAsSpeaker()
if sm.sendAskAccept("Hmm, I thought I heard something... Maybe I should pay more attention."):
    sm.setNpcOverrideBoxChat(9330458)
    sm.sendNext("Hello... anyone there? Please, someone answer.")


    sm.setSpeakerID(9330458)
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendSay("H-hello? Spooky voice?")


    sm.setNpcOverrideBoxChat(9330458)
    sm.sendSay("Yes? Can you hear me?")


    sm.setSpeakerID(9330458)
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendSay("Yes, but where are you? This is creepy.")


    sm.setNpcOverrideBoxChat(9330458)
    sm.sendSay("Oh, thank goodness! I need your help. Shao...")


    sm.setSpeakerID(9330458)
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendSay("Okay, but please don't telep-")
    
    sm.startQuest(55234)
    sm.warp(749080900, 0)
else:
    sm.setSpeakerID(9330458)
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendSayOkay("Am I hearing things?")


