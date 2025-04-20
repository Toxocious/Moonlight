reactor.incHitCount()
reactor.increaseState()
if reactor.getHitCount() >= 4:
    sm.removeReactor()
    sm.lockInGameUI(True)
    sm.flipDialoguePlayerAsSpeaker()
    sm.removeEscapeButton()
    # todo: show time capsule in below dialog
    sm.sendNext("This ## must be it. I know we promised to open it together guys, but I'm sure you all would understand.")
    sm.sendSay("This is definite Phantom's. A scroll? Hm, Luminious must've put it in. This whetstone must've been used to sharpen Maha. He DOES cherish Maha...")
    sm.sendSay("What's this black thing? Ah, Afrien's scale. And this...the token fo the Ruler of the Elves. Wonder if Mercedes really meant to put this in here. I thought this is supposed to be really important.")
    sm.sendSay("And the last is...")
    # todo: show picture of heroes
    sm.lockInGameUI(False)
    sm.createQuestWithQRValue(38075, "clear")
    sm.completeQuest(38075)
