# Hidden Street - Ardentmill :: 910001000
# Gere :: Master of Smithing :: 9031003

MINING_SKILL = 92010000
SMITHING_CRAFT_SKILL = 92020000
ACCESSORY_CRAFT_SKILL = 92030000
ALCHEMY_CRAFT_SKILL = 92040000
FEE = [5000, 15000, 25000, 40000, 60000, 85000, 115000, 150000, 190000, 235000]

if not sm.hasSkill(SMITHING_CRAFT_SKILL):
    selection = sm.sendSay("I am #bGere#k, master Blacksmith. What do you want?\r\n#L0#Hear an explanation about #b#eSmithing#n.#l\r\n#L1#Learn #eSmithing#n.#k#l")
    if selection == 0:
        sm.sendNext("Smithing is the art of forging the minerals and gems you get from Mining into durable armor and punishing weapons. I'll show you how to shape these raw materials into equipment you've never seen before.")
    elif selection == 1:
        if not sm.hasSkill(MINING_SKILL):
            sm.sendSayOkay("How do you plan to learn Smithing if you don't even know the Mining Profession? Seek out #bCole#k, learn what he has to teach. Then maybe you will be ready.")
            sm.dispose()

        if sm.hasSkill(ACCESSORY_CRAFT_SKILL) or sm.hasSkill(ALCHEMY_CRAFT_SKILL):
            sm.sendNext("Well now, it seems you've already learned Accessory Crafting or Alchemy. Your reach is exceeding your grasp, isn't it? If you really want to learn it, forget one of the other Professions.")
            sm.dispose()

        learn = sm.sendAskYesNo("You want to learn #bSmithing#k? Show me how earnest you are by coughing up some dough!\r\nIt's #b 5,000 Mesos#k... Are you really ready to do this?\r\n")
        if learn:
            if sm.getMesos() < 5000:
                sm.sendNext("If you can't even gather #b5000 Mesos#k, how can you hope to become a Blacksmith?")
                sm.dispose()

            sm.giveMesos(-5000)
            sm.giveSkill(SMITHING_CRAFT_SKILL, 0x1000000, 13)
            sm.playSound("profession/levelup")
            sm.sendNext("To be honest, I didn't think you could learn Smithing. Well, you've only learned the most basic skill level. If you can manage to increase your Mastery, I can teach you more.")
        else:
            sm.sendNext("It's good to be cautious. Come back after you've thought it through.")
else:
    selection = sm.sendSay("I am #bGere#k, master Blacksmith. What do you want?\r\n#L2##bRaise #eSmithing#n level.#l\r\n#L3#Unlearn Smithing.#k#l")
    if selection == 2:
        if sm.isAbleToLevelUpMakingSkill(SMITHING_CRAFT_SKILL):
            levelup = sm.sendAskYesNo("Looks like you're ready to level up your Smithing. I'll take #b" + str(FEE[sm.getMakingSkillLevel(SMITHING_CRAFT_SKILL)]) + " Mesos#k as tuition. Ready to learn?")
            if levelup:
                if sm.getMesos() < FEE[sm.getMakingSkillLevel(SMITHING_CRAFT_SKILL)]:
                    sm.sendNext("You don't have enough mesos.")
                    sm.dispose()

                sm.giveMesos(-FEE[sm.getMakingSkillLevel(SMITHING_CRAFT_SKILL)])
                sm.makingSkillLevelUp(SMITHING_CRAFT_SKILL)
                sm.sendNext("Your Smithing skill is now Lv. " + str(sm.getMakingSkillLevel(SMITHING_CRAFT_SKILL)) +".")
            else:
                sm.sendNext("Sure, take some time to think it over. I'll be here.")
                sm.dispose()
        else:
            sm.sendNext("You're not ready to level up your Profession. Come back when you've increased your Mastery.")
    elif selection == 3:
        unlearn = sm.sendAskYesNo("You will forget everything I've taught you about Smithing. Everything you've worked towards, gone. Do you really want to do this?")
        if unlearn:
            sm.removeSkill(SMITHING_CRAFT_SKILL)
            # set quest value 11495, "0"
            # start quest 3263
            # complete quest 3263
            # remove quest 3263
            sm.sendNext("Very well. You are no longer a Blacksmith.")
        else:
            sm.sendSayOkay("For once, you say something sensible.")