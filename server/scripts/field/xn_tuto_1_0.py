# Peacetime Edelstein : Edelstein Outskirts 2 ; Xenon Intro Abduction Scene

isFemale = chr.getAvatarData().getAvatarLook().getGender()

sm.lockInGameUI(True)
sm.removeEscapeButton()
sm.hideUser(True)

# CHILDHOOD_SELF_MALE = 2159368
# CHILDHOOD_SELF_FEMALE = 2159369
# ABDUCTED_CHILDHOOD_SELF_MALE = 2159370
# ABDUCTED_CHILDHOOD_SELF_FEMALE = 2159371
GELIMER_PEACETIME = 2159376
CHILDHOOD_SELF = 2159368 + isFemale
ABDUCTED_CHILDHOOD_SELF = 2159370 + isFemale
CLAUDINE = 2159372


# sm.spawnNpc(CHILDHOOD_SELF, -1050, -14)
sm.spawnNpc(CHILDHOOD_SELF, -1397, -14)
sm.spawnNpc(GELIMER_PEACETIME, -1808, -14)
sm.flipNpcByTemplateId(GELIMER_PEACETIME, False)

# TODO: He won't move :()
# move CHILDHOOD_SELF left until on 2nd yellow flower after lamp post  (track camera)
# sm.moveNpcByTemplateId(CHILDHOOD_SELF, True, 1000, 1000)
# sm.sendDelay(10000)

sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/story/BalloonMsg0/0", 2000, CHILDHOOD_SELF)
sm.sendDelay(2000)

sm.setSpeakerID(CHILDHOOD_SELF)
sm.sendNext("Who is that grandpa? I don't think he's from this town...")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/6", 2000, GELIMER_PEACETIME)
sm.sendDelay(2000)
# TODO: two robots spawn around childhood self (not yet abducted) ->
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg1/0", 2000, CHILDHOOD_SELF)
sm.sendDelay(2000)
sm.removeNpc(CHILDHOOD_SELF)
# TODO: fix the position of this
sm.spawnNpc(ABDUCTED_CHILDHOOD_SELF, -1397, -14)
sm.setSpeakerID(GELIMER_PEACETIME)

sm.sendNext("Finally, I find what I'm looking for... It's a good thing I looked all over town.")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/8", 2000, GELIMER_PEACETIME)
sm.sendDelay(2000)

# TODO: They're not moving
# sm.moveNpcByTemplateId(ABDUCTED_CHILDHOOD_SELF, True, 1000, 1000)
# sm.moveNpcByTemplateId(GELIMER_PEACETIME, True, 1000, 1000)
# sm.sendDelay(10000)

# TODO: fix below
# sm.moveCamera(False, 1000, -400, 0)
# move camera right until childhood self is on left edge -> claudine moves left onto screen, moves right left right left

sm.spawnNpc(CLAUDINE, -530, -14)

sm.setSpeakerID(CLAUDINE)
sm.sendNext("Did #h # already go home? I was going to return the dagger I borrowed.")
sm.sendSay("I'll give it back tomorrow.")

sm.removeNpc(CLAUDINE)
sm.removeNpc(ABDUCTED_CHILDHOOD_SELF)
sm.removeNpc(GELIMER_PEACETIME)
sm.warp(931060080)
sm.hideUser(False)
sm.lockInGameUI(False)
