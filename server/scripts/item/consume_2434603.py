# High Quality Single Passenger Classic Car Mount  |  (2434603)
if sm.getSkillByItem() == 0:# Check whether item has an vehicleID stored,  0 if false.
    sm.chat("An Error occurred whilst trying to find the mount.")
elif sm.hasSkill(sm.getSkillByItem()):
    sm.chat("You already have the 'High Quality Single Passenger Classic Car' mount.")
else:
    sm.consumeItem()
    sm.giveSkill(sm.getSkillByItem())
    sm.chat("Successfully added the 'High Quality Single Passenger Classic Car' mount.")
sm.dispose()
