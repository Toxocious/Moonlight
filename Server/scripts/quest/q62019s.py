# [Shaolin Temple] Making a Mask

DABAO = 9310034

sm.removeEscapeButton()
sm.setSpeakerID(DABAO)
sm.setBoxChat()
sm.sendNext("How can I help you today?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Do you... make masks?")

sm.setSpeakerID(DABAO)
sm.setBoxChat()
sm.sendNext("Oh, sure. Sure. I do it all. What kind of mask do you want?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Um, a man face?")

sm.setSpeakerID(DABAO)
sm.setBoxChat()
sm.sendNext("Come again?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Like, a mask of a man's face. It's for, um, science..")

sm.setSpeakerID(DABAO)
sm.setBoxChat()
sm.sendNext("I could never say no to science.")

sm.sendNext("All I need is some soft leather to make the mask. "
            "Problem is, the leather was being stored iat the Sutra Depository Warehouse, and silver demons stole it all.")

sm.sendNext("If you can bring me 30 pieces of soft leather, I'll make your mask.")
sm.startQuestNoCheck(parentID)