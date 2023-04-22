# id 26511 ([Maple Rewards] Theme Dungeon: Annihilate Captain Darkgoo), field 993017200
sm.setSpeakerID(9030200) # Worena
sm.setParam(1)
res = sm.sendAskYesNo("Whoa! You defeated the boss monster! \r\nI'll give you some #bReward Tokens#k for ridding Maple World of evil. Do you want them now?")
sm.createQuestWithQRValue(18192, "count=1;val2=20;tDate=19/06/23/11/48;val=40")
sm.createQuestWithQRValue(18192, "count=1;val2=40;tDate=19/06/23/11/48;val=40")
sm.createQuestWithQRValue(18192, "count=2;val2=40;tDate=19/06/23/11/48;val=40")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18272, "A=1;B=0;C=1;D=0;E=0;F=0;G=0;H=0;I=0;J=0;K=0;L=0;M=0")
sm.setParam(0)
sm.sendNext("#b#i2431872# #t2431872# 20 obtained!#k \r\n\r\nThank you so much for helping us bring a little light back to Maple World.")
sm.sendPrev("If you take these Reward Points to the Cash Shop, you can #bget a discount on cash items#k. \r\n\r\nYou get to save some money AND gain honor by simply doing away with a dangerous boss monster. Two birds with one stone!")
