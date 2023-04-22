# Created by MechAviv
# Map ID :: 940011080
# Western Region of Pantheon : Heliseum Hideout

sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Bwaaaa... why... does... nothing... ever... work for me?!!")
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene1")


sm.sendDelay(900)

OBJECT_1 = sm.sendNpcController(3000140, -400, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendDelay(30)


sm.moveNpcByObjectId(OBJECT_1, False, 170, 100)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(690)


sm.forcedInput(1)
sm.sendDelay(210)


sm.forcedInput(0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Oh, Kyle! *sniff* I-I heard you were some kind of superhero now... That's great. Great for you. *sniff*")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#h0#, I was looking for you. A-are you all right?")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Me? Why do you wanna see me? Is it this thing on my arm? I didn't mean to get it stuck on there but then it just...")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I shoulda known something bad was gonna happen to me...")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#h0#...")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I... I just thought maybe I'd finally get to use magic like you guys. Instead, I get a big stupid pink bracelet and a whole lot of people mad at me... I never shoulda come with you guys.")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#h0#, I... I mean, me and Velderoth are worried about you.")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I'm sorry. I'm so sorry you two always have to worry about me. I'm just gonna stay here so you never have to worry about me again.")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("You should go on, okay? I need some time alone.")


sm.forcedInput(1)
sm.curNodeEventEnd(True)
