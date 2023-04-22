# 410000002
MOONBEAM = 3002100

sm.setSpeakerID(MOONBEAM)
sm.sendNext("Let's start with #b[Fox Trot]#k. If you really do have ears, perk them up for this!")
sm.sendSay("#b[Fox Trot]#k is super helpful when you want to move quickly or get close to an enemy. You're way too slow to catch prey, so you need all the help you can get.")
sm.sendSay("Now try using #b[Fox Trot]#k. Oh, and it's a lot easier if you hotkey it and use it that way!")
sm.startQuest(parentID)

sm.sendNext("Whaddaya think? Was that amazingly fast, or what? Use this skill to get close to your prey.")
sm.sendSay("Use this skill when you're using other skills to #binterrupt#k the first one and #bmove#k quickly!")
sm.sendSay("I know you can be a little slow, Shade. Are you keeping up?")
sm.sendSay("I'll tell you how to use it to make up for your weakness. Talk to me when you're ready!")
sm.completeQuest(parentID)
sm.giveExp(700)


