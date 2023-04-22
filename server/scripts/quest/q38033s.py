CHIEF = 1012003

sm.lockInGameUI(True, False)
sm.forcedInput(0)
sm.removeEscapeButton()
sm.setSpeakerID(CHIEF)
sm.sendNext("Now let's open this map, and I'll elaborate on what I was talking about earlier.")

sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/0")
sm.sendDelay(1000)

sm.sendNext("This, is Maple World. You see this island right here? That's Victoria Island. Henesys is right here, on the south side of Victoria Island.")

sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/1")
sm.sendDelay(1500)

sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/1", False)
sm.sendNext("Leafre? It's in Ossyria continent, over the sea. El Nath is right here, and Nihal Desert is right there.")

sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/2")
sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/3")
sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/4")
sm.sendDelay(1500)

sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/2", False)
sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/3", False)
sm.reservedEffectRepeat("Effect/Direction15.img/effect/story/worldmap/4", False)
if sm.sendAskAccept("So, you must come from Ossyria. There's someone, an elf, who came from Ossyria, too. Her name is #rAthena Pierce#k... Do you want to meet her?"):
    sm.startQuest(parentID)
    sm.setPlayerAsSpeaker()
    sm.sendNext("(#rAthena Pierce#k? Is he talking about the little kid that used to follow me around? I guess she was headed to Victoria Island on the rescue ship. She must have settled down there. Maybe she'll know something about Mercedes.)")

    sm.setSpeakerID(CHIEF)
    sm.sendSay("Athena Pierce is at the Bowman Instructional School. She's busy teaching new bowmen, so don't take up too much of her time.")
    sm.lockInGameUI(False)