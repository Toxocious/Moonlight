randomInt = sm.getRandomIntBelow(1)
if randomInt == 0 and not sm.hasItem(4032616):
    sm.giveItem(4032616)
    sm.systemMessage("You've retrieved a Mirror of Insight from the shattered Opalescent Glass Marble.")
    sm.avatarOriented("Effect/OnUserEff.img/itemEffect/quest/2430071")
else:
    sm.systemMessage("The Opalescent Glass Marble has shattered. Nothing is inside.")
    sm.avatarOriented("Effect/OnUserEff.img/itemEffect/quest/2430071")
sm.consumeItem(2430071)