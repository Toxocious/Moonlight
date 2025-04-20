# [Shaolin Temple] Say My Name 2

NINE_TAILED_FOX = 9310579
selectName = ["Quashara", "Quivia", "Shaniqua"]

sm.removeEscapeButton()
sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("You did surprisingly well, human.\r\n"
            "I guess I'll let you live.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("That's right, #b#p"+ str(NINE_TAILED_FOX) +"##k. You and me are buds now, so I'll give you a #rNickname#k.")

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("What's a #rNickname#k?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("It's a name that I can use to address you. Just like my name is #r#h0##k.")

sm.sendNext("I want YOU to have a name. That way I don't have to use your demon name, which is really just the name of your species.")

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("If it's a human thing, I'm in!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
text = "Hmm, what about I call you...\r\n"
for i in range(len(selectName)):
    text += "#L"+ str(i) +"#"+ selectName[i] +"\r\n"
selection = sm.sendNext(text)

sm.createQuestWithQRValue(62015, selectName[selection])

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext(sm.getQRValue(62015) +", hm? I kinda like it.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("I'll call you #r"+ sm.getQRValue(62015) +"#k from now on, then.")

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext( "Very well. #r"+ sm.getQRValue(62015) +"#k has a sophisticated ring to it")

sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(1010226)