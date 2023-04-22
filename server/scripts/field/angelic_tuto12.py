# Created by MechAviv
# Map ID :: 940011150
# Eastern Region of Pantheon : East Sanctum

# [SET_DRESS_CHANGED] [01 00 ]
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(1)
sm.sendDelay(60)
# Unhandled User Effect [ResetOnStateForOnOffSkill] Packet: 33


sm.forcedInput(0)
sm.sendDelay(300)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/4", 900, 0, -120, -2, -2, False, 0)
sm.sendDelay(150)


sm.forcedInput(2)
sm.sendDelay(60)


sm.forcedInput(1)
sm.sendDelay(60)


sm.forcedInput(2)
sm.sendDelay(90)


sm.forcedInput(1)
sm.sendDelay(60)


sm.forcedInput(0)
sm.sendDelay(510)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/9", 900, 0, -120, -2, -2, False, 0)
sm.sendDelay(900)


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Mwahahaha, how's THAT for power?!")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("That was so much fun! It was all PEW PEW KABLOOOM!!")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Uh, yes. Yes it was sort of like that, only a lot less stupid.")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Hehehe, hahahaha, HAHAHAHAHA!")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You didn't hear a single word I said, did you? HEY! Enough with the terrifying laughter. You need some practice to use my powers properly.")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Awww, practice? I don't wannnaaaa....")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("My powers require a transformation to really be effective. I think you should give it a shot. ")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Oh, okay!")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Hehehe... Ahem, then look deep into your heart, feel my presence, and say REALLY loud... Dress-Up!")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Dress-Up?")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.warp(940012020, 0)
