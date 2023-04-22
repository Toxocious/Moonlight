# [Shaolin Temple] Making a Mask

DABAO = 9310034
HUMAN_FACE_MASK = 4034664
GOBLIN_DEMON = 9310578
SECRET_LIBRARY = 701220350
SOFT_LEATHER = 4034724

sm.removeEscapeButton()
sm.setSpeakerID(DABAO)
sm.setBoxChat()
sm.sendNext("You got the leather! Now it's my turn.")

sm.sendNext("All done!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(The face on the mask looks very realistic, though you have the niggling feeling it may have been extracted from a real person. "
            "No matter. Bring it to #p"+ str(GOBLIN_DEMON) +"# at the #m"+ str(SECRET_LIBRARY) +"#.)")

sm.completeQuest(parentID)
sm.consumeItem(SOFT_LEATHER, 30)
sm.giveExp(1515339)
sm.giveItem(HUMAN_FACE_MASK)
