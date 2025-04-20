# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setNpcOverrideBoxChat(NERO)
sm.sendNext("#face1#OH! Good job. Lemme at those Evil Eye tails. I bet Three Moon can make something delicious with these. Hee hee.\r\n\r\n#b#i2010044#  #t2010044#")
sm
sm.giveItem(2010044, 30)
sm.completeQuest(parentID)
sm.giveExp(10500)
#Take 10 evil eyes
