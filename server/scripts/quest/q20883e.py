# 20883 - [Job Adv] (Lv.60)   Cygnus Knight
sm.setSpeakerID(1404009)
sm.sendNext("#h # it is fortunate that you protected the book from the Black Mage. It is a book with tremendous value. I must admit your braveness for this.")
if sm.sendAskYesNo("The Queen has appointed you the title of nobility for your actions, do you wish to accept it?"):
    sm.sendSayOkay("#h #, from now on you are an elite member. You will be given quests with higher level, but you will manage.")
    chrJobID = sm.getChr().getJob()
    sm.jobAdvance(chrJobID+1)
    sm.completeQuest(parentID)
    sm.giveItem(1142068)
else:
    sm.sendSayOkay("Let me know when you wish to accept the title.")
