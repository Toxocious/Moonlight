# [Shaolin Temple] But Everyone Else Has Nice Shoes

TOWN_MISCREANT = 9310580
KID_SHOES = 4034657

sm.removeEscapeButton()
sm.setSpeakerID(TOWN_MISCREANT)
sm.setBoxChat()
sm.sendNext("Hey! My shoes! Thanks for giving them back, demon!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("I'm not a demon!")

sm.setSpeakerID(TOWN_MISCREANT)
sm.setBoxChat()
sm.sendNext("But only demons steal shoes from little kids who don't eat their orange mushrooms!")

sm.sendNext("Ma told me so!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("It's not nice to stereotype against demons. The demon who BORROWED your shoes asked me to return them. He just wanted to try them on")

sm.setSpeakerID(TOWN_MISCREANT)
sm.setBoxChat()
sm.sendNext("Really? Did he dance in them? Because I loooove dancing in these shoes. Maybe he and I can be friends!")

sm.consumeItem(KID_SHOES)
sm.giveExp(2020453)
sm.completeQuestNoRewards(parentID) # completeQuest didn't work for w/e reason.
sm.warp(701220350)
