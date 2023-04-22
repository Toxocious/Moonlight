# 927030050
FOX_POINT_PATH = 940200060
CURSE_YOUR_HUMAN_LEGS = 38000
SHADE_SKIP_QR = 37999

QUESTS_TO_SKIP = [38000, 38001, 38002, 38003, 38004, 38005, 38006, 38007, 38008, 38009, 38010, 38011, 38012, 38013, 38014, 38015, 38016, 38017, 38018, 38019, 38020, 38021, 38022, 38023, 38024, 38025, 38026, 38027]

sm.setSpeakerID(2007)
sm.setBoxChat()
if sm.sendAskAccept("Would you like to complete #r#eall quests up to 2nd job advancement#k#n? (You'll have to grind until level 27.)"):
    sm.addLevel(9)
    sm.jobAdvance(2500)
    sm.giveAndEquip(1353100)
    sm.giveItem(1142671)
    sm.completeQuestNoRewards(32024)
    sm.giveItem(3010766)
    sm.resetAP(False, 2500)
    sm.giveSkill(20051284)
    sm.giveSkill(20050285)
    sm.giveSkill(25001002, 0, 25)
    sm.warp(410000000)
    for q in QUESTS_TO_SKIP:
        sm.startQuestNoCheck(q)
        sm.completeQuestNoRewards(q)
    sm.consumeItem(4033998, 4)
    sm.dispose()

if sm.sendAskYesNo("Would you like to skip the tutorial cutscene?"):
    sm.createQuestWithQRValue(SHADE_SKIP_QR, "SKIPPED_ILLUST")
    sm.warp(FOX_POINT_PATH)
    sm.dispose()

sm.lockInGameUI(True, False)
sm.hideUser(True)
sm.forcedInput(0)
if "3" in sm.getQRValue(38907):
    sm.reservedEffect("Effect/Direction15.img/eunwolTutorial/Scene1")
    sm.sendDelay(16000)
    sm.warpInstanceIn(927030040, 0)
elif "2" in sm.getQRValue(38907):
    sm.reservedEffect("Effect/Direction15.img/eunwolTutorial/Scene0")
    sm.sendDelay(4000)
    sm.warpInstanceIn(927030030, 0)
elif "1" in sm.getQRValue(38900):
    sm.sendDelay(500)
    sm.showFieldEffect("Map/Effect2.img/eunwol/enter")
    sm.sendDelay(3500)
    sm.warpInstanceIn(927030020, 0)
else:
    sm.sendDelay(500)
    sm.sayMonologue("\r\n\r\nA great darkness is covering Maple World.", False)
    sm.sayMonologue("\r\nThe Black Mage wishes to dominate Maple World with overwhelming darkness.", False)
    sm.sayMonologue("\r\nMany feared the Black Mage and his incredible strength, and few dared to oppose him.", True)

    sm.sayMonologue("\r\nDespair reigned in Maple World.", False)
    sm.sayMonologue("\r\n\r\n\r\nBut then...", False)
    sm.sayMonologue("\r\nThere came heroes who were bold enough to oppose the Black Mage.", True)
    sm.sendDelay(1000)

    sm.reservedEffect("Effect/Direction8.img/lightningTutorial/Scene0")
    sm.sendDelay(3300)

    sm.warpInstanceIn(927030000, 0)
