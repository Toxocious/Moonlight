# Start of Well-Behaved Student
KINU = 1102006

sm.setSpeakerID(KINU)

response = sm.sendAskYesNo("I am Kinu. I will get you up to speed on Ereve's history. "
"Now, go on and sit! I teach best when my students are shorter than me!")

if not response:
	sm.sendNext("Why are you still standing? That is not what a well-behaved student would do.")
	sm.dispose()

sm.removeEscapeButton()

sm.sendSay("Press X in front of any chair to sit down. "
"If you own one, it's the same deal. X marks the butt.")

sm.showEffect("Effect/OnUserEff.img/guideEffect/cygnusTutorial/10", 0, 5000)

sm.startQuest(parentID)