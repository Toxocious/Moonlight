LANIA = 1032203
MENU_TEXT = ["Light Path", "Dark Path"]

sm.lockInGameUI(True)
sm.curNodeEventEnd(True)

sm.spawnNpc(LANIA, 0, 0)
sm.showNpcSpecialActionByTemplateId(LANIA, "summon", 0)
sm.sendDelay(1500)

sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.playExclSoundWithDownBGM("Voice.img/Luminous_M/0", 100)
sm.sendNext("What have I done?")

sm.moveCamera(False, 300, 0, 27)

sm.sendNext("Lania...")

sm.moveCamera(False, 300, 500, 27)

sm.sendNext("Our home...")

sm.moveCamera(True, 0, 0, 0)

sm.sendNext("The forest... I destroyed it all.")
sm.sendSay("The Black Mage has cursed me! His dark power has corrupted my heart!")
sm.sendSay("But why now? Has the fiend broken free of his prison?!")
sm.sendSay("What can I do? The power of light is lost to me... ")

answer = sm.sendAskSelectMenu(0, 0, MENU_TEXT)
if answer == 0:
    # Light Side
    sm.playExclSoundWithDownBGM("Voice.img/Luminous_M/2", 100)
    sm.sendNext("I will not be swept away by this darkness. I will save Lania and this world... Even if it means my destruction.")
    sm.levelUntil(10)
    sm.giveSkill(20040218, 0, 0)
    sm.jobAdvance(2700)
    sm.giveSkill(20040216, 1, 1)
    sm.giveSkill(20040217, 1, 1)
    sm.giveSkill(20040221, 1, 1)
    sm.giveSkill(20041222, 1, 1)
    sm.giveSkill(27001100, 1, 20)
    sm.giveSkill(27000106, 1, 5)
    sm.setSTR(4)
    sm.setDEX(4)
    sm.setAP(35)
    sm.giveItem(1142478)
    sm.giveAndEquip(1052496)
    sm.giveAndEquip(1072701)
    sm.giveAndEquip(1102443)
    sm.giveAndEquip(1352400)
    sm.lockInGameUI(False)
    sm.removeNpc(LANIA)
    sm.warp(101000100)
else:
    # Dark Side
    sm.sendNext("Is this how the Black Mage understood the world? I see now that everyone else is beneath me!")
    sm.sendSay("My soul was almost lost to the power of darkness. I see its appeal, but I would not have it erode my entire being. I will learn to harness it, and make it my own. ")
    sm.sendSay("But first, I must master my new magic. My old weapons of Light will now be tools of the Dark.")
    sm.levelUntil(10)
    sm.giveSkill(20040218, 3, 3)
    sm.jobAdvance(2700)
    sm.giveSkill(20040216, 1, 1)
    sm.giveSkill(20040217, 1, 1)
    sm.giveSkill(20040221, 1, 1)
    sm.giveSkill(20041222, 1, 1)
    sm.giveSkill(27001201, 1, 20)
    sm.giveSkill(27000207, 1, 5)
    sm.setSTR(4)
    sm.setDEX(4)
    sm.setAP(35)
    sm.giveItem(1142479)
    sm.giveAndEquip(1052497)
    sm.giveAndEquip(1072702)
    sm.giveAndEquip(1102444)
    sm.giveAndEquip(1352400)
    sm.sendSay("Yes, it feels good to have a weapon in my hands once more. Now, who shall I test my new powers on first...")
    sm.lockInGameUI(False)
    sm.removeNpc(LANIA)
    sm.warp(101020100)
