# 140090100
PUKA = 1202001

sm.setSpeakerID(PUKA)
sm.sendNext("We've been digging and digging inside the Ice Cave in the hope of finding a hero, but I never thought I'd actually see the day... The prophecy was true! You were right, #p1201000#! Now that one of the legendary heroes has returned, we have no reason to fear the Black Mage!")
sm.sendSay("Oh, I've kept you too long. I'm sorry, I got a little carried away. I'm sure the other Penguins feel the same way. I know you're busy, but could you #bstop and talk to the other Penguins#k on your way to town? They would be so honored.\r\n\r\n#fUI/UIWindow2.img/QuestIcon/4/0# \r\n#i2000022# 5 #t2000022#\r\n#i2000023# 5 #t2000023#\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 16 exp")
sm.giveItem(2000022, 5)
sm.giveItem(2000023, 5)
sm.giveExp(16)
sm.completeQuest(parentID)

sm.removeEscapeButton()
sm.flipSpeaker()
sm.sendNext("Oh, you've leveled up! You may have even received some skill points. In Maple World, you can acquire 3 skill points every time you level up. Press the #bK key#k to view the Skill window.")
sm.sendSay("#b(Everyone's been so nice to me, but I just can't remember anything. Am I really a hero? I should check my skills and see. But how do I check them?)#k")
sm.tutorAutomatedMsg(15)
