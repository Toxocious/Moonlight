# Glacier  |  (2430050)x
if sm.hasSkill(2439913):
    sm.chat("You already have the 'Glacier' mount.")
else:
    sm.consumeItem()
    sm.giveSkill(2439913)
    sm.chat("Successfully added the 'Galcier' mount.")
sm.dispose()
