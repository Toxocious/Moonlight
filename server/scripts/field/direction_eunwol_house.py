SNIFFS = 3002003

sm.setSpeakerID(2007)
if sm.sendAskYesNo("Would you like to skip the tutorial cutscenes?"):
    sm.createQuestWithQRValue(37999, "SKIP_RAIN_EXPLAIN")
if sm.getQRValue(37999) != "SKIP_RAIN_EXPLAIN":
    sm.lockInGameUI(True, False)
    sm.hideUser(False)
    sm.spawnNpc(SNIFFS, -512, 5)
    sm.showNpcSpecialActionByTemplateId(SNIFFS, "summon")
    sm.sendDelay(1000)

    sm.removeEscapeButton()
    sm.setSpeakerID(SNIFFS)
    sm.sendNext("Shade! Something bad is happening!")

    sm.forcedInput(1)
    sm.sendDelay(100)

    sm.forcedInput(0)
    sm.setPlayerAsSpeaker()
    sm.sendNext("...Well, I'm not in the best situation either... Anyway, what happened?")

    sm.setSpeakerID(SNIFFS)
    sm.sendSay("I-it's raining in the town!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Rain? Hm, I guess it's the first time I've seen it rain in this town. But why is it so bad? Are there damages to the houses?")

    sm.setSpeakerID(SNIFFS)
    sm.sendSay("No, no! Raining means... It means... You should hear it from the chief himself.  Hurry, go talk to him!")

    sm.removeNpc(SNIFFS)
sm.startQuestNoCheck(38019)
sm.startQuestNoCheck(38903)  # interestingly enough if you do the skip in GMS it doesn't start raining so this quest isn't started
sm.lockInGameUI(False)
sm.warp(410000001, 0)
