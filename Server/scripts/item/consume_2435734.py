# For quest 1465. TODO add exp thing
STONES = [2435734, 2435735, 2435736]

sm.completeQuest(1465)
sm.setPlayerAsSpeaker()
sm.sendSayOkay("I should go back to the Memory Keeper to inform him of my progress")

for stone in STONES:
    while sm.hasItem(stone):
        sm.consumeItem(stone)