# [Shaolin Temple] For Shaolin Temple

ZHEUNG_GUAN = 9310046
WISE_CHIEF_PRIEST = 9310053
ELDER_JUNG = 9310049
HAIFENG_FASHI = 9310051

sm.removeEscapeButton()
sm.setSpeakerID(ELDER_JUNG)
sm.setBoxChat()
sm.sendNext("The mountain weeps this day. My old friend has become a demon... What pain must he have been hiding to have come to this?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("It goes to show... A tiny grain of greed can snowball into something huge and monstrous")

sm.removeEscapeButton()
sm.setSpeakerID(ELDER_JUNG)
sm.setBoxChat()
sm.sendNext("My friend's body has disappeared, but his spirit remains. "
            "It must be exorcised, over and over, until the temple is clean. "
            "You can encounter <Boss: Chief Priest> by talking to #p"+ str(HAIFENG_FASHI) +"# at the Sutra Depository entrance. "
            "We will reward you as best we can.")

sm.sendNext("I will remain here, working humbly and tirelessly to restore Shaolin Temple to its former purity. You are welcome at any time.")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.completeQuestNoRewards(62044)
sm.giveExp(2525566)
sm.giveItem(1142912) # Shaolin Savior (Medal)