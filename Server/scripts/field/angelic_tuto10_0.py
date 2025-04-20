# Created by MechAviv
# Map ID :: 940011100
# Eastern Region of Pantheon : East Sanctum

sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg2/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Just put that on your little finger. ")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Is this gonna shock me or something? I hate pranks...")


sm.giveAndEquip(1222001)
sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Would you just put the stupid thing on so I can make you powerful?!")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.warp(940012000, 0)
