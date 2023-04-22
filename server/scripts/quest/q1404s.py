sm.setSpeakerID(1052001)
sm.sendNext("So, you're the one Mai was talking about? #h #... I guess you do have some potential. You want to become a Thief? Do you knwo what Thieves are all about?")
sm.sendSay("Most people think of us as petty thieves who steal things, but that's not true at all. Thieves in Maple World are those who fight with sharp daggers and throwing stars from the shadows. We don't always fight fair, but we always fight to win.")
sm.sendSay("As a job, Thieves attack enemies with swift, powerful skills. Though their HP is a bit low, they make it up with speed, so you had better learn to dodge. High luck allows them to land critical hits often, as well.")
if sm.sendAskAccept("Now, will you join us on the path of Thieves? If you decide to doso, I will bring you to the #bsecret Thieves' Hideout in Kerning City#k using my power as the Job Instructor... You should feel honored. #rBut if you prefer a different job, I will help you find the other paths#k."):
    sm.warp(103000003)
    sm.startQuest(parentID)
else:
    choice = sm.sendNext("You don't wish to walk the path of a Thief? I will not force this path on someone who doesn't want it. WHich job do you want?\r\n\r\n#b#L0#Warrior#l\r\n#L1#Magician#l\r\n#L2#Bowman#l\r\n#L3#Pirate#l")
    if choice == 0:
        sm.sendNext("Warrior? I mean, if you want go with the trendy one, I'll send you to #bDances with Balrog#k.")
        sm.createQuestWithQRValue(1406, "1")
        sm.warp(102000003)
    elif choice == 1:
        sm.sendNext("Magician? I mean, if you want go with the trendy one, I'll send you to #bGrendel the really Old#k.")
        sm.createQuestWithQRValue(1406, "2")
        sm.warp(101000003)
    elif choice == 2:
        sm.sendNext("Bowman? I mean, if you want go with the trendy one, I'll send you to #bAthena Pierce#k.")
        sm.createQuestWithQRValue(1406, "3")
        sm.warp(100000201)
    elif choice == 3:
        sm.sendNext("Pirate? I mean, if you want go with the trendy one, I'll send you to #bKyrin#k.")
        sm.createQuestWithQRValue(1406, "5")
        sm.warp(120000101)
    sm.chatScript("Please CC.")
