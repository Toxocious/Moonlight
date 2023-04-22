# Created by MechAviv
# Map ID :: 100000000
# NPC ID :: 9111063
# Heart of Zipangu

sm.setSpeakerID(0)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.setSpeakerType(3)

if sm.sendNext("\r\n\r\n#L0# #m100000000# awaits.#l") == 0:
    sm.setNpcOverrideBoxChat(9111063)
    sm.setSpeakerType(3)
    if sm.sendAskYesNo("Shall I transport you to #m100000000#?"):
        sm.warp(100000000, 5)