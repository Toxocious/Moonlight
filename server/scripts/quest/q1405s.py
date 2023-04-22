sm.setSpeakerID(1090000)
sm.sendNext("#h #...? Oh, right! Mai mentioned you. Yeah...you look pretty good to me. I'm Kyrin, the captain of the Nautilus and the Pirate Job Instructor. I heard you are interested in becoming a Pirate. Is that true?")
sm.sendSay("If that's the case, I should tell you a bit about myself first. I brought the pirates together to start working against the Black Mage, the great evil that threatens all of Maple World. Turns out the hero business is more profitable than looting and pillaging!")
sm.sendSay("If you become a Pirate, you can help investigate the Black Mage's plots, and assist in the defense of Maple World. Keep in mind that I won't make you do anything...I'm primarily a Job Instructor, and just guide the pirates in a general sense.")
sm.sendSay("But, I know you would help us fight the Black Mage. You have that gleam in your eye that all heroes do. Anyway, I've said my piece. That was just for your information. What's really important is coming up next.")
sm.sendSay("There are two paths you can take as a Pirate. You can fight with guns, or with your fists. Your weapons and skills will be quite different depending on what you pick, but both are still Pirates. And that means you're gonna look GOOD while you fight!")
if sm.sendAskAccept("Okay, I've said enough. So, Pirate. In, or out? If you want to become a Pirate, I'll bring you to the Nautilus right now using my power as a Job Instructor. #rAnd if you don't, I'll help you find the right job for you#k."):
    sm.warp(120000101)
    sm.startQuest(parentID)
else:
    choice = sm.sendNext("You wish to choose a different path? Hey, if that's what you want. Which job will you choose, then?\r\n\r\n#b#L0#Warrior#l\r\n#L1#Magician#l\r\n#L2#Bowman#l\r\n#L3#Thief#l")
    if choice == 0:
        sm.sendNext("You want to be a Warrior? I don't really understand why, but all right. I'll send you to #bDances with Balrog#k.")
        sm.createQuestWithQRValue(1406, "1")
        sm.warp(102000003)
    elif choice == 1:
        sm.sendNext("You want to be a Magician? I don't really understand why, but all right. I'll send you to #bGrendel the really Old#k.")
        sm.createQuestWithQRValue(1406, "2")
        sm.warp(101000003)
    elif choice == 2:
        sm.sendNext("You want to be a Bowman? I don't really understand why, but all right. I'll send you to #bAthena Pierce#k.")
        sm.createQuestWithQRValue(1406, "3")
        sm.warp(100000201)
    elif choice == 3:
        sm.sendNext("You want to be a Thief? I don't really understand why, but all right. I'll send you to #bthe Dark Lord#k.")
        sm.createQuestWithQRValue(1406, "4")
        sm.warp(103000003)
    sm.chatScript("Please CC.")
