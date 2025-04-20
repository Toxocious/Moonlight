# Hidden Street - Ardentmill :: 910001000
# Intaglio :: Master of Accessory Crafting :: 9031004

MINING_SKILL = 92010000
SMITHING_CRAFT_SKILL = 92020000
ACCESSORY_CRAFT_SKILL = 92030000
ALCHEMY_CRAFT_SKILL = 92040000
FEE = [5000, 15000, 25000, 40000, 60000, 85000, 115000, 150000, 190000, 235000]

if not sm.hasSkill(ACCESSORY_CRAFT_SKILL):
    selection = sm.sendSay("The brilliant radiance! The crystalline purity! The prismatic beauty! Are you a Jeweler too, my friend? Shall we seek the mysteries of Accessory Crafting together?\r\n#L0#Hear an explanation about #b#eAccessory Crafting#n.#l\r\n#L1#Learn #eAccessory Crafting#n.#k#l")
    if selection == 0:
        sm.sendNext("Where to begin, where to begin? I could tell you about the inherent beauty of jewels, but...that could take all night.\r\nIn short, Accessory Crafting is the art of taking a raw jewel or mineral, and shaping it until its true beauty comes shining through. Even the roughest gemstone has the power to become not only elegant, but powerful.")
    elif selection == 1:
        if not sm.hasSkill(MINING_SKILL):
            sm.sendSayOkay("Oh, no. You simply MUST learn Mining from #bCole#k before I can teach you to be a Jeweler. He'll teach you how to get all the minerals and jewels you need to make shining, glowing accessories.")
            sm.dispose()

        if sm.hasSkill(SMITHING_CRAFT_SKILL) or sm.hasSkill(ALCHEMY_CRAFT_SKILL):
            sm.sendNext("Don't you know you can't learn Accessory Crafting if you've already learned Smithing and Alchemy? Shh... All you need to do is delete one of your current Professions, and we can study Accessory Crafting together!")
            sm.dispose()

        learn = sm.sendAskYesNo("Oh, you're ready to learn #bAccessory Crafting#k?\r\nSince you're so cute, I'll give you a discount. #b5,000 Mesos#k to become my student.\r\n")
        if learn:
            if sm.getMesos() < 5000:
                sm.sendNext("You don't have #b 5000 Mesos#k? I wish I could, but I just can't teach you for free.")
                sm.dispose()

            sm.giveMesos(-5000)
            sm.giveSkill(ACCESSORY_CRAFT_SKILL, 0x1000000, 13)
            sm.playSound("profession/levelup")
            sm.sendNext("Oh! Wonderful! And that's how you do Accessory Crafting. Practice, practice, practice, and when you've gained enough Mastery, I'll teach you some more.")
        else:
            sm.sendNext("What? Why not?! I was looking forward to sharing my knowledge with you!")
else:
    selection = sm.sendSay("The brilliant radiance! The crystalline purity! The prismatic beauty! Are you a Jeweler too, my friend? Shall we seek the mysteries of Accessory Crafting together?\r\n#L2##bRaise #eAccessory Crafting#n level.#l\r\n#L3#Unlearn Accessory Crafting.#k#l")
    if selection == 2:
        if sm.isAbleToLevelUpMakingSkill(ACCESSORY_CRAFT_SKILL):
            levelup = sm.sendAskYesNo("Looks like you're ready to level up your Accessory Crafting. I'll take #b" + str(FEE[sm.getMakingSkillLevel(ACCESSORY_CRAFT_SKILL)]) + " Mesos#k as tuition. Ready to learn?")
            if levelup:
                if sm.getMesos() < FEE[sm.getMakingSkillLevel(ACCESSORY_CRAFT_SKILL)]:
                    sm.sendNext("You don't have enough mesos.")
                    sm.dispose()
                sm.giveMesos(-FEE[sm.getMakingSkillLevel(ACCESSORY_CRAFT_SKILL)])
                sm.makingSkillLevelUp(ACCESSORY_CRAFT_SKILL)
                sm.sendNext("Your Accessory Crafting skill is now Lv. " + str(sm.getMakingSkillLevel(ACCESSORY_CRAFT_SKILL)) +".")
            else:
                sm.sendNext("Sure, take some time to think it over. I'll be here.")
                sm.dispose()
        else:
            sm.sendNext("Oh, you're not ready to level up your Profession just yet. Keep at it, though!")
    elif selection == 3:
        unlearn = sm.sendAskYesNo("You want to delete your Accessory Crafting skill? Are you tired of me already? All the effort you've put into raising your level and Mastery will be lost... All that...effort...gone... Are you really doing this?")
        if unlearn:
            sm.removeSkill(ACCESSORY_CRAFT_SKILL)
            # set quest value 11496, "0"
            # start quest 3263
            # complete quest 3263
            # remove quest 3263
            sm.sendNext("It's been reset... You are so cold... But if you ever change your mind, I'll be here.")
        else:
            sm.sendSayOkay("Oh, thank you, thank you, thank you!")