# End of Knight's Cavalier
KIMU = 1102004
FEATHERED_NOBLESSE_HAT = 1003769

sm.setSpeakerID(KIMU)

if not sm.isEquipped(FEATHERED_NOBLESSE_HAT):
	sm.sendSayOkay("It doesn't look like you put your hat on yet... "
	"Press the #bHotkey I#k to open the inventory window, #rthen double-click on the equipment#k.\r\n"
	"You can see what you have equipped by pressing the #bHotkey E#k.")
	sm.dispose()

sm.sendNext("Isn't putting on equipment easy? "
"That's a good thing because you'll need WAY better gear if you want to be a real knight! "
"Always keep an eye out for new stuff.")

sm.sendSay("I found Kinu in a pile of books. "
"He might tell you what you need to know, or he might just put you to sleep. Or both.")

sm.completeQuest(parentID)