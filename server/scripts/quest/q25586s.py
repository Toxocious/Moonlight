# Created by MechAviv
# Quest ID :: 25586
# Light Versus Dark

sm.setSpeakerID(1032209)
sm.sendNext("Luminous? Is that you? But what's this darkness I'm sensing on you?")


sm.setSpeakerID(1032209)
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("You yet live? What a surprise.")


sm.setSpeakerID(1032209)
sm.sendSay("Luminous!")


sm.setSpeakerID(1032209)
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("You've no need to worry! I've finally seen the light, as it were. I've claimed the power of the Dark!")


sm.setSpeakerID(1032209)
sm.sendSay("Huh. Luminous, the guardian of Light and hero who defeated the Black Mage, now a Dark Magician himself...")


sm.setSpeakerID(1032209)
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("You should try it for yourself, Vieren. It feels...indescribable!")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.sendSay("...")

sm.startQuest(25586)
sm.completeQuest(25586)
sm.giveExp(9136)
sm.warp(910142050, 0)
