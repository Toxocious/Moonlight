sm.setSpeakerID(1032001)
sm.sendNext("Ah, so you are the one that Mai was talking about. How do you do? I heard that you are interested in the path of a Magician. If that's the case, I will help guide you. I am Grendel the Really Old, the Magician Job Instructor.")
sm.sendSay("I'm sure you already know a little bit about Magicians. With high intelligence as our foundation, we learn all manner of magic spells to wield in battle. Range is not a concern for us, but our low HP is our weakness. We've come up with many ways around that, though, so don't worry too much.")
if sm.sendAskAccept("I see that you are more than qualified to beocme a Magician... would you like to become a Magician? If you accept, I will use my power as the Job Instructor to bring you to the #bMagic Library in Ellinia#k. I'll perform the Job Advancement once we meet in person. #rThere are still other paths open to you if you change your mind, and I will help you find them if you do.#k"):
    sm.warp(101000003)
    sm.startQuest(parentID)
else:
    choice = sm.sendNext("You are not content with the path of a Magician? That is unfortunate, but I will respect your decision. Which path will you now choose?\r\n\r\n#b#L0#Warrior#l\r\n#L1#Bowman#l\r\n#L2#Thief#l\r\n#L3#Pirate#l")
    if choice == 0:
        sm.sendNext("You're choosing Warrior? That's disappointing. But if you're sure, I'll send you to #bDances with Balrog#k.")
        sm.createQuestWithQRValue(1406, "1")
        sm.warp(102000003)
    elif choice == 1:
        sm.sendNext("You're choosing Bowman? That's disappointing. But if you're sure, I'll send you to #bAthena Pierce#k.")
        sm.createQuestWithQRValue(1406, "3")
        sm.warp(100000201)
    elif choice == 2:
        sm.sendNext("You're choosing Thief? That's disappointing. But if you're sure, I'll send you to #bthe Dark Lord#k.")
        sm.createQuestWithQRValue(1406, "4")
        sm.warp(103000003)
    elif choice == 3:
        sm.sendNext("You're choosing Pirate? That's disappointing. But if you're sure, I'll send you to #bKyrin#k.")
        sm.createQuestWithQRValue(1406, "5")
        sm.warp(120000101)
    sm.chatScript("Please CC.")
