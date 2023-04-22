# Tears of Princess Sakuno (Sengoku Questline) | Momiji Hills : Master Room (811000008)
# Author: Tiger

SAKUNO = 9130124
sm.setSpeakerID(SAKUNO)

sm.flipDialogue()
sm.sendNext("Please accept my gratitude for the medicine. Thanks to you I am able to see Ayame once again.")

sm.flipDialogue()
sm.sendSay("You must have been suprised to see me like that. The truth is I have been indebted to Ayame.")

response = sm.sendAskYesNo("Would you like to hear the whole story?")

if response:
    sm.startQuest(58909) # Tears of Princess Sakuno
    sm.warpInstanceIn(811000009) # Master Room
