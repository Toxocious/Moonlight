# Hidden Street - Ardentmill :: 910001000
# Gere :: Master of Mining :: 9031002

Pickaxe = 1512000
MINING_SKILL = 92010000
FEE = [5000, 15000, 25000, 40000, 60000, 85000, 115000, 150000, 190000, 235000]

if not sm.hasSkill(MINING_SKILL):
    selection = sm.sendSay("Now what can I do for ya?\r\n#L0##bHear an explanation about #eMining#n.#l\r\n#L1#Learn #eMining#n.#k#l")
    if selection == 0:
        sm.sendNext("If you're looking to get yourself some minerals, all you need is the Mining skill. Refine the minerals you collect in one of them molds #p9031006# sells, then use them to craft all sorts of useful items.")
    elif selection == 1:
        learn = sm.sendAskYesNo("Do you really want to learn #bMining#k? It'll cost you some money... #b5,000 Mesos#k, to be exact.\r\n")
        if learn:
            if sm.getMesos() < 5000:
                sm.sendNext("You don't got enough Mesos. I need #b5000 Mesos#k from every student, no exceptions.")
                sm.dispose()

            if not sm.canHold(Pickaxe):
                sm.sendNext("You don't have enough inventory slots in your equip tab.")
                sm.dispose()

            sm.giveMesos(-5000)
            sm.giveSkill(MINING_SKILL, 0x1000000, 10)
            sm.giveItem(Pickaxe)
            sm.playSound("profession/levelup")
            sm.sendNext("Okay, them's the basics of Mining. Work on increasing your Mastery, and I'll teach you some new tricks.")
        else:
            sm.sendNext("It's good to be cautious. Come back after you've thought it through.")
else:
    selection = sm.sendSay("Now what can I do for ya?\r\n#L2##bLevel up #eMining#n.#l\r\n#L3##bTrade #t4011010#.#k#l")
    if selection == 2:
        if sm.isAbleToLevelUpMakingSkill(MINING_SKILL):
            levelup = sm.sendAskYesNo("Looks like you're ready to level up your Mining. I'll take #b" + str(FEE[sm.getMakingSkillLevel(MINING_SKILL)]) + " Mesos#k as tuition. Ready to learn?")
            if levelup:
                if sm.getMesos() < FEE[sm.getMakingSkillLevel(MINING_SKILL)]:
                    sm.sendNext("You don't have enough mesos.")
                    sm.dispose()
                sm.giveMesos(-FEE[sm.getMakingSkillLevel(MINING_SKILL)])
                sm.makingSkillLevelUp(MINING_SKILL)
                sm.sendNext("Your Mining skill is now Lv. " + str(sm.getMakingSkillLevel(MINING_SKILL)) +".")
            else:
                sm.sendNext("Sure, take some time to think it over. I'll be here.")
                sm.dispose()
        else:
            sm.sendNext("You're not ready to level up your Profession. Come back when you've increased your Mastery.")
    elif selection == 3:
        sm.sendSayOkay("#b100 #t4011010#s#k can be traded for 1 #i2028067:##b#t2028067##k. Please dig up some more #t4011010#s.")