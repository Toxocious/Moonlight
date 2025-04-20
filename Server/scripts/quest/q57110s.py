# 57110 | Counterintelligence
sm.setSpeakerID(9130008)
sm.sendNext("A report has just come in about soldiers in dark armor circling the outskirts of Momijigaoka.")
sm.setPlayerAsSpeaker()
sm.sendNext("Soldiers in dark armor... Oda! They must have been brought to this world as well!")
sm.setSpeakerID(9130008)
sm.sendNext("There is no doubt they are Oda's soldiers. However, I am sure they have not discovered the base. Their movements are too erratic.")
if sm.sendAskYesNo("It is only a matter of time before they stumble onto our nest, most likely due to blind luck. We would be best served if someone were to go fight them. Don't you think, Hayato?"):
    sm.startQuest(parentID)
    sm.sendNext("Well then, I suppose you should get to work. The reports mention Maple Hill Road. I would expect resistance, and I would not be surprised if you find more difficult than you expected.")
    sm.dispose()
else:
    sm.dispose()