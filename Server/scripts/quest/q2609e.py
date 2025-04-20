LADY_SYL = 1056000

sm.setSpeakerID(LADY_SYL)
sm.sendNext("#h0#, you look rather green. Are you ill? You're poisoned? Did Ryden tell you that? The potion was just apple juice. Couldn't you tell? Anyway, Ryden was just making a point...")
sm.sendSay("Don't even THINK about betraying us. The Dual Blades do not forgive their enemies...")

if sm.sendAskYesNo("The look in your eyes, the lift in your shoulders. You seem ready. Do you wish to advance to #bRogue#k? Once you do, you can begin your REAL missions."):
    if chr.getLevel() >= 10:
        sm.giveItem(1332063)
        sm.startQuest(parentID)
        sm.completeQuest(parentID)
        sm.startQuest(29900)
        sm.completeQuest(29900)
        sm.jobAdvance(400)
        sm.setSTR(4)
        sm.setDEX(4)
        sm.setLUK(25)
        sm.setAP(33)
        sm.startQuest(17903)
        sm.completeQuest(17903)
        sm.avatarOriented("Effect/OnUserEff.img/RecordClear_BT/clear")
        sm.sendNext("You're not a Thief, though you haven't learned any Dual Blade skills yet. But you should be able to approach the Dark Lord as our spy.")
        sm.sendSay("Dual Blades and Thieves value the same stats, specifically LUK, with DEX as a secondary. Use the #bAuto-Assign#k feature if you're unsure how to allocate your stats.")
        sm.sendSay("You'll need a lot of items to be a successful spy, so I've increased your Equip and ETC tab slots.")
        sm.sendSay("That's all from me. Ryden will you in on the details. I look forward to fruitful results...")
    else:
        sm.sendSay("Please talk to me again after reaching level 10.")
        sm.disose()

else:
    sm.sendNext("I can see you're not yet prepared...")
    sm.dispose()