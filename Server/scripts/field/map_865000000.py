# San Commerci | Used to complete a Quest:  [Commerci Republic] Ciao, Until Next Time
sm.showEffect("Map/EffectBT.img/dawnveil1/temaD") # San Commerci Theme Dungeon Effect


if sm.hasQuest(17614): # [Commerci Republic] Ciao, Until Next Time
    sm.removeEscapeButton()
    sm.sendNext("This place is huge! \r\n"
                   "How am I ever going to find Leon?")
    sm.sendSayOkay("Maybe I can ask this guy for directions")
    sm.completeQuest(17614)

elif sm.hasQuest(17617): # [Commerci Republic] Missing Goods
    sm.chatScript("You were told the impostor was last seen heading south")