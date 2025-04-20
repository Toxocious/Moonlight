# Secret Instructions ; Xenon 2nd Job

TONERO = 3001004
CLAUDINE = 1540452

sm.setSpeakerID(TONERO)
sm.sendNext("Hello to you #b#h ##k! The name's Tonero, commisioner of instructions most discreet. I have a little something for you!.")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("What is this?")
sm.setSpeakerID(TONERO)
sm.sendSay("I was instructed, and persuaded with monetary incentive, to bring this #bResistance Orders#k to you, no questions asked. Now then, I'm off.")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("What the... He just disappeared!\r\nWho sent me secret instructions?")
sm.setSpeakerID(CLAUDINE)
sm.sendSay("Dear #b#h ##k, I hope you're doing well. It hasn't been easy to track you down, but I think that's probably good, all things considered. I apologize for the odd man we had to choose as a messenger, but I assure you he is trustworthy enough.")
sm.sendSay("I'm sending you this message because the Resistance has formed a secret research organization to investigate strange occurrences in Maple World. With the help of the Alliance, our #bnew research agency#k is up and running, but slightly understaffed. I would like for you to go and help.")
sm.sendSay("I'm sure they would be more than happy to help you with your problems as well. I believe it may be exactly the sort of support you need right now. I'm sorry that I can't be there to give it to you myself, but I am needed here. May fortune smile on you.")
sm.flipDialoguePlayerAsSpeaker()
if sm.sendAskAccept("Now that Gelimer is off my tail, these people may be just what I need. It couldn't hurt to visit, at least.\r\n\r\n#r(Press Y to move automatically.)"):
    sm.warp(230050000)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
