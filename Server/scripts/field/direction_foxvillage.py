LUMPS = 3002001
SALLY = 3002002
SNIFFS = 3002003
TWITCH = 3002004
SILVER = 3002005
COMPASS = 3002006
TIMBER = 3002007
BROOK = 3002008
PATIENCE = 3002009
TUMBLEWEED = 3002105

sm.setSpeakerID(2007)
if sm.sendAskYesNo("Would you like to skip the tutorial cutscenes?"):
    sm.createQuestWithQRValue(37999, "SKIP_FOXVILLAGE")

if sm.getQRValue(37999) != "SKIP_FOXVILLAGE":
    sm.lockInGameUI(True)
    sm.forcedInput(0)

    sm.spawnNpc(LUMPS, 300, 89)
    sm.showNpcSpecialActionByTemplateId(LUMPS, "summon")

    sm.spawnNpc(SALLY, -175, 0)
    sm.showNpcSpecialActionByTemplateId(SALLY, "summon")

    sm.spawnNpc(SNIFFS, 360, 103)
    sm.showNpcSpecialActionByTemplateId(SALLY, "summon")

    sm.spawnNpc(TWITCH, -238, -33)
    sm.showNpcSpecialActionByTemplateId(TWITCH, "summon")

    sm.spawnNpc(SILVER, 84, 37)
    sm.showNpcSpecialActionByTemplateId(SILVER, "summon")

    sm.spawnNpc(COMPASS, 174, 55)
    sm.showNpcSpecialActionByTemplateId(COMPASS, "summon")

    sm.spawnNpc(TIMBER, -128, 51)
    sm.showNpcSpecialActionByTemplateId(TIMBER, "summon")

    sm.spawnNpc(BROOK, -190, 77)
    sm.showNpcSpecialActionByTemplateId(BROOK, "summon")

    sm.spawnNpc(PATIENCE, 241, 72)
    sm.showNpcSpecialActionByTemplateId(PATIENCE, "summon")

    sm.spawnNpc(TUMBLEWEED, -61, 41)
    sm.showNpcSpecialActionByTemplateId(TUMBLEWEED, "summon")
    sm.sendDelay(2000)

    sm.removeEscapeButton()
    sm.setSpeakerID(TIMBER)
    sm.sendNext("We were really getting used to having you around here...")

    sm.setSpeakerID(BROOK)
    sm.sendSay("Have all three meals of the day. Especially breakfast. Foxes live to eat.")

    sm.setSpeakerID(SILVER)
    sm.sendSay("You're leaving this town, but don't ever forget you are one of us Pointy-Ear Foxes. Don't forget the pride of foxes. Come back anytime you like. We'll be waiting for you.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("Thank you for all you've done. And I will never...never forget.")

    sm.setSpeakerID(TWITCH)
    sm.sendSay("Shade! You sound like you're leaving forever! Come back soon, and bring yummy treats next time!")

    sm.setSpeakerID(SILVER)
    sm.sendSay("Ah, old age makes me tear up about anything. But enough about me, Moonbeam won't come out of her room. She doesn't want to see you leave. And that heavy rainfall... I don't think she'll stop crying anytime soon.")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("......")

    sm.setSpeakerID(TUMBLEWEED)
    sm.sendSay("I still think you should wait a couple days and go with me, but I guess if you're busy that's too bad. Here, take this scroll, it will take you right to Pantheon. When you get there, talk to  #rCartalion#k. Tell him you came on behalf of me and he'll take good care of you.")

    sm.removeNpc(LUMPS)
    sm.removeNpc(SALLY)
    sm.removeNpc(SNIFFS)
    sm.removeNpc(TWITCH)
    sm.removeNpc(SILVER)
    sm.removeNpc(COMPASS)
    sm.removeNpc(TIMBER)
    sm.removeNpc(BROOK)
    sm.removeNpc(PATIENCE)
    sm.removeNpc(TUMBLEWEED)

sm.lockInGameUI(False)
sm.startQuest(38028)
sm.warp(410000000, 6)
