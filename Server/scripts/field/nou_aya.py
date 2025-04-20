# Cutsence for Sengoku Era Questline | Honnou-ji : Precinct of Honnou-ji (811000011)
# Author: Tiger

PRINCESS_NO = 9000435
AYAME = 9130100

sm.lockInGameUI(True)
sm.hideUser(True)
sm.removeEscapeButton()

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.setSpeakerID(PRINCESS_NO)

sm.flipDialogue()
sm.sendNext("Your next mission is in Echigo. You'll find the details in this letter.")

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.setSpeakerID(AYAME)

sm.flipDialogue()
sm.sendSay("...How many of these errands are you going to send me on? They're so... petty.")

sm.setSpeakerID(PRINCESS_NO)

sm.flipDialogue()
sm.sendSay("......")

sm.flipDialogue()
sm.sendSay("Patience, child. Prove yourself one last time.")

sm.flipDialogue()
sm.sendSay("And I will trust you completely.")

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.blind(1, 200, 0, 1000)
sm.sendDelay(1000)

sm.sayMonologue("She had the weirdest look on her face that day. One I've never seen before", False)
sm.sayMonologue("Then the Oda soldiers began moving in secret, and I knew something was up.", False)
sm.sayMonologue("That's why I went to Honnou-ji, to see what the Oda were up to.", False)
sm.sayMonologue("Oda Nobunaga was trying to sacrifice Princess Sakuno in Honnou-ji to summon the Demon King. I fought the Oda, but... it didn't get me very far.", True)

sm.blind(0, 0, 0, 1000)

sm.warp(811000012) # Honnou-ji : Precinct of Honnou-ji
