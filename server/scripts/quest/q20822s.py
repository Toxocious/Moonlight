# Start of The Path of Bravery
KIKU = 1102000
sm.setSpeakerID(KIKU)

response = sm.sendAskYesNo("I don't know if they told you during the orientation, but we're here to fight the Black Mage. "
"Right now, you're not fit to fight a black mop. I'm gonna fix that.\r\n"
"You ready for some action?")

if not response:
	sm.sendSayOkay("You listening? Pay attention!")
	sm.dispose()

sm.removeEscapeButton()

sm.sendSay("Your first stop is Kimu. If you get lost, hit the #bQ key#k and check your #rQuest Info#k. "
"That's tip number one: always check your quest info before you go running your mouth!")

sm.startQuestNoCheck(parentID)
