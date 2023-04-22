# [Root Abyss] Guardians of the World Tree

MYSTERIOUS_GIRL = 1064001 # npc Id
sm.removeEscapeButton()
sm.lockInGameUI(True)
sm.setPlayerAsSpeaker()
sm.sendNext("We need to find those baddies if we want to get you out of here.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("But... they all left")

sm.setPlayerAsSpeaker()
sm.sendNext("They had to have left some clues behind. "
            "What about those weird doors over there?")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("They showed up when the bad guys left, but I can't get through them.")

sm.setPlayerAsSpeaker()
sm.sendNext("Then that sounds like a good place to start. Maybe I should-")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("Y-you're glowing!")
sm.invokeAtFixedRate(0, 2450, 3, "showEffect", "Effect/Direction11.img/effect/Aura/0", 3, 0)

sm.setPlayerAsSpeaker()
sm.sendNext("Ah! What is this?! Don't let it take all my fr00dz!!")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("#h0#!!!")

sm.startQuest(parentID)
sm.lockInGameUI(False)
sm.warpInstanceIn(910700300, 0) # Fake Vellum Cave for QuestLine
