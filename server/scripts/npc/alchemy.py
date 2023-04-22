# Hidden Street - Ardentmill :: 910001000
# Ally :: Master of Alchemy :: 9031005

HERBALISM_SKILL = 92000000
MINING_SKILL = 92010000
SMITHING_CRAFT_SKILL = 92020000
ACCESSORY_CRAFT_SKILL = 92030000
ALCHEMY_CRAFT_SKILL = 92040000
FEE = [5000, 15000, 25000, 40000, 60000, 85000, 115000, 150000, 190000, 235000]

if not sm.hasSkill(ALCHEMY_CRAFT_SKILL):
    selection = sm.sendSay("Hello. Are you interested in Alchemy?\r\n#L0##bListen to an explanation about #eAlchemy#n.#l\r\n#L1#Learn #eAlchemy#n.#k#l")
    if selection == 0:
        sm.sendNext("Alchemy is the science of turning herb oils into potions. You can make potions to recover HP and MP, potions to make you stronger--potions the likes of which you've never imagined.")
    elif selection == 1:
        if not sm.hasSkill(HERBALISM_SKILL):
            sm.sendSayOkay("You cannot learn alchemy without learning herbalism first.")
            sm.dispose()

        if sm.hasSkill(SMITHING_CRAFT_SKILL) or sm.hasSkill(ACCESSORY_CRAFT_SKILL):
            sm.sendNext("You cannot learn Alchemy if you have already learned Smithing and Accessory Crafting. You must unlearn one of those two Professions if you wish to learn Alchemy.")
            sm.dispose()

        learn = sm.sendAskYesNo("Do you really want to learn #bAlchemy#k?\r\nYou must pay #b5,000 Mesos#k to learn this Profession. \r\n#b")
        if learn:
            if sm.getMesos() < 5000:
                sm.sendNext("Umm... I don't think you have enough money... I'm sorry, but please bring #b5000 Mesos#k.")
                sm.dispose()

            sm.giveMesos(-5000)
            sm.giveSkill(ALCHEMY_CRAFT_SKILL, 0x1000000, 13)
            sm.playSound("profession/levelup")
            sm.sendNext("Congratulations! You are now an Alchemist. Brew up some potions to increase your Mastery. When you're ready, I'll teach you something new.")
        else:
            sm.sendNext("Think carefully before you choose a Profession. Such things take effort and time, after all. Come see me when you are ready.")
else:
    selection = sm.sendSay("Hello. Are you interested in Alchemy?\r\n#L2##bRaise #eAlchemy#n level.#l\r\n#L3#Unlearn Alchemy.#k#l")
    if selection == 2:
        if sm.isAbleToLevelUpMakingSkill(ALCHEMY_CRAFT_SKILL):
            levelup = sm.sendAskYesNo("Looks like you're ready to level up your Alchemy. I'll take #b" + str(FEE[sm.getMakingSkillLevel(ALCHEMY_CRAFT_SKILL)]) + " Mesos#k as tuition. Ready to learn?")
            if levelup:
                if sm.getMesos() < FEE[sm.getMakingSkillLevel(ALCHEMY_CRAFT_SKILL)]:
                    sm.sendNext("You don't have enough mesos.")
                    sm.dispose()

                sm.giveMesos(-FEE[sm.getMakingSkillLevel(ALCHEMY_CRAFT_SKILL)])
                sm.makingSkillLevelUp(ALCHEMY_CRAFT_SKILL)
                sm.sendNext("Your Alchemy skill is now Lv. " + str(sm.getMakingSkillLevel(ALCHEMY_CRAFT_SKILL)) +".")
            else:
                sm.sendNext("Sure, take some time to think it over. I'll be here.")
                sm.dispose()
        else:
            sm.sendNext("You're not ready to learn more Alchemy yet. Work on improving your Mastery first.")
    elif selection == 3:
        unlearn = sm.sendAskYesNo("All your knowledge of Alchemy will be wiped away. You Alchemy level and Mastery will both be reset to 0. Are you sure you want to do this?")
        if unlearn:
            sm.removeSkill(ALCHEMY_CRAFT_SKILL)
            # set quest value 11497, "0"
            # start quest 3263
            # complete quest 3263
            # remove quest 3263
            sm.sendNext("Your Alchemy skill has been reset. Come back if you ever want to learn it again.")
        else:
            sm.sendSayOkay("Yes. It would be a shame to lose all that hard work.")