# [Shaolin Temple] Chief Priest's Woes

WISE_CHIEF_PRIEST = 9310053
sm.removeEscapeButton()
sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("This is troubling... The demons have extended their reach to Mount Song Hamlet...")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Yeah, you know I thought you guys were supposed to be professional demon slayers or something, "
            "but things look ever worse here than back in town.")

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("It is a sad tale... Long ago, the demons were sealed within the Sutra Depository. Now and then, a few would break free and play pranks on the towns people...")

sm.sendNext("But we of the Shaolin Temple would suppress them, using the power of zen and prayer beads.")

sm.sendNext("But lately, the demon presence has been overwhelming. Could there be a problem with the Sutra Depository?")

sm.sendNext("Why don't you go investigate the Mahavira Hall?")
sm.startQuest(parentID)