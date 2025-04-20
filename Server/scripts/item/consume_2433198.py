# Permanent Feline Pirate Ship Mount Coupon  |  (2433198)
if sm.getSkillByItem() == 0:# Check whether item has an vehicleID stored,  0 if false.
    sm.chat("An Error occurred whilst trying to find the mount.")
elif sm.hasSkill(sm.getSkillByItem()):
    sm.chat("You already have the 'Permanent Feline Pirate Ship' mount.")
else:
    sm.consumeItem()
    sm.giveSkill(sm.getSkillByItem())
    sm.chat("Successfully added the 'Permanent Feline Pirate Ship' mount.")
sm.dispose()
