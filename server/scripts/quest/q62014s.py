# [Shaolin Temple] Say My Name 1

BLUE_DEMON = 9310577
selectName = ["Tyrone", "DeShawn", "Lamar"]


sm.removeEscapeButton()
sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("You returned the shoes! And the kid wants to be friends? I've never felt cuter in my life!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("That's right, #b#p"+ str(BLUE_DEMON) +"##k. You and me are buds now, so I'll give you a #rNickname#k.")

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("What's a #rNickname#k?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("It's a name that I can use to address you. Just like my name is #r#h0##k.")

sm.sendNext("I want YOU to have a name. That way I don't have to use your demon name, which is really just the name of your species.")

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext("If it's a human thing, I'm in!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
text = "Hmm, what about I call you...\r\n"
for i in range(len(selectName)):
    text += "#L"+ str(i) +"#"+ selectName[i] +"\r\n"
selection = sm.sendNext(text)

sm.createQuestWithQRValue(62013, selectName[selection])

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext( sm.getQRValue(62013) +", hm? I.. I like it!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("I'll call you #r"+ sm.getQRValue(62013) +" from now on, then.")

sm.setSpeakerID(BLUE_DEMON)
sm.setBoxChat()
sm.sendNext( "#r"+ sm.getQRValue(62013) +"#k has a nickname, #r"+ sm.getQRValue(62013) +"#k has a nickname!")

sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(1010226)