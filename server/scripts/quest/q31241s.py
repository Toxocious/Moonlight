# id 31241 ([Crimsonheart] Message from Tynerum), field 101000003
sm.setSpeakerID(1032001) # Grendel the Really Old
sm.sendNext("Before we proceed, you must understand that the #bGrand Athenaeum#k is no simple archive of books. There is great magic here.")
sm.sendSay("It is a living, breathing space, where all the knowledge in the universe is stored. Much like the Akashic Records of old, where magicians who surpassed time and space gathered to share their knowledge. Rather grandiose, is it not?")
sm.sendSay("The records there can be rather troublesome. Some of the books seem to have personalities of their own, flitting about from room to room. There is a rather peculiar tome that seems to have taken a liking to the coffee percolator in my reading room. No matter how often I return it to its rightful place, it returns to my chambers.")
res = sm.sendAskYesNo("Aren't you curious to know what that book contained?")
sm.setParam(1)
sm.sendNext("Luckily, as I was completing my morning routine, I found the book open to a page full of odd writing. Once I deciphered the meaning, I realized it was a call for help. It said...\r\n\r\n#bTynerum is on the verge of collapse. Chaos rules, law is gone. The demons of Shadow Veil Forest have fallen to ruin. Send help. \r\n- Ridley#k")
sm.sendSay("The name #bTynerum#k brings to mind stirrings of knowledge I had thought forgotten. I can recall little, though I believe it may have once been the #bhome of a race of demons#k.")
sm.sendSay("I believe this strange message could warrant an investigation. Particularly with the string of Demons that have arrived in Maple World as of late.")
sm.sendSay("Tynerum could give us a better understanding of the demon's true intentions... and what happened in the past that led us to where we are today.")
sm.startQuest(parentID)
sm.completeQuestNoCheck(parentID)
