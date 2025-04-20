# Created by MechAviv
# Map ID :: 940001100
# Heliseum : Heliseum Outskirts

OBJECT_1 = sm.sendNpcController(3000131, -390, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("magnus", OBJECT_1)
sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Kaiser...you're late.")


sm.moveCamera(False, 450, -600, 178)


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Magnus! What are you doing here? What happened to Heliseum?")


sm.forcedInput(2)
sm.curNodeEventEnd(True)
