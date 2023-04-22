# Maplerunner - Entry | Forest of Tenacity
# Author: shavit

from net.swordie.ms.enums import UIType

REQUIRED_QUEST = 34515
REQUIRED_LEVEL = 33

TWO_PUNCH_CAT = 9070200

# Settings
sm.setSpeakerID(TWO_PUNCH_CAT)
sm.flipDialogue()
sm.setBoxChat()

def first_dialog():
	sm.sendNext("Oh, it's you! "
		"You're that goat with superhuman powers, aren't you? "
		"It's an honor!")

	sm.sendNext("What are you doing hiding inside a human's body? "
		"Ah, I get it. "
		"You don't want the other animals to know who you really are!")

	sm.sendNext("Don't worry, your secret's safe with me. "
		"But honestly, you'd be a great role model for some of the slackers around here.")

	sm.sendNext("Since you're not in your true goat form, I imagine you're a bit limited right now. "
		"When you're out of that human body, I'd personally like to see #gwhat you're all about#k.")

	sm.sendNext("I think I get why you're pretending to be a human. "
		"Discipline. I like that. Mad respect. "
		"Anyway, good luck.")

	sm.sendNext("Oh, don't forget that you can use the #rDimensional Mirror #kwhen you're coming back!")

	sm.setQRValue(REQUIRED_QUEST, "1")

if sm.getChr().getLevel() < REQUIRED_LEVEL:
	sm.sendSayOkay("I'm sorry buddy, but this content is available starting from level %d and above." % REQUIRED_LEVEL)
	sm.dispose()

if not sm.hasQuestCompleted(REQUIRED_QUEST):
	sm.sendSayOkay("You will have to finish the pre-entry quest attempting Maplerunner.")
	sm.dispose()

if sm.getQRValue(REQUIRED_QUEST) != "1":
	first_dialog()

sm.openUI(UIType.UI_PLATFORM_STAGE_SELECT)