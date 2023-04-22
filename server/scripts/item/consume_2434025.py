# Pink Bean Balloon Mount (30 Days) Coupon  |  (2434025)
if sm.getSkillByItem() == 0:# Check whether item has an vehicleID stored,  0 if false.
    sm.chat("An Error occurred whilst trying to find the mount.")
elif sm.hasSkill(sm.getSkillByItem()):
    sm.chat("You already have the 'Pink Bean Balloon (30 Days)' mount.")
else:
    sm.consumeItem()
    sm.giveSkill(sm.getSkillByItem())
    sm.chat("Successfully added the 'Pink Bean Balloon (30 Days)' mount.")
sm.dispose()
