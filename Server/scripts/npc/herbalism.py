# Hidden Street - Ardentmill :: 910001000
# Saffron :: Master of Herbalism :: 9031001

HERBALISM_SKILL = 92000000
FEE = [5000, 15000, 25000, 40000, 60000, 85000, 115000, 150000, 190000, 235000]

if not sm.hasSkill(HERBALISM_SKILL):
    selection = sm.sendSay("Hello. What can I help you with?\r\n#L0#Hear an explanation about #b#eHerbalism#n.#l\r\n#L1#Learn #eHerbalism#n.#k#l")
    if selection == 0:
        sm.sendNext("Herbalism lets you gather herbs. You can buy Oil Bottles from #p9031007# to refine the herbs and use them as materials to craft equipment, accessories, alchemy tools, and the like.")
    if selection == 1:
        learn = sm.sendAskYesNo("To learn #bHerbalism#k, you must pay #b5,000 Mesos#k. You sure you want to learn it?\r\n")
        if learn:
            if sm.getMesos() < FEE[0]:
                sm.sendNext("Umm... I don't think you have enough money... I'm sorry, but please bring #b5000 Mesos#k.")
                sm.dispose()

            sm.giveMesos(-FEE[0])
            sm.giveSkill(HERBALISM_SKILL, 0x1000000, 10)
            sm.playSound("profession/levelup")
            sm.sendNext("Congratulations! You're an Herbalist. See me again when you've increased your Mastery and I'll level up your Herbalism skill.")
        else:
            sm.sendNext("Ah, it's smart not to rush into anything. Come back after you've pondered it some more.")
else:
    selection = sm.sendSay("Hello. What can I help you with?\r\n#L2##bLevel up #eHerbalism#n.#l\r\n#L3##bTrade #t4022023#.#k#l")
    if selection == 2:
        if sm.isAbleToLevelUpMakingSkill(HERBALISM_SKILL):
            levelup = sm.sendAskYesNo("Looks like you're ready to level up your Herbalism. I'll take #b" + str(FEE[sm.getMakingSkillLevel(HERBALISM_SKILL)]) + " Mesos#k as tuition. Ready to learn?")
            if levelup:
                if sm.getMesos() < FEE[sm.getMakingSkillLevel(HERBALISM_SKILL)]:
                    sm.sendNext("You don't have enough mesos.")
                    sm.dispose()
                sm.giveMesos(-FEE[sm.getMakingSkillLevel(HERBALISM_SKILL)])
                sm.makingSkillLevelUp(HERBALISM_SKILL)
                sm.sendNext("Your Herbalism skill is now Lv. " + str(sm.getMakingSkillLevel(HERBALISM_SKILL)) +".")
            else:
                sm.sendNext("Sure, take some time to think it over. I'll be here.")
                sm.dispose()
        else:
            sm.sendNext("Have you been working on your Mastery? You can't level up this Profession yet.")