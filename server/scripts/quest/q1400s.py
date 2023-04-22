sm.setSpeakerID(12100)
sm.sendNext("Hmm, you're making good progress with your leveling. Have you decided on which job you want to take? You could be a Warrior with great strength and high HP, a Magician with many spells, a Bowman that shoots arrows from afar, a Thief that uses quick, sneaky attacks, or a Pirate with all kinds of flashy chain skills... There are so many!")
choice = sm.sendNext("If you go to Victoria Island, you can advance to the job of your choice by going to the right Job Instructor. But before that, lemme know which one you're interested in, and I'll send #bthem#k a letter of recommendation. That will make it easier for you to advance! So, which job will you choose?\r\n\r\n#b#L0#I want to be a might Warrior!#l\r\n#L1#I want to be a mystical Magician!#l\r\n#L2#I want to be a sharp-shooting Bowman!#l\r\n#L3#I want to be a sneaky Thief!#l\r\n#L4#I want to be a swashbuckling Pirate!#l\r\n#L5#I want to be a pinky bean!#l")

sm.createQuestWithQRValue(1406, str(choice+1))
sm.startQuest(parentID)
sm.completeQuest(parentID)

if choice == 0:
    sm.sendNext("A Warrior, huh? Boy, you're going to get really strong! They can take tons of damage, and dish plenty out, too. Okay, I'll send my recommendation to #bDances with Balrog#k, the Warrior Job Instructor.")
    sm.sendSay("He will contact you when you reach Lv. 10. Become a great Warrior!")
elif choice == 1:
    sm.sendNext("A Magician, huh? Okay, I'll send my recommendation to #bGrendel, the Really Old#k, the Magician Job Instructor.")
    sm.sendSay("They will contact you when you reach Lv. 10. Become a great Magician!")
elif choice == 2:
    sm.sendNext("A Bowman, huh? Okay, I'll send my recommendation to #bAthena Pierce#k, the Bowman Job Instructor.")
    sm.sendSay("They will contact you when you reach Lv. 10. Become a great Bowman!")
elif choice == 3:
    sm.sendNext("A Thief, huh? Okay, I'll send my recommendation to #bthe Dark Lord#k, the Thief Job Instructor.")
    sm.sendSay("They will contact you when you reach Lv. 10. Become a great Thief!")
elif choice == 4:
    sm.sendNext("A Pirate, huh? Okay, I'll send my recommendation to #bKyrin#k, the Pirate Job Instructor.")
    sm.sendSay("They will contact you when you reach Lv. 10. Become a great Pirate!")
elif choice == 5:
    sm.sendNext("#bA Pink Bean, huh? Okay, I would usually send a recommendation to some job instructor but since you want to be a Pink Bean I'll just throw 'em skills at ya.")
    sm.jobAdvance(13100)
