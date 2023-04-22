# [Shaolin Temple] Finding the Secret Library

CHENGXIN = 9310047
WISE_CHIEF_PRIEST = 9310053
SUTRA_5_6F = 701220300

sm.removeEscapeButton()
sm.setSpeakerID(CHENGXIN)
sm.setBoxChat()
sm.sendNext("Now that the demons have quieted down, tell me who you are. What are you doing here?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(You fill him in on the details.)")

sm.setSpeakerID(CHENGXIN)
sm.setBoxChat()
sm.sendNext("Ah, #bMonk Laomo#k... Now there was a truly great leader. A selfless soul, not an ounce of greed in him... A far cry from our current #b#p"+ str(WISE_CHIEF_PRIEST) +"##k...")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(Come to think of it, the #b#p"+ str(WISE_CHIEF_PRIEST) +"##k was dressed rather ostentatiously, for a monk. "
            "The main temple is rather flamboyant, too. And who names themselves '#b#p"+ str(WISE_CHIEF_PRIEST) +"##k'?")

sm.setSpeakerID(CHENGXIN)
sm.setBoxChat()
sm.sendNext("You and I have the same aim, friend. Stopping those demons! I'll help you find that book!")

sm.sendNext("You can't enter #b#m"+ str(SUTRA_5_6F) +"##k looking the way you do. The demonic power is too great. Try drinking this Metamorph Potion.")

sm.sendNext("Temporarily transform into a demon and investigate #m"+ str(SUTRA_5_6F) +"#. The potion isn't that strong, so avoid the demons' direct gaze!")

response = sm.sendAskYesNo("Ready to go?")

if response:
    sm.sendNext("Good luck, friend.")
    sm.startQuest(parentID)
    sm.warpInstanceIn(701220310)
else:
    sm.sendSayOkay("Let me know when you are ready to go.")