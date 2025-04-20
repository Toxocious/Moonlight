KIMA = 3003110

sm.setSpeakerID(KIMA)
if sm.hasQuestCompleted(34107) and (sm.hasQuestCompleted(34108) or sm.hasQuest(34108)):
    sm.warp(450001105)
    sm.dispose()
elif sm.hasQuestCompleted(34107):
    sm.sendAskYesNo("...Steering this boat is one of the few things that I enjoy doing...")
elif sm.hasQuestCompleted(34106) and not sm.hasQuestCompleted(34107):
    sm.sendSayOkay("Don't just enter my boat! Talk to me first...")
    sm.dispose()
else:
    sm.sendSayOkay("Sorry I don't remember how to row this boat. If only the #bTree of Memories#k was back to it's original state...")
    sm.dispose()

if sm.sendNext("...Alright, here we go..."):
    sm.warpInstanceIn(450001310, 0) # left side of the map
    sm.rideVehicle(1932393)
    sm.progressMessageFont(3, 20, 20, 0, "Use the direction keys to steer the boat.")
    sm.addPopUpSay(KIMA, 6000, "Cross this lake, and you'll arrive at a massive cliff. I don't know what's behind it.", "")