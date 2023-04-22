# Created by MechAviv
# Map ID :: 620100041
# Nautilus : Wrecked Airship 1

if "1" not in sm.getQRValue(5673):
    sm.setSpeakerID(9270086)
    sm.setSpeakerType(3)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipBoxChat()
    sm.sendNext("Check that portal over yonder. That thing I saw was heading in that direction.")
else:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.setSpeakerID(9270086)
    sm.setSpeakerType(3)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipBoxChat()
    sm.sendNext("What are you thinking, crashing that hunk of junk on my beaches? You're helpin' me clean up.")


    sm.curNodeEventEnd(True)
    sm.setSpeakerID(9270086)
    sm.setSpeakerType(3)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipBoxChat()
    sm.sendSay("You came hurtling out of the sky at a thousand miles an hour, explode, and you think I'M gonna take care of this myself?")


    sm.setSpeakerID(9270086)
    sm.setSpeakerType(3)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.boxChatPlayerAsSpeaker()
    sm.sendSay("Have you seen a guy with clothes like mine? He was with me... ")


    sm.setSpeakerID(9270086)
    sm.setSpeakerType(3)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipBoxChat()
    sm.sendSay("I swear I saw something black and red dive out of that wreckage before I came up. Red hair, maybe?")


    sm.sendDelay(1000)


    sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/15", 2000, 0, -100, -2, -2, False, 0)
    sm.sendDelay(1500)


    sm.setSpeakerID(9270086)
    sm.setSpeakerType(3)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.boxChatPlayerAsSpeaker()
    sm.sendNext("#b(That sorta sounds like #p9270083#. He must be scouting the area for enemies. Couldn't ask for a better captain.)#k")


    sm.createQuestWithQRValue(5673, "1")
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
