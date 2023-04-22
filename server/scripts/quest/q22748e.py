# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setNpcOverrideBoxChat(NERO)
sm.sendNext("#face1#Do you have them all? Gimme, gimme.")
sm.sendSay("#face1#Woo! Cold Eye tails! Three Moon will make some awesome soup from this.\r\n\r\n#b#i2010045#  #t2010045#")
sm.giveItem(2010045, 10)
sm.completeQuest(parentID)
sm.giveExp(12500)
#take 10 cold eye tails
sm.sendSayOkay("#face0#You're getting there, Kinesis. Once you get to #bLevel 30#k I can send you back to Three Moon.")