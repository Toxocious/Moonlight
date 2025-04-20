# [Shaolin Temple] Demon B Gone

ZHEUNG_GUAN = 9310046
DEMON_B_GONE = 2434568
PATH_OF_STONE = 701210160

sm.removeEscapeButton()
sm.setSpeakerID(ZHEUNG_GUAN)
sm.setBoxChat()
sm.sendNext("Let me guess. You were sent to help me. Why do they always send me the runts?")

sm.sendNext("I've spent many sleepless nights concocting this #v"+ str(DEMON_B_GONE) +"##t"+ str(DEMON_B_GONE) +"#. Go test it out. If my calculations are correct, the result will be miraculous")

if not sm.canHold(DEMON_B_GONE):
    sm.sendSayOkay("Please make some space in your inventory.")
    sm.dispose()

sm.sendNext("Try it out at #b#m"+ str(PATH_OF_STONE) +"##k.")

response = sm.sendAskYesNo("#b#m"+ str(PATH_OF_STONE) +"##k was once a sacred spot where we performed holy rituals. "
                "Now it's become a playground for demons. Spritz my potion there to get rid of them.")

if response:
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("Uh, will there be any side effects?")

    sm.setSpeakerID(ZHEUNG_GUAN)
    sm.setBoxChat()
    sm.sendNext("We won't know until you try it out. Now get going!")

    sm.giveItem(2434568)
    sm.startQuest(parentID)