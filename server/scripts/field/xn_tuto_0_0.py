# Peacetime Edelstein : Edelstein Outskirts 1 ; Xenon Intro Childhood

sm.removeEscapeButton()
isFemale = chr.getAvatarData().getAvatarLook().getGender()
# CHILDHOOD_SELF_MALE = 2159368
# CHILDHOOD_SELF_FEMALE = 2159369
CHILDHOOD_SELF = 2159368 + isFemale
CLAUDINE = 2159372
BELLE = 2159373
BRIGHTON = 2159374
ELEX = 2159375

sm.setSpeakerID(2007)
if sm.sendAskYesNo("Would you like to skip the tutorial cutscenes??"):
    sm.jobAdvance(3600)
    sm.giveItem(1142575)
    sm.giveAndEquip(1353001)
    sm.removeSkill(30021238)
    sm.giveSkill(36000004, 1)
    sm.addSP(-1)
    sm.warp(310010000, 1)
    sm.lockInGameUI(False)
    sm.dispose()

sm.lockInGameUI(True)
sm.hideUser(True)
sm.giveSkill(30021238, 1, 1)

sm.spawnNpc(BRIGHTON, 358, -14)
sm.flipNpcByTemplateId(BRIGHTON, False)
sm.spawnNpc(CLAUDINE, 432, -14)
sm.flipNpcByTemplateId(CLAUDINE, False)
sm.spawnNpc(BELLE, 512, -14)
sm.flipNpcByTemplateId(BELLE, False)
sm.spawnNpc(ELEX, 585, -14)
sm.spawnNpc(CHILDHOOD_SELF, 672, -14)

# TODO: Edelstein, long ago...

sm.setSpeakerID(BELLE)
sm.sendNext("Okay, here we go.")

sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/0", 2000, BELLE)
sm.sendDelay(2000)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/1", 2000, ELEX)
sm.sendDelay(2000)

sm.setSpeakerID(ELEX)
sm.sendNext("Red M-Forcer!")
sm.setSpeakerID(BELLE)
sm.sendSay("Yellow M-Forcer!")
sm.setSpeakerID(CLAUDINE)
sm.sendSay("Blue M-Forcer!")
sm.setSpeakerID(BRIGHTON)
sm.sendSay("Green M-Forcer!")
sm.setSpeakerID(CHILDHOOD_SELF)
sm.sendSay("Black M-Forcer!")

sm.setSpeakerID(BELLE)
sm.sendSay("All together..")

sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/2", 2000, BELLE)
sm.sendDelay(2000)
# TODO: belle hops

sm.sendNext("Oh! Awesome!")
sm.setSpeakerID(CLAUDINE)
sm.sendSay("Belle likes to pretend she's an M-Forcer.")
sm.setSpeakerID(BELLE)
sm.sendSay("It's so fun! THey are righteous heroes who protect places like Edelstein from evil! Like me!")
sm.setSpeakerID(BRIGHTON)
sm.sendSay("Too bad there's never anybody for ME to beat up")
sm.setSpeakerID(ELEX)
sm.sendSay("That's why we usually just yell at each other and dance around. It's super fun.")
sm.setSpeakerID(CHILDHOOD_SELF)
sm.sendSay("I can be the bad guy...")
sm.setSpeakerID(BELLE)
sm.sendSay("No way, #h #! We all have to be super righteous heroes! It's no fun if you're the bad guy.")
sm.setSpeakerID(CHILDHOOD_SELF)
sm.sendSay("Yes......")
sm.setSpeakerID(BRIGHTON)
sm.sendSay("Well, I guess as long as it's fun, it wouldn't matter. Maybe we can play more later.")
sm.setSpeakerID(CHILDHOOD_SELF)
sm.sendSay("I have to head home! Talk to you later!")
sm.setSpeakerID(BELLE)
sm.sendSay("See you tomorrow!")

sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/3", 2000, ELEX)
sm.sendDelay(2000)

sm.moveNpcByTemplateId(CHILDHOOD_SELF, True, 1000, 1000)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/4", 2000, BRIGHTON)
sm.sendDelay(400)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/5", 2000, CHILDHOOD_SELF)
sm.sendDelay(2000)

sm.warpInstanceIn(931050910)

sm.removeNpc(BRIGHTON)
sm.removeNpc(CLAUDINE)
sm.removeNpc(BELLE)
sm.removeNpc(ELEX)
sm.removeNpc(CHILDHOOD_SELF)
sm.hideUser(False)
sm.lockInGameUI(False)
