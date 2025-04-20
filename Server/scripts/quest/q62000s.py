# [Shaoling Temple] Elder Jung's Summons

ELDER_JUNG = 9310049
MOUNT_SONG_HAMLET = 701210000

sm.removeEscapeButton()
sm.setBoxChat()
sm.flipBoxChat()
sm.flipBoxChatPlayerNoEscape()
sm.sendNext("#fs 24#(You've received a letter from #b#p"+ str(ELDER_JUNG) +"##k from Mount Song Hamlet.)")

sm.setSpeakerID(ELDER_JUNG)
sm.setBoxChat()
sm.sendNext("I hope this finds you well, #h0#. "
            "I am the elder of #b#m"+ str(MOUNT_SONG_HAMLET) +"##k. We have have of your great deeds for other realms. We are in dire need of your assistance.")

response = sm.sendAskYesNo("The details are sensitive. I would like to discuss them in person. Will you join me at #b#m"+ str(MOUNT_SONG_HAMLET) +"##k?")

if response:
    sm.sendNext("Thank you. I await you at #b#m"+ str(MOUNT_SONG_HAMLET) +"##k.")
    sm.warp(MOUNT_SONG_HAMLET)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.giveExp(505113)
else:
    sm.sendSayOkay("Let me remind you... You're talking to a letter.")