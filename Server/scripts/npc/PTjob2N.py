# Phantom 2nd job adv
if not sm.hasQuest(25103):
    sm.giveItem(1142376)
    sm.startQuestNoCheck(25103)
    sm.jobAdvance(2410)
    sm.giveSkill(20031209)
    sm.giveSkill(20031260)
    sm.giveSkill(24100003)
    sm.addMaxHP(300)
    sm.addMaxMP(150)

    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("Let's see... 'A History of Ribbon Pigs' first edition... that's not it. 'The Great Mushroom Uprising'... why did I even steal this? Ah, there we are! I'll be back to my old self in no time!")
    sm.sendSay("Judgment Draw was in here as well? Lucky me! I believe that one will show up in the... Beginner Skill window?")
else:
    sm.chat("You are not 1st job Phantom.")
sm.dispose()