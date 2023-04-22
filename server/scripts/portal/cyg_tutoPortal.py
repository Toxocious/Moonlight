# 130030101 - Forest of Beginnings -> Physical Training Yard
KIMU = 1102004

if sm.hasQuest(20827): # What's Next?
	sm.warp(130030102)
else:
	sm.setSpeakerID(KIMU)
	sm.sendSayOkay("Proceed with the quest #bWhat's Next#k to continue.")