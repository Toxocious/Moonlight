# Created by MechAviv
# Quest ID :: 25510
# The Middle Road

sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.sendNext("Luminous! Snap out of it, will you? I'll resonate with the power of Light and draw the Dark out of you. Maybe then you'll come to your senses.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendSay("(Vieren's voice seems to be calming me. Peculiar.)")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendSay("The Dark no longer clouds my mind. You have my thanks.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.sendSay("It was nothing. All I did was help you find the strength to control your Dark. Here, this Aurora Prism will let you come and go as you please.")

sm.giveItem(2430874)
sm.setJob(2710)
sm.giveSkill(27101100, 1, 20)
sm.giveSkill(27101202, 1, 20)
sm.addSP(2, True)
# Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 D3 11 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 43 15 00 00 FF 00 00 00 00
sm.startQuest(25510)
sm.completeQuest(25510)