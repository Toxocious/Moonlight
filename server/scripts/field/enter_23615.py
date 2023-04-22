# Hidden Street : Road to the Mine 1 ; Xenon 3rd Job

# spawnPoint = 533 28
# lackey sp = 1184 28

BLACK_WINGS_LACKEY = 2159397
BLACK_WINGS_GOON = 9300643

sm.lockInGameUI(True)
sm.sendDelay(30)

sm.spawnNpc(BLACK_WINGS_LACKEY, 1184, 28)
sm.moveCamera(False, 250, 700, 28)
sm.sendDelay(2500)

# TODO: just moves back left and right
# sm.moveNpcByTemplateId(BLACK_WINGS_LACKEY, True, 100, 100)
# sm.moveCamera(True, 250, 0, 0)

sm.setSpeakerID(BLACK_WINGS_LACKEY)
sm.sendNext("Hey, what're you doing out here? And where did that other guy go? You don't look familiar...")
sm.setPlayerAsSpeaker()
sm.sendSay("I am a Black Wing.")
sm.setSpeakerID(BLACK_WINGS_LACKEY)
sm.sendSay("Are you now? Let me see here... I think I've seen your face before. I think I saw you in some orders I got from Gelimer.")
sm.setPlayerAsSpeaker()
sm.sendSay("You are mistaken.")
sm.setSpeakerID(BLACK_WINGS_LACKEY)
sm.sendSay("I am? Maybe I'd better check with Gelimer. I don't want to get into hot water. Come along!")
sm.setPlayerAsSpeaker()
sm.sendSay("Maybe I should have just taken this guy out...")

sm.moveCamera(True, 1000, 0, 0)
sm.lockInGameUI(False)
sm.removeNpc(BLACK_WINGS_LACKEY)
sm.spawnMob(BLACK_WINGS_GOON, 610, 28, False)
