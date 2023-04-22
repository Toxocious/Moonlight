# [Shaolin Temple] But Everyone Else Has Nice Shoes

BLUE_DEMON = 9310577
KID_SHOES = 4034657
PATH_OF_STONE_3 = 701210180

sm.removeEscapeButton()
sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("We all know that I'm the epitome of cute, but one day, I realised that colourful shoes would make me even cuter!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("So you stole some poor kid's shoes?")

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("I borrowed them. Kids don't need shoes while they're sleeping, and I was planning to return them in the morning")

sm.sendNext("But as I was dancing around, admiring myself, I tripped and the shoes went soaring.")

sm.sendNext("Do you think you can #rfind the #t"+ str(KID_SHOES) +"##k and return them?")

response = sm.sendAskYesNo("Will you help me?")

if response:
    sm.sendNext("I'm sure you'll find the #b#t"+ str(KID_SHOES) +"##k at #r#m"+ str(PATH_OF_STONE_3) +"##k.")
    sm.startQuest(parentID)

else:
    sm.sendSayOkay("I guess you don't want that book you were crying for..")