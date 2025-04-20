# Shaolin Temple : Sutra Depository Top (701220600)  |  Used for Cutscene in Shaolin Temple Questline

from net.swordie.ms.world.field.fieldeffect import GreyFieldType

WISE_CHIEF_PRIEST = 9310596
NINE_TAILED_FOX = 9310579
MEGAN = 9310583
OSCAR = 9310582


sm.lockInGameUI(True, False)
sm.sendDelay(1000)

sm.forcedMove(False, 300)
sm.sendDelay(3000)

sm.removeEscapeButton()
sm.setBoxChat()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Where are we? Is this still the Sutra Depository? Hey! What was that sound?!")

sm.spawnNpc(WISE_CHIEF_PRIEST, 615, 212)
sm.flipNpcByTemplateId(WISE_CHIEF_PRIEST, False)

sm.forcedMove(False, 400)
sm.sendDelay(800)

sm.moveCamera(300, 0, chr.getPosition().getY())

sm.sendNext("Chief Priest!")
sm.sendDelay(200)

sm.flipNpcByTemplateId(WISE_CHIEF_PRIEST, True)
sm.sendDelay(200)

sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/13", 1500, WISE_CHIEF_PRIEST)
sm.sendDelay(600)

sm.moveNpcByTemplateId(WISE_CHIEF_PRIEST, True, 150, 120)
sm.sendDelay(1000)

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("How did you find me? Leave at once!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Is that the Book of Demons upon that altar? The #p"+ str(WISE_CHIEF_PRIEST) +"# said it just contained research, but...")

sm.sendNext("Wait! The mirror #p"+ str(NINE_TAILED_FOX) +"# gave me! It shows a person's true form. I'll use it on him...")

sm.sendDelay(800)

sm.setFieldColour(GreyFieldType.Field, 50, 50, 50, 1500)
sm.sendDelay(1500)

sm.showEffectOnPosition("Effect/EffectCN.img/shaolin/CutScean", 3000, 445, 0)
sm.setFieldColour(GreyFieldType.Field, 250, 250, 250, 1500)
sm.sendDelay(1500)

sm.setFieldColour(GreyFieldType.Field, 250, 250, 250, 1500)
sm.spawnNpc(MEGAN, 770, 212)
sm.spawnNpc(OSCAR, 860, 212)
sm.sendDelay(1200)

sm.sendNext(sm.getQRValue(62015) +"! "+ sm.getQRValue(62018) +"!")

sm.showBalloonMsg("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/14", 1500)
sm.sendDelay(2000)

sm.sendNext("You! You did all of this! You took my friends!")

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("You're friends with demons? Intriguing. Anyway, I should be thanking you.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Thanking Me?")

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("You gave me this power! #fs22#You handed me the Book of Demons, and with it, the ability to draw demonic power and bend it to my will!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("But I-")

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("Leave now! Do not attempt to follow me!")

sm.flipNpcByTemplateId(WISE_CHIEF_PRIEST, False)
sm.moveNpcByTemplateId(WISE_CHIEF_PRIEST, False, 760, 130)
sm.sendDelay(6000)

sm.forcedMove(False, 800)
sm.sendDelay(5000)

sm.warpInstanceOut(701220601)
sm.lockInGameUI(False)
sm.dispose()