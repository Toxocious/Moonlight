# [Shaolin Temple] Thef Chief Priest's Disappearance...

ZHEUNG_GUAN = 9310046
WISE_CHIEF_PRIEST = 9310053
ELDER_JUNG = 9310049

sm.removeEscapeButton()
sm.setSpeakerID(ZHEUNG_GUAN)
sm.setBoxChat()
sm.sendNext("I can't believe it! The #p"+ str(WISE_CHIEF_PRIEST) +", who I trusted and followed, was the one responsible for all the demons? I feel like such an idiot!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("No wonder he didn't help fine-tune your concoction")

sm.setSpeakerID(ZHEUNG_GUAN)
sm.setBoxChat()
sm.sendNext("We wouldn't have figured this out without your help. Thanks I'll handle the rest, with my new and improved Demon B Gone.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Yeah, good luck with that.")

sm.setSpeakerID(ZHEUNG_GUAN)
sm.setBoxChat()
sm.sendNext("By the way, #p"+ str(ELDER_JUNG) +"# is taking charge of the temple for now. He wants to talk to you.")
sm.completeQuest(parentID)
sm.completeQuestNoRewards(62037)
sm.giveExp(2020453)