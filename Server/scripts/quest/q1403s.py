sm.setSpeakerID(1012100)
sm.sendNext("Hello, #h #. I've heard plenty about you from Mai. You are interested in becoming a Bowman, right? My name is Athena Pierce, Bowman Job Instructor. Nice to meet you!")
sm.sendSay("How much do you know about Bowmen? We use bows or crossbows to attack enemies at long range, mainly. We're a bit slower than others, but our arrows never miss their mark!")
if sm.sendAskAccept("If you really wish to become a Bowman, I will bring you to the #bBowman Instructional School in Henesys#k using my power as the Job Instructor, #rif you are interested in other jobs, however, I will help you find your true path#k. Now, would you like to become a Bowman?"):
    sm.warp(100000201)
    sm.startQuest(parentID)
else:
    choice = sm.sendNext("So, you have chosen another path. That is your decision, of course. Which path will you now choose?\r\n\r\n#b#L0#Warrior#l\r\n#L1#Magician#l\r\n#L2#Thief#l\r\n#L3#Pirate#l")
    if choice == 0:
        sm.sendNext("You seek the powerful strength of a Warrior, do you? Then I'll send you to #bDances with Balrog#k.")
        sm.createQuestWithQRValue(1406, "1")
        sm.warp(102000003)
    elif choice == 1:
        sm.sendNext("You seek the powerful strength of a Magician, do you? Then I'll send you to #bGrendel the really Old#k.")
        sm.createQuestWithQRValue(1406, "2")
        sm.warp(101000003)
    elif choice == 2:
        sm.sendNext("You seek the powerful strength of a Thief, do you? Then I'll send you to #bthe Dark Lord#k.")
        sm.createQuestWithQRValue(1406, "4")
        sm.warp(103000003)
    elif choice == 3:
        sm.sendNext("You seek the powerful strength of a Pirate, do you? Then I'll send you to #bKyrin#k.")
        sm.createQuestWithQRValue(1406, "5")
        sm.warp(120000101)
    sm.chatScript("Please CC.")