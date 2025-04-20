# Created by MechAviv
# Quest ID :: 25584
# Secret Ritual

sm.setSpeakerID(1032001)
sm.sendNext("And that concludes our deal. Goodbye, farewell, and don't let the door hit your butt on the way out!")


sm.setSpeakerID(1032001)
sm.setPlayerAsSpeaker()
sm.sendSay("Not so fast. There's one more thing I need.")


sm.setSpeakerID(1032001)
sm.sendSay("What is it now? I've helped you enough!")


sm.setSpeakerID(1032001)
sm.setPlayerAsSpeaker()
sm.sendSay("Calm yourself, old man. All I need is a secluded place to do my ritual. Preferably someplace with an ample supply of magical energy.")


sm.setSpeakerID(1032001)
sm.sendSay("Hm. I know just the place. The Black Wings used to hold their meetings there; seems like a good fit for someone like you. Head to the #beast end of the Golem Temple Entrance#k and take the portal. #bDouble-click on the Vampiric Lantern#k to teleport nearby.")


sm.setSpeakerID(1032001)
sm.setPlayerAsSpeaker()
sm.sendSay("By my virtue, I will return Lolo to you now. I.. believe I have enough control over the power of darkness to do so.")


sm.setSpeakerID(1032001)
sm.sendSay("I am glad you didn't forget. Perhaps the darkness has not claimed you entirely just yet.")


sm.startQuest(25584)
sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("G-G-Grendel! It was dark and cold and I was s-s-s-scared!!")


sm.setSpeakerID(1032001)
sm.removeEscapeButton()
sm.sendSay("Lolo! You're safe!")


sm.setSpeakerID(1032001)
sm.removeEscapeButton()
sm.sendPrev("Heed my words. The power of darkness is much more dangerous than you think. Learn to control it before the worst happens.")
