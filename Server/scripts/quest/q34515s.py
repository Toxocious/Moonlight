# [Maplerunner] It's Maplerunner!
# Author: shavit

TWO_PUNCH_CAT = 9070200
FOREST_OF_TENACITY_LOBBY = 993001000

# Settings
sm.setSpeakerID(TWO_PUNCH_CAT)
sm.flipDialogue()
sm.setBoxChat()

sm.sendNext("Hey, you! I found you!")

sm.sendNext("I've been searching high and low for you! "
	"Word is already starting to trickle out about you. "
	"You know, the part about you really being a goat in a human's body.")

sm.sendNext("But I know you don't want that to spread like wildfire, so I'll keep it a secret!")

sm.sendNext("Anyway, there are a whole lot of animals in the Forest of Tenacity that wish to be half as amazing as you are. "
	"They're talented, sure, but...")

sm.sendNext("They NEED proper guidance!")

if not sm.sendAskYesNo("I figured if anyone can help them, you can. "
	"So, how 'bout it? Interested in helping out? "
	"If you are, let me know, and I'll guide you to the Forest of Tenacity."):
	sm.sendSay("Well, that's unfortunate. "
		"Please talk to me when you can help the animals in the Forest of Tenacity.")
	sm.dispose()

sm.sendNext("That's what I like to hear! "
	"Then I'll guide you the Forest of Tenacity. "
	"We'll discuss the details after that.")

sm.warp(FOREST_OF_TENACITY_LOBBY)
sm.completeQuestNoRewards(parentID)