# A Warm(er) Welcome ; Xenon level 200 quest

CLAUDINE = 2151003
BELLE = 1540431
BRIGHTON = 1540554
ELEX = 1540433
CHECKY = 1540432

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("What is it, Claudine?")
sm.setSpeakerID(CLAUDINE)
sm.sendSay("It's nothing urgent, but...")
sm.setSpeakerID(BELLE)
sm.sendSay("When you arrived in Edelstein, there was no time for pleasantries. Now that things have settled down a little, I thought we should welcome you like we would welcome any other member of our family.")
sm.setSpeakerID(BRIGHTON)
sm.sendSay("it's just a little welcome...")
sm.setSpeakerID(ELEX)
sm.sendSay("Don't get too excited, I just want to make sure you were introduced to everybody.")
sm.setSpeakerID(CHECKY)
sm.sendSay("I always feel so weird doing this kind of stuff...")
sm.setSpeakerID(BELLE)
sm.sendSay("Checky! What are you talking about? We're part of the same team!")
sm.setSpeakerID(CHECKY)
sm.sendSay("I'm not technically an original member of this group and...")
sm.setSpeakerID(BRIGHTON)
sm.sendSay("Hey. We're here to welcome #h #. So get over it")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Hahaha... Thank you all.")
sm.sendSay("(Wait, something feels strange.)")
sm.sendSay("(For some reason, I feel like #rthis has happened before#k...)")
sm.setSpeakerID(CLAUDINE)
sm.sendSay("Something wrong, #h #? You wandered off there.")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Oh, it's nothing.")
sm.setSpeakerID(CLAUDINE)
sm.sendSay("All right. Well, we got you a little gift. I mean, sort of. It'll teach you a new skill. I hope you'll use this as your new source of power when things get dark.\r\n#Wbasic#\r\n#s30021005# #q30021005#\r\n#i1142579:# #t1142579:#")
if not sm.canHold(1142579):
    sm.sendSayOkay("Please make some space in your equipment inventory.")
    sm.dispose()
sm.completeQuest(parentID)
sm.giveItem(1142579)
sm.giveSkill(30021005)
