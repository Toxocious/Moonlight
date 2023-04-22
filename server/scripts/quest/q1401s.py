sm.setSpeakerID(1022000)
sm.sendNext("So, you are the person Mai recommended. You seek to become a Warrior, am I right? I am Dances with Balrog, the Warrior Job Instructor. I instruct newcomers in the ways of battle.")
sm.sendSay("How much do you know about Warriors? Warriors have great strength and high HP, and face their enemies up-close with powerful attacks. Sounds fun, right?")
if sm.sendAskAccept("You look like you are more than qualified. If you wish to become a Warrior, I welcome you. You wish to become a Warrior? If you accept, I will use my power as the Job Instructor to bring you to the #bWarriors' Sanctuary in Perion#k right away. #rThere are still paths for you even if you change your mind, and I will help you if you do.#k."):
    sm.warp(102000003)
    sm.startQuest(parentID)
else:
    choice = sm.sendNext("You do not wish to choose the path of a Warrior? Very well. There are four other paths you can choose.\r\n\r\n#b#L0#Magician#l\r\n#L1#Bowman#l\r\n#L2#Thief#l\r\n#L3#Pirate#l")
    if choice == 0:
        sm.sendNext("Do you want to go the way of the Magician? I'll admit I'm disappointed, but I'll respect your decision and send you to #bGrendel the Really Old#k.")
        sm.createQuestWithQRValue(1406, "2")
        sm.warp(101000003)
    elif choice == 1:
        sm.sendNext("Do you want to go the way of the Bowman? I'll admit I'm disappointed, but I'll respect your decision and send you to #b#k.Then I'll send you to #bAthena Pierce#k.")
        sm.createQuestWithQRValue(1406, "3")
        sm.warp(100000201)
    elif choice == 2:
        sm.sendNext("Do you want to go the way of the Thief? I'll admit I'm disappointed, but I'll respect your decision and send you to #b#k.Then I'll send you to #bthe Dark Lord#k.")
        sm.createQuestWithQRValue(1406, "4")
        sm.warp(103000003)
    elif choice == 3:
        sm.sendNext("Do you want to go the way of the Pirate? I'll admit I'm disappointed, but I'll respect your decision and send you to #b#k.Then I'll send you to #bKyrin#k.")
        sm.createQuestWithQRValue(1406, "5")
        sm.warp(120000101)
    sm.chatScript("Please CC.")
