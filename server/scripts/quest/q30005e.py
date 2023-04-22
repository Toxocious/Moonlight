# [Root Abyss] The World Girl

MYSTERIOUS_GIRL = 1064001 # npc Id
sm.removeEscapeButton()
sm.lockInGameUI(True)
sm.setPlayerAsSpeaker()
sm.sendNext("If you're really the World Tree, can't you just like... magic yourself outta here?")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("No! Those bad people did this to me!")

sm.setPlayerAsSpeaker()
sm.sendNext("Oh, here we go...")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("Before I laid down to rest, I set up a barrier to keep me safe here, but some creeps broke in. "
            "One of them even tried to kidnap me!")

sm.setPlayerAsSpeaker()
sm.sendNext("Were they the Black Mage's minions?")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("I don't know, they were all wearing hoods. One of them was this nasty little demon-faced guy with an eye patch. "
            "I think he was their boss.")

sm.showFieldBackgroundEffect("Effect/Direction11.img/effect/meet/frame0/0")
sm.showFieldEffect("Map/Effect.img/rootabyss/demian")
sm.invokeAfterDelay(1000, "showFadeTransition", 1500, 0, 1000)

sm.setPlayerAsSpeaker()
sm.invokeAfterDelay(4500, "sendNext", "A demon with an eyepatch tried to kidnap you? Do you realise how crazy that sounds?")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("It's true! He was dragging me out of here until he found out I wasn't fully recovered. Then he sealed me up in here.")

sm.setPlayerAsSpeaker()
sm.sendNext("Is that why you couldn't get through the gateway?")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("I think so. I'm pretty sure he was the one who corrupted Root Abyss too. "
            "I just can't use my powers with all of this dark energy around.")

sm.sendNext("I'm worried that the darkness will swallow me whole at this rate. Will you help me?")
sm.lockInGameUI(False)
sm.completeQuest(parentID)