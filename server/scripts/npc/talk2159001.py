# Introduction quest portal for resistance
JUN_ID = 2159000
ULRIKA_ID = 2159001
VON_ID = 2159002

sm.setSpeakerID(ULRIKA_ID)
sm.sendNext("There you are, #h #! You're late. Get over here.")

sm.setSpeakerID(VON_ID)
sm.flipDialogue()
sm.sendSay("What was the hold up? You scared or something?")

sm.setPlayerAsSpeaker()
sm.sendSay("Don't be ridiculous.")

sm.setSpeakerID(JUN_ID)
sm.flipDialogue()
sm.sendSay("You're not s-s-scared at all? I am, a little b-b-bit... The grown-ups warned us never to venture into the #bVerne Mines#k... Plus, there are all those #rBlack Wings#k around, watching us, I just know it.")

sm.setSpeakerID(VON_ID)
sm.flipDialogue()
sm.sendSay("We snuck here, Jun. No one saw us. No one's watching us, okay? Come on, when else would we have ever gotten the chance to leave #bEdelstein#k? Don't be a chicken.")

sm.setSpeakerID(JUN_ID)
sm.flipDialogue()
sm.sendSay("But what if we get in trouble?")

sm.setSpeakerID(ULRIKA_ID)
sm.sendSay("Jun, we're already here. If we're going to get in trouble, let's at least have some fun first. Let's play hide-and-seek!")

sm.setPlayerAsSpeaker()
sm.sendSay("Hide and seek?")

sm.setSpeakerID(VON_ID)
sm.flipDialogue()
sm.sendSay("Ugh, la-ame.")

sm.setSpeakerID(ULRIKA_ID)
sm.sendSay("Don't be a brat, Von. What? Are you scared to hide all by yourself in these big, bad caves? *snicker*\r\n#h0#, since you were late, you're it. Count to 10 and then come find us. No peeking.")

sm.warp(931000001, 1)