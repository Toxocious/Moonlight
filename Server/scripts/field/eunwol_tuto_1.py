# 927030000
PHANTOM = 2159439
LUMINOUS = 2159467

sm.lockInGameUI(True, False)
sm.hideUser(True)
sm.forcedInput(0)

sm.spawnNpc(PHANTOM, 1210, 10)
sm.showNpcSpecialActionByTemplateId(PHANTOM, "summon")

sm.spawnNpc(LUMINOUS, 830, 10)
sm.showNpcSpecialActionByTemplateId(LUMINOUS, "summon")
sm.sendDelay(1000)

sm.removeEscapeButton()

sm.setSpeakerID(PHANTOM)
sm.sendNext("I knew I wasn't going to like you from the start. You're too stuffy.")

sm.setSpeakerID(LUMINOUS)
sm.sendSay("The feeling is mutual, I assure you.")

sm.setSpeakerID(PHANTOM)
sm.sendSay("I'm glad we're on the same page. And yet we were sent here together to wait for the end... Maybe Freud has a better sense of humor than I thought.")
sm.sendDelay(500)

sm.hideUser(False)
sm.avatarOriented("Effect/BasicEff.img/Summoned")
sm.sendDelay(2000)

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("Who knows, but he might get mad for real if we keep putting off time.")

sm.setSpeakerID(PHANTOM)
sm.sendSay("Freud? Mad? Now that's funny.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("More like we won't ever see each other again. I'll head in first, I'm feeling a bad energy.")

sm.forcedInput(1)
