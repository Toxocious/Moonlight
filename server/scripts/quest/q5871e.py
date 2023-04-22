# id 5871 ([Maple Rewards] Easy Magnus Annihilation and Golden Glory), field 993017200
sm.setSpeakerID(9030200) # Worena
sm.setParam(1)
res = sm.sendAskYesNo("Whoa! You defeated the boss monster! \r\nI'll give you some #bReward Tokens#k for ridding Maple World of evil. Do you want them now?")
sm.createQuestWithQRValue(18192, "count=3;val2=90;tDate=19/06/23/11/48;val=110")
sm.createQuestWithQRValue(18192, "count=3;val2=110;tDate=19/06/23/11/48;val=110")
sm.createQuestWithQRValue(18192, "count=4;val2=110;tDate=19/06/23/11/48;val=110")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18442, "HR=0;VE=0;NO=0;GX=1;RM=0;KX=0;EM=1")
sm.setParam(0)
sm.sendNext("#b#i2431872# #t2431872# 20 obtained!#k \r\n\r\nThank you so much for helping us bring a little light back to Maple World.")
sm.sendPrev("If you take these Reward Points to the Cash Shop, you can #bget a discount on cash items#k. \r\n\r\nYou get to save some money AND gain honor by simply doing away with a dangerous boss monster. Two birds with one stone!")
