# Permanent Hot-Air Balloon Mount Coupon  |  (2433168)
if sm.getSkillByItem() == 0:# Check whether item has an vehicleID stored,  0 if false.
    sm.chat("An Error occurred whilst trying to find the mount.")
elif sm.hasSkill(sm.getSkillByItem()):
    sm.chat("You already have the 'Permanent Hot-Air Balloon' mount.")
else:
    sm.consumeItem()
    sm.giveSkill(sm.getSkillByItem())
    sm.chat("Successfully added the 'Permanent Hot-Air Balloon' mount.")
sm.dispose()
