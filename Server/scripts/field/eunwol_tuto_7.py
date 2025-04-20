# 940200010
MOONBEAM = 3002100
TWITCH = 3002004
LUMPS = 3002001
SALLY = 3002002
SNIFFS = 3002003

sm.lockInGameUI(True)
sm.spawnNpc(MOONBEAM, 200, -145)
sm.showNpcSpecialActionByTemplateId(MOONBEAM, "summon")

sm.spawnNpc(TWITCH, 40, -145)
sm.showNpcSpecialActionByTemplateId(TWITCH, "summon")

sm.spawnNpc(LUMPS, -30, -145)
sm.showNpcSpecialActionByTemplateId(LUMPS, "summon")

sm.spawnNpc(SALLY, -100, -145)
sm.showNpcSpecialActionByTemplateId(SALLY, "summon")

sm.spawnNpc(SNIFFS, 260, -145)
sm.showNpcSpecialActionByTemplateId(SNIFFS, "summon")

if "2" in sm.getQRValue(38900):
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("What are you doing?")

    sm.setSpeakerID(TWITCH)
    sm.sendSay("Whoa! No tail! For reals!")

    sm.setSpeakerID(SALLY)
    sm.sendSay("You're so mean. Mama said not to make fun of unlucky people.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("Huh... What?")

    sm.setSpeakerID(SNIFFS)
    sm.sendSay("Moonbeam, we'll get going first. You bring it to town.")
    sm.sendDelay(1000)

    sm.removeNpc(TWITCH)
    sm.removeNpc(LUMPS)
    sm.removeNpc(SALLY)
    sm.removeNpc(SNIFFS)
    sm.sendDelay(1000)

    sm.setSpeakerID(MOONBEAM)
    sm.sendNext("'Kay. Let's go, human.")
    sm.sendDelay(1000)

    sm.removeNpc(MOONBEAM)
    sm.warp(940200060, 0)
elif "1" in sm.getQRValue(38900):
    sm.forcedInput(4)
    sm.sendDelay(2000)
    sm.showBalloonMsg("Effect/Direction5.img/effect/mercedesInIce/merBalloon/0", 0)
    sm.sendDelay(3000)

    sm.forcedInput(3)
    sm.sendDelay(1000)

    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("W-where is this...?")

    sm.setSpeakerID(SNIFFS)
    sm.sendSay("Ooh! It woke up, it's up! ")

    sm.setSpeakerID(SALLY)
    sm.sendSay("It even spoke! It knows how to speak!")

    sm.setSpeakerID(TWITCH)
    sm.sendSay("Did you see that? Its eyes are round!")

    sm.setSpeakerID(LUMPS)
    sm.sendSay("It's so huge! Maybe we'll get eaten!")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("Ugh...")

    sm.setSpeakerID(TWITCH)
    sm.sendSay("Hey, I think it's sick. Ask it if it's hurt.")

    sm.setSpeakerID(LUMPS)
    sm.sendSay("Why do you keep budging me? I'm scared.")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("You scaredy-cats! Move over. Hey, you! You okay?")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("Yeah, I'm feeling okay. It's howling in my head. But where is this? Who are you...?")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("We're the Pointy-Ear Foxes. We live here. Who are you? How'd you get here? This is our territory.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("(Pointy-Ear Foxes? An unknown race? They do resemble foxes, with those ears and tails.)")
    sm.sendSay("I'm a human. I just came from near the Temple of Time, where is this near?")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("Human? Temple of Time? Never heard of those before.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("(Not know the Temple of Time? How far did I come? Where is this...)")

    sm.showEffect("Effect/Direction2.img/effect/chat/nugu/0", 2000, 20, -80, 0, 0, False, 0)
    sm.sendDelay(2500)

    sm.sendNext("T-two moons?!")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("...There's always two moons. Human, you feelin' okay?")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("Always two moons? That's not possible! And my level is... #r#e10#k#n?! What happened...??")

    sm.setSpeakerID(SNIFFS)
    sm.sendSay("I knew it! Something was off from its earless looks. It must be...stupid!")

    sm.setSpeakerID(SALLY)
    sm.sendSay("Maybe that's why it got abandoned. Oh, poor thing...")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("We have no choice, let's take it back to the town.")

    sm.setSpeakerID(LUMPS)
    sm.sendSay("But the adults will be mad if we bring in an outsider.")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("But we can't just leave it here. I'll take full responsibility. Let's go!")

    sm.setQRValue(38900, "2")
    sm.removeNpc(MOONBEAM)
    sm.removeNpc(TWITCH)
    sm.removeNpc(LUMPS)
    sm.removeNpc(SALLY)
    sm.removeNpc(SNIFFS)
    sm.warp(940200000, 0)