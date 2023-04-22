# Created by MechAviv
# Map ID :: 940001200
# Eastern Region of Pantheon : Near East Sanctum

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(3000103, -1400, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000104, -1650, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Wow, what lovely weather!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Get your head out of the clouds. #h0# and I have already become knights. When are you going to become a knight?")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I dunno. I don't have magic power or anything. Maybe I should look for some!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm pretty sure you've told me that one about a thousand times.")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Anyways, you guys are knights! That's so cool! You can ride on horses and wear shiny armor and stuff, right? I want in on that!")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I'm not sure you're cut out to be a knight, Tear.")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Do what?! I thought we all promised to become knights! That's what the Heliseum Force is all about, right? Well, that and awesome cook-outs.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yeah, but you don't use magic. You have to face the truth sometime...")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Ugh, why do you keep bringing that up? Magic is no big thing.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I just want you to think sometimes. Anyway, I gotta get back.")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Man, I wish I was going too.")


sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg1/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Really? Because I don't feel so good about this.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("What are you talking about? Come on, you can daydream about smooching with #h0# on the way back to camp.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No... Something's there. I think something is happening at the East Sanctum.")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Well, let's go check it out! I wonder what's going on?")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Tear, are you really going to fall for that rubbish?")


sm.setSpeakerID(3000103)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Well, #h0# is a pretty good guesser. And I don't have anything else to do.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I wish I had the authority to overrule you people... Fine. Let's go.")


sm.moveNpcByObjectId(OBJECT_1, False, 400, 100)
sm.moveNpcByObjectId(OBJECT_2, False, 400, 100)
sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg0/3", 1200, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(600)


sm.forcedInput(2)
