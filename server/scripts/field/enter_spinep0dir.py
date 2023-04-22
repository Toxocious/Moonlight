# Created by MechAviv

if sm.getFieldID() == 330002040:
    # Map ID :: 330002040
    # Shinsoo International School : Front Gate
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.hideUser(True)
    sm.removeAdditionalEffect()
    OBJECT_7 = sm.sendNpcController(1530010, -1620, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
    OBJECT_8 = sm.sendNpcController(1530100, -300, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_8, "summon", 0)
    OBJECT_9 = sm.sendNpcController(1530080, 100, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_9, "summon", 0)
    OBJECT_10 = sm.sendNpcController(1530090, 200, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_10, "summon", 0)
    sm.blind(1, 255, 0, 0, 0, 0, 0)
    sm.sendDelay(1200)


    sm.blind(0, 0, 0, 0, 0, 1000, 0)
    sm.sendDelay(1400)


    sm.setSpeakerID(1530011)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendNext("Empress, what are you doing here?")


    sm.moveCamera(False, 100, -1933, 132)


    sm.sendDelay(4059)


    sm.flipNpcByObjectId(OBJECT_7, True)
    sm.sendDelay(500)


    sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs26#Ereve\r\n#fs14#- Empress' Resting Spot -", 100, 1800, 4, 0, 0, 1, 4, 0, 0, 0)
    sm.sendDelay(5000)

    sm.setSpeakerID(1530010)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendNext("Hello, #p1530011#.")


    sm.setSpeakerID(1530011)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Is something on your mind?")


    sm.setSpeakerID(1530010)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Ha... I had a funny dream.")


    sm.setSpeakerID(1530011)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Oh? What was it?")


    sm.setSpeakerID(1530010)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("I dreamed that I was the #p1530010# from another world.")


    sm.setSpeakerID(1530011)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Another world...?")


    sm.setSpeakerID(1530010)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Yes, a world very different from this one.")


    sm.setSpeakerID(1530010)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("No dragons or magic, but wondrous machines and a great society that spanned the globe.")


    sm.setSpeakerID(1530010)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("I was the daughter of a rich family. I was a student!")


    sm.setSpeakerID(1530011)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Fascinating. Was anyone you know in this dream with you?")


    sm.setSpeakerID(1530010)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Ha, that's my secret. Anyway, what's on my schedule this week, #p1530011#?")


    sm.setSpeakerID(1530011)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("There is a ceremony for the knights coming up. Let's be on our way, and I can give you the details as we walk.")


    sm.moveCamera(False, 200, -1933, -773)


    sm.sendDelay(4519)


    sm.changeBGM("Bgm00.img/Silence", 0, 0)
    sm.setIntroBoxChat(1530100)
    sm.setSpeakerType(3)
    sm.sendNext("Mihile, Eckhart! Wait up, bros!")


    sm.changeBGM("Bgm38.img/SchoolLife", 0, 0)
    sm.moveCamera(False, 2000, -83, -773)


    sm.sendDelay(924)


    sm.moveCamera(False, 200, -83, 117)


    sm.sendDelay(4451)


    sm.moveNpcByObjectId(OBJECT_8, False, 220, 100)
    sm.sendDelay(500)


    sm.flipNpcByObjectId(OBJECT_9, True)
    sm.flipNpcByObjectId(OBJECT_10, True)
    sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs26#Shinsoo International School\r\n#fs14#- 2019, A Street in Seoul -", 100, 2500, 4, 0, 0, 1, 4, 0, 0, 0)
    sm.sendDelay(5000)

    sm.setIntroBoxChat(1530100)
    sm.setSpeakerType(3)
    sm.sendNext("Why you guys bookin' it so hard? Hold up!")


    sm.setIntroBoxChat(1530090)
    sm.setSpeakerType(3)
    sm.sendSay("I've got places to be. I don't know what HIS excuse is.")


    sm.setIntroBoxChat(1530080)
    sm.setSpeakerType(3)
    sm.sendSay("Same here.")


    sm.setIntroBoxChat(1530100)
    sm.setSpeakerType(3)
    sm.sendSay("Aww, did you two have another fight?\r\nNot like I care, though. There was this smokin' chick on TV last night-")


    OBJECT_11 = sm.sendNpcController(1530370, -400, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_11, "summon", 0)
    sm.sendDelay(500)


    sm.setIntroBoxChat(1530370)
    sm.setSpeakerType(3)
    sm.sendNext("Hawkeye! Get over here! I know you were the one that broke that window in the lounge yesterday!")


    sm.setIntroBoxChat(1530100)
    sm.setSpeakerType(3)
    sm.sendSay("Ahh, Stan's ruining my morning again! I'm out, catch you guys later!")


    sm.moveNpcByObjectId(OBJECT_8, False, 1300, 250)
    sm.moveNpcByObjectId(OBJECT_11, False, 1400, 250)
    sm.sendDelay(5500)


    sm.setIntroBoxChat(1530100)
    sm.setSpeakerType(3)
    sm.sendNext("#face0#Urgh! Hey, let go of me! I've got rights, ya know!")


    sm.setIntroBoxChat(1530370)
    sm.setSpeakerType(3)
    sm.sendSay("Not with me you don't!")


    sm.sendDelay(500)


    OBJECT_12 = sm.sendNpcController(1530110, -200, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_12, "summon", 0)
    OBJECT_13 = sm.sendNpcController(1530120, -300, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_13, "summon", 0)
    sm.setIntroBoxChat(1530120)
    sm.setSpeakerType(3)
    sm.sendNext("Hawkeye's busted again. I think it's gonna be a good day.")


    sm.moveNpcByObjectId(OBJECT_12, False, 80, 100)
    sm.moveNpcByObjectId(OBJECT_13, False, 80, 100)
    sm.sendDelay(1500)
    sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 1530080)


    sm.setIntroBoxChat(1530120)
    sm.setSpeakerType(3)
    sm.sendNext("And look, it's Mihile and Eckhart.")


    sm.setIntroBoxChat(1530110)
    sm.setSpeakerType(3)
    sm.sendSay("Why do you two hang out all the time? You must love fighting with each other.")


    sm.sendDelay(500)


    sm.sendDelay(2500)
    sm.speechBalloon(True, 0, 0, "No way.", 2500, 1, 0, 0, 0, 4, 1530080)
    sm.speechBalloon(True, 0, 0, "No way.", 2500, 1, 0, 0, 0, 4, 1530090)


    sm.setIntroBoxChat(1530110)
    sm.setSpeakerType(3)
    sm.sendNext("Aww, that's sho cute!")


    sm.showEffect("Effect/OnUserEff.img/emotion/ddam", 0, 0, 0, 0, OBJECT_9, False, 0)
    sm.showEffect("Effect/OnUserEff.img/emotion/ddam", 0, 0, 0, 0, OBJECT_10, False, 0)
    sm.sendDelay(2500)


    sm.sendDelay(2500)
    sm.speechBalloon(True, 0, 0, "*Sigh*", 2500, 1, 0, 0, 0, 4, 1530080)
    sm.speechBalloon(True, 0, 0, "*Sigh*", 2500, 1, 0, 0, 0, 4, 1530090)


    sm.sendNpcController(OBJECT_8, False)
    sm.sendNpcController(OBJECT_11, False)
    sm.flipNpcByObjectId(OBJECT_9, False)
    sm.flipNpcByObjectId(OBJECT_10, False)
    sm.moveNpcByObjectId(OBJECT_9, False, 800, 120)
    sm.moveNpcByObjectId(OBJECT_10, False, 800, 120)
    sm.moveCamera(False, 200, 417, 117)


    sm.sendDelay(4500)


    OBJECT_14 = sm.sendNpcController(1530070, 400, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_14, "summon", 0)
    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendNext("What are you guys doing here?")


    OBJECT_15 = sm.sendNpcController(1530060, -400, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_15, "summon", 0)
    OBJECT_16 = sm.sendNpcController(1530360, -550, 100)
    sm.showNpcSpecialActionByObjectId(OBJECT_16, "summon", 0)
    sm.moveCamera(False, 200, 117, 117)
    sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 1530120)


    sm.moveNpcByObjectId(OBJECT_14, True, 300, 100)
    sm.sendDelay(3500)


    sm.setIntroBoxChat(1530120)
    sm.setSpeakerType(3)
    sm.sendNext("Well hello to you too, Mr. Class President. Are you here to make sure Cygnus gets to school safely? How loyal of you.")


    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendSay("Yes, I am. Since it is one of my duties as Class President. Or did you forget she practically owns the school?")


    sm.moveNpcByObjectId(OBJECT_12, False, 800, 120)
    sm.moveNpcByObjectId(OBJECT_13, False, 800, 120)
    sm.moveNpcByObjectId(OBJECT_14, True, 250, 100)
    sm.moveCamera(False, 200, -83, 117)


    sm.sendDelay(1000)


    sm.sendDelay(5000)


    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendNext("Good morning, Miss Cygnus.")


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendSay("Oh, good morning Neinheart.")


    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendSay("You look like something's on your mind.")


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendSay("Ha, I had a funny dream last night.")


    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendSay("Really? What was it?")


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendSay("I had a dream that I was the Cygnus from another world.")


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendSay("That world had dragons and magic, and I was an empress with lots of knights, and I lived on an island that floats in the sky. Pretty neat, right?")


    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendSay("Sounds like something from a video game. I didn't know you were into that.")


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendSay("#face0#Ha ha. Yeah, it was pretty ridiculous.\r\nSo, Neinheart, what do we have planned for this week?")


    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendSay("We have a new #btransfer student#k coming this week. We'll need to-")


    sm.sendDelay(2500)
    sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 1530060)


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendNext("Transfer student? We've got a transfer student coming?")


    sm.setIntroBoxChat(1530070)
    sm.setSpeakerType(3)
    sm.sendSay("Hm...? Why are you so excited?")


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendSay("Ah, it's nothing.")


    sm.sendDelay(200)


    sm.showEffect("Effect/OnUserEff.img/emotion/love", 0, 0, 0, 0, OBJECT_15, False, 0)
    sm.sendDelay(2000)


    sm.setIntroBoxChat(1530060)
    sm.setSpeakerType(3)
    sm.sendNext("I just... like meeting new students.")


    sm.blind(1, 255, 0, 0, 0, 500, 0)
    sm.sendDelay(500)


    sm.hideUser(False)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.sendNpcController(OBJECT_7, False)
    sm.sendNpcController(OBJECT_9, False)
    sm.sendNpcController(OBJECT_10, False)
    sm.sendNpcController(OBJECT_12, False)
    sm.sendNpcController(OBJECT_13, False)
    sm.sendNpcController(OBJECT_14, False)
    sm.sendNpcController(OBJECT_15, False)
    sm.sendNpcController(OBJECT_16, False)
    sm.warp(330002041, 0)
elif sm.getFieldID() == 330002041:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.hideUser(True)
    sm.removeAdditionalEffect()
    sm.blind(1, 255, 0, 0, 0, 0, 0)
    sm.sendDelay(1200)


    sm.blind(0, 0, 0, 0, 0, 1000, 0)
    sm.sendDelay(1400)


    sm.sendDelay(1000)


    sm.showFieldEffect("Map/Effect2.img/spinOff1/title", 0)
    sm.sendDelay(2500)


    sm.blind(1, 255, 0, 0, 0, 500, 0)
    sm.sendDelay(500)


    sm.hideUser(False)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(330002042, 0)
elif sm.getFieldID() == 330002042:
    # Map ID :: 330002042
    # Shinsoo International School : Dorm
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.hideUser(True)
    sm.removeAdditionalEffect()
    sm.blind(1, 255, 0, 0, 0, 0, 0)
    OBJECT_1 = sm.sendNpcController(1530000, 320, 5)
    sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
    sm.sendDelay(1000)


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendNext("Oh man, another day at school...")


    sm.blind(1, 255, 0, 0, 0, 0, 0)
    sm.sendDelay(1200)


    sm.blind(0, 0, 0, 0, 0, 1000, 0)
    sm.sendDelay(1400)


    sm.moveNpcByObjectId(OBJECT_1, False, 150, 150)
    sm.sendDelay(2500)


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendNext("I'm so sick of this grind. Every day, the same old garbage.")


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendSay("Who wants to go to an 'international school', anyway?\r\nAnd what kind of a name is #bShinsoo International School#k?")


    sm.moveNpcByObjectId(OBJECT_1, True, 150, 150)
    sm.sendDelay(2500)


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendNext("I never fit in wherever I go. It's not gonna be any better here.")


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendSay("God, it's all so BORING! Why couldn't life be more exciting? I wish I could be #banywhere but here#k!")


    sm.sendDelay(2000)
    sm.speechBalloon(True, 0, 0, "...?!", 2500, 1, 0, 0, 0, 4, 1530000)


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendNext("...What's with that closet?")


    sm.moveNpcByObjectId(OBJECT_1, True, 50, 100)
    sm.sendDelay(2000)


    sm.showEffect("Map/Obj/spinOff1.img/frame/ani/0", 10000, 0, -30, 0, OBJECT_1, False, 0)
    sm.sendDelay(300)


    sm.showEffect("Map/Obj/spinOff1.img/frame/ani/0", 10000, 10, -60, 0, OBJECT_1, False, 0)
    sm.sendDelay(300)


    sm.showEffect("Map/Obj/spinOff1.img/frame/ani/0", 10000, 20, -55, 0, OBJECT_1, False, 0)
    sm.sendDelay(300)


    sm.showEffect("Map/Obj/spinOff1.img/frame/ani/0", 10000, -10, -70, 0, OBJECT_1, False, 0)
    sm.sendDelay(300)


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendNext("W-whaaaaaa THIS ISN'T WHAT I MEANT!!")


    sm.blind(1, 255, 240, 240, 240, 1300, 0)
    sm.sendDelay(1600)


    sm.hideUser(False)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.startQuest(32707)
    sm.sendNpcController(OBJECT_1, False)
    sm.warp(100000004, 0)
