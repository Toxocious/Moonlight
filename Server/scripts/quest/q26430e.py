# id 26430 ([Maple Rewards] Annihilate Normal Zakum for Riches and Honor!), field 993017200
sm.setSpeakerID(9030200) # Worena
sm.setParam(1)
res = sm.sendAskYesNo("Whoa! You defeated the boss monster! \r\nI'll give you some #bReward Tokens#k for ridding Maple World of evil. Do you want them now?")
sm.createQuestWithQRValue(18192, "count=4;val2=110;tDate=19/06/23/11/48;val=160")
sm.createQuestWithQRValue(18192, "count=4;val2=160;tDate=19/06/23/11/48;val=160")
sm.createQuestWithQRValue(18192, "count=4;val2=160;tDate=19/06/23/11/49;val=160")
sm.createQuestWithQRValue(18192, "count=5;val2=160;tDate=19/06/23/11/49;val=160")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18194, "ML=0;MM=0;MN=0;MO=0;MA=1;MB=0;MC=0;MD=0;ME=0;MF=0;MG=0;MH=0;MI=0;MJ=0;MK=0")
sm.setParam(0)
sm.sendNext("#b#i2431872# #t2431872# 50 obtained!#k \r\n\r\nThank you so much for helping us bring a little light back to Maple World.")
sm.sendPrev("If you take these Reward Points to the Cash Shop, you can #bget a discount on cash items#k. \r\n\r\nYou get to save some money AND gain honor by simply doing away with a dangerous boss monster. Two birds with one stone!")
