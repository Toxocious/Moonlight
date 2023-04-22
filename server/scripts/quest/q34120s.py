# [Vanishing Journey] Arcane Symbol: Vanishing Journey
VANISHING_JOURNEY_ARCANE_SYMBOL = 1712001
sm.setPlayerAsSpeaker()
if sm.canHold(VANISHING_JOURNEY_ARCANE_SYMBOL):
    sm.sendNext("#b(I've picked up the Arcane Symbol that Kao left behind.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.giveItem(VANISHING_JOURNEY_ARCANE_SYMBOL)
    sm.progressMessageFont(3, 20, 20, 0, "Kao's last wish was for me to jump into the waterfall..")
else:
    sm.sendSayOkay("I should make some space in my inventory.")