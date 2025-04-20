# 927030020
EUNWOL = 2159449
LUMINOUS = 2159467
FREUD = 2159445

sm.lockInGameUI(True, False)
sm.hideUser(True)
sm.forcedInput(0)

sm.spawnNpc(EUNWOL, 215, -85)
sm.showNpcSpecialActionByTemplateId(EUNWOL, "summon")
sm.moveCamera(False, 400, 215, -85)

sm.blind(1, 200, 0, 1000)
sm.sendDelay(1000)

sm.sayMonologue("\r\n\r\nThings were looking grim.", False)
sm.sayMonologue("\r\nThe Black Mage was stronger than anyone expected.", False)
sm.sayMonologue("\r\nThe heroes could not overcome the incredible power wielded by the Black Mage.", True)

sm.sayMonologue("\r\n\r\n\r\n\r\nThere was only one option left,\r\n\r\n\r\n                        to seal the Black Mage behind the Seal of Time.", True)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1000)


sm.removeEscapeButton()

sm.setSpeakerID(LUMINOUS)
sm.sendNext("I've activated all five seals! ...Now what?")

sm.setSpeakerID(FREUD)
sm.sendSay("All there is left to do is take the Black Mage's Power of Time. There's just...one catch.")

sm.setSpeakerID(LUMINOUS)
sm.sendSay("Catch? What kind of catch?")

sm.setSpeakerID(FREUD)
sm.sendSay("To activate the Seal of Time, we need the time from a person, a #r#eliving person#k#n. Like a sacrifice. Don't make that face. I will do it.")

sm.setSpeakerID(LUMINOUS)
sm.sendSay("Don't say that! Why would you do that?")

sm.setSpeakerID(FREUD)
sm.sendSay("Because I made it. Besides, I like the idea of being the big hero for once! I'm tired of cleaning up your messes anyway.")

sm.setSpeakerID(LUMINOUS)
sm.sendSay("How can you make jokes in this situation? No, I'll do it. I've got nothing to lose.")

sm.setSpeakerID(FREUD)
sm.sendSay("Luminous, I know how you feel, but this...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I'll do it.")

sm.setSpeakerID(FREUD)
sm.sendSay("Oh, stop it. We have the fate of Maple World in our hands right now. We can't be getting emotional now.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("That's why I'll do it. You know better than anyone here that your health cannot handle the seal.")

sm.setSpeakerID(FREUD)
sm.sendSay("......")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Luminous, you're the only one who can activate the seal. We need your power of light to end this.")

sm.setSpeakerID(LUMINOUS)
sm.sendSay("But we have to sacrifice someone! How can we decide something like that?")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("It's easy. Let me do it. I have no family, no clan, no one to miss me. Except you lot, I suppose.")
sm.sendSay("Look, we're running out of time. Let's get this over with, and then we can argue about it in the afterlife. Deal?")
sm.sendDelay(500)

sm.moveNpcByTemplateId(LUMINOUS, True, 180, 100)
sm.sendDelay(2000)

sm.showBalloonMsg("Effect/Direction15.img/effect/story/BalloonMsg0/0", 0)
sm.sendDelay(2500)

sm.showBalloonMsg("Effect/Direction15.img/effect/story/BalloonMsg0/1", 0)
sm.sendDelay(2000)

sm.showBalloonMsgOnNpc("Effect/Direction15.img/effect/story/BalloonMsg0/2", 0, LUMINOUS)
sm.sendDelay(2000)

sm.showBalloonMsgOnNpc("Effect/BasicEff.img/Teleport", 0, 0, 0, LUMINOUS)
sm.removeNpc(LUMINOUS)
sm.sendDelay(300)

sm.moveCamera(True, 400, 0, 0)

sm.hideUser(False)
sm.avatarOriented("Effect/BasicEff.img/Teleport")
sm.sendDelay(1000)

sm.sendNext("All right, let's do this.")
sm.sendDelay(500)

sm.createQuestWithQRValue(38907, "2")
sm.warp(927030050, 0)