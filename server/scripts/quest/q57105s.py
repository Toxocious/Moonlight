# 57105 | powerless
sm.setSpeakerID(9130008)
sm.sendNext("Who are you?")
sm.setPlayerAsSpeaker()
sm.sendNext("Mouri Motonari! We met at the Honnou-Ji military council. I am Anegasaki Kenji, a retainer to the Matsuyama clan. I've just arrived.")
sm.setSpeakerID(9130008)
sm.sendNext("The Mouri welcome you to Momijigaoka, Matsuyama clan. My sons and I were the first to arrive, so we set up a base of operations. We must all work together, under my guidance, to adjust to this new world.")
sm.setPlayerAsSpeaker()
sm.sendNext("what do you mean 'adjust'?")
if sm.sendAskYesNo("Attempt to move as you normally would, Anegasaki Kenji."):
    sm.startQuest(parentID)
    sm.dispose()
else:
    sm.dispose()
