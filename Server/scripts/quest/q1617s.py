# [Silent Crusade] The Silent Crusade

BASTILLE = 9073003
time = 5 *60

sm.setSpeakerID(BASTILLE)
response = sm.sendAskYesNo("Yes, yes, I'm sure you're eager to know all about our little operation. "
                           "But first thing's first! I must test your aptitude. "
                           "Are you ready to join the Silent Crusade? Well, are you?")

if response:
    sm.sendNext("We shall see. Your test is simple enough: Defeat my puppy, Coco. Shall we begin?")

    sm.warpInstanceIn(931050510)
    sm.setInstanceTime(time, 931050500)
    sm.startQuest(parentID)

else:
    sm.sendSayOkay("No?...")