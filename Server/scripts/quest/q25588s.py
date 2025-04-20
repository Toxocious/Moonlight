# Created by MechAviv
# Quest ID :: 25588
# Seeking Serenity

sm.setSpeakerID(1032209)
sm.sendNext("You aren't out of the woods yet. You never know when the Dark might claim you again.")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("You're right. I cannot let my guard down.")


sm.setSpeakerID(1032209)
sm.sendSay("Why don't you try making the darkness yours?")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("Is such a thing even possible?")


sm.setSpeakerID(1032209)
sm.sendSay("I already told you, Light and Dark are practically the same thing! Don't worry, I'll help. Maybe we can even grant our teacher's dying wish.")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("The master had a dying wish?")


sm.setSpeakerID(1032209)
sm.sendSay("Yes. How'd it go again? Ahem! #rWhoever wants to conquer the Dark must collect the Auguries and combine their strength.#k And I happen to know where the Auguries are.")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("I suppose we should gather them.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.sendSay("There are 5 Auguries all together. We'll start with #bthe first two#k for now, and worry about the others later.")

sm.startQuest(25588)
sm.completeQuest(25588)
sm.giveExp(1529)
