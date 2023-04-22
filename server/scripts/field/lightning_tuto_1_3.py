# Hidden Street : Destroyed Temple of Time Entrance (927020000) | Used in Luminous' Intro
GUWARU = 2159354
MAGNUS = 2159355
sm.removeEscapeButton()
sm.setSpeakerID(GUWARU)
sm.sendNext("The light you possess is like a warm ray of sunshine to the spirits. It will be painful to see it extinguished...")

sm.setPlayerAsSpeaker()
sm.sendSay("If you feel so strongly about my preservation, turn away from this insanity. Turn away from the Black Mage!")

sm.setSpeakerID(GUWARU)
sm.sendSay("If doing what I believe is labeled as 'insanity,' then I will gladly bear the stigma. Though you and your kind will bear it with me...")

sm.setPlayerAsSpeaker()
sm.sendSay("Enough with the sophistry, #p2159354#.")

sm.setSpeakerID(GUWARU)
sm.sendSay("I thought I would enjoy some pre-dinner conversation, but I will be happy to end you now.")
sm.sendDelay(500)

sm.showNpcSpecialActionByTemplateId(GUWARU, "special", 0)
sm.playSound("LuminousTuto/Special1", 100)
sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0")
sm.sendDelay(1600)

sm.playSound("LuminousTuto/Special2", 100)
sm.sendDelay(2280)

sm.spawnNpc(MAGNUS, 0, 10)
sm.flipNpcByTemplateId(MAGNUS, False)
sm.showNpcSpecialActionByTemplateId(MAGNUS, "summon", 0)
sm.sendDelay(700)

sm.removeNpc(GUWARU)

sm.setSpeakerID(MAGNUS)
sm.flipSpeaker()
sm.sendNext("You served that fool up on a platter for me!")

sm.setPlayerAsSpeaker()
sm.sendSay("#p2159355#! Y-you destroyed him!")

sm.forcedAction(443, 540)
sm.showEffect("Skill/2711.img/skill/27111100/prepare", 540, -40, -25, -2, -2, False, 0)
sm.playSound("LuminousTuto/Use", 100)
sm.sendDelay(90)

sm.showNpcSpecialActionByTemplateId(MAGNUS, "barrier", 0)
sm.sendDelay(450)

sm.forcedAction(444, 3000)
sm.showEffect("Skill/2711.img/skill/27111100/keydown", 3000, -40, -25, -2, -2, False, 0)
sm.playSound("LuminousTuto/Loop", 100)
sm.sendDelay(30)

for i in range(8):
    sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, sm.getNpcObjectIdByTemplateId(MAGNUS), False, 0)
    sm.playSound("LuminousTuto/Hit", 100)
    sm.sendDelay(270)

sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, sm.getNpcObjectIdByTemplateId(MAGNUS), False, 0)
sm.playSound("LuminousTuto/Loop", 100)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


for i in range(2):
    sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, sm.getNpcObjectIdByTemplateId(MAGNUS), False, 0)
    sm.playSound("LuminousTuto/Hit", 100)
    sm.sendDelay(270)

sm.showEffect("Skill/2711.img/skill/27111101/keyedownend", 0, -40, -25, -2, -2, False, 0)
sm.playSound("LuminousTuto/End", 100)
sm.sendDelay(600)

sm.setSpeakerID(MAGNUS)
sm.flipSpeaker()
sm.sendNext("How cute. Well, I've got to go kill your friends! See you!")

sm.setPlayerAsSpeaker()
sm.sendSay("What are you talking about?!")

sm.setSpeakerID(MAGNUS)
sm.flipSpeaker()
sm.sendSay("I don't have time to play with you! Ha. I'm done with this world, anyway.")

sm.showNpcSpecialActionByTemplateId(MAGNUS, "teleportation", 0)
sm.sendDelay(450)

sm.removeNpc(MAGNUS)
sm.setPlayerAsSpeaker()
sm.sendNext("I've no time left to worry about Magnus. The Black Mage awaits!")

sm.lockInGameUI(False)
sm.warp(927020010, 0)