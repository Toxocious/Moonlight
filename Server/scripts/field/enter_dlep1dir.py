# this map share same script with 302090100 so I changed the map to 302010000
if sm.getFieldID() == 302090000:
    # [Grand Athenaeum] Stormy Forest : White Mage Prologue
    sm.lockInGameUI(True)
    sm.blind(1, 255, 0, 0)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/0", 100)
    sm.sayMonologue("I am a wandering mercenary.\r\n", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/1", 100)
    sm.sayMonologue("Looking back, it was a life that could end anywhere, at anytime.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/2", 100)
    sm.sayMonologue("The sun will set and the wind will blow, and someday I will be lost to history.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/3", 100)
    sm.sayMonologue("\r\n\r\nAnd perhaps...today will be that day.", True)

    sm.blind(1, 255, 0, 0)
    sm.sendDelay(1200)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1400)

    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("...")

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/4", 100)
    sm.sendSay("What happened to everyone? His followers trusted him...!")

    sm.moveCamera(300, 450, 185)

    sm.moveCameraBack(300)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/5", 100)
    sm.sendNext("Were his words all lies?")

    sm.blind(1, 200, 0, 1300)
    sm.sendDelay(1600)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/7", 100)
    sm.sayMonologue("Where shall I start...?", True)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/8", 100)
    sm.sayMonologue("...Yes. Let's begin when I first heard about the White Mage.", True)

    sm.blind(1, 255, 0, 500)
    sm.sendDelay(500)

    sm.sendDelay(1200)
    sm.sayMonologue("#fs40#Episode I: The White Mage", True)

    sm.lockInGameUI(False)
    sm.warp(302090100, 0)

elif sm.getFieldID() == 302090100:
    # [Grand Athenaeum] Ariant : Middle of the Desert
    HATSAR = 2510001
    sm.lockInGameUI(True)
    sm.moveCamera(0, -205, -500)

    sm.blind(1, 255, 0, 0)
    sm.sendDelay(1200)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1400)

    sm.blind(1, 200, 0, 1300)
    sm.sendDelay(1600)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/9", 100)
    sm.sayMonologue("I was always alone.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/10", 100)
    sm.sayMonologue("My weapons were the only thing I trusted. I had to put my life on the line just to eat.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/11", 100)
    sm.sayMonologue("\r\nI am lucky enough to be alive today, but...I can't say that about all my friends.", True)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/12", 100)
    sm.sayMonologue("As I learned to fight, I also learned not to fear the end.", True)

    sm.moveCameraBack(100)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/13", 100)
    sm.sayMonologue("War and famine, plagues and disasters... The world was surely falling apart.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/14", 100)
    sm.sayMonologue("And the rich lined their pockets with comfort as they watched it happen. The worst of them was Hatsar, the mogul of Ariant.", True)

    sm.blind(0, 0, 0, 1300)
    sm.sendDelay(1600)

    sm.removeEscapeButton()
    sm.setSpeakerID(HATSAR)
    sm.sendNext("*Huff huff* Hey, are you the servant that Hatsar sent? To escort me to Ariant?")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("...What? It's mercenary, not servant.")

    sm.setSpeakerID(HATSAR)
    sm.sendSay("Hey, nothing wrong with being a servant! Anyway, good. Do you have some water? It's rough having to carry all these myself.")

    sm.lockInGameUI(False)
    sm.startQuest(32629)
    sm.completeQuest(32629)
    sm.warp(302010000, 0)
