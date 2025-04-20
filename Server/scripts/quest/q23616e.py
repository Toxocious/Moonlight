# Identity Crisis ; Xenon 4th Job

ROO_D = 2300000

sm.setSpeakerID(ROO_D)
sm.sendNext("Why the serious face? Wait, you always look serious. Why the more-serious face?")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Roo-D, what if I never get my memories back? What if I threw away my whole life for something I'll never see?")
sm.setSpeakerID(ROO_D)
sm.sendSay("What? That's crazy! If you had stayed in that lab, Gelimer would have you... stomping on puppies or something! What if he'd ordered you to destroy Edelstein?! That could have been you!")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I know that, I don't regret leaving. But I feel an emptiness inside that I do not know how to deal with. You and Beryl do not seem to care about your past at all. Why does it trouble me so much? Am I... broken?")
sm.setSpeakerID(ROO_D)
sm.sendSay("No! You're great, just the way you are. There's nothing wrong with wanting to know who you are, #h #. But you and I are different, you know? Not everybody wants the same thing.")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay(".....")
sm.setSpeakerID(ROO_D)
sm.sendSay("You taught me that Xenoroids are different. You and Beryl couldn't be less alike. That means we were meant to have different personalities. If anything, you wanting to find you memories makes you MORE human than the rest of us.")
sm.sendSay("I know you're struggling. This isn't gonna be easy for you. It could take years. But if it's something you want, I know you'll make the decision to pursue it, no matter how much pain it comes with. And that decision will always be yours to make. You're free now.")
sm.sendSay("Everybody can see that you're doing the right thing. That's why we all help you. It's not because they feel sorry for you, it's because they believe it's the right thing to do. Don't ever forget the friends you've found. You might have been enemies if you hadn't left the lab.")

if not sm.canHold(1142578):
    sm.sendSayOkay("Please make some space in your equipment inventory.")
    sm.dispose()
sm.completeQuest(parentID)
sm.jobAdvance(3612)
sm.giveAndEquip(1353004)
sm.giveItem(1142578)

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Thank you for standing beside me, Roo-D. I hope that the choices I make will help you as much as they help me.")
sm.setSpeakerID(ROO_D)
sm.sendPrev("Don't mention it! I owe you my life. We're gonna be besties forever!")
