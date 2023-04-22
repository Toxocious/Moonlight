# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
NERO = 1531003
OLD_MAN = 1531012
sm.setIntroBoxChat(OLD_MAN)
sm.sendNext("Did Three Moon give you his approval yet?")
sm.sendSay("Looks like you have much better control over your powers now. I think it is safe for you to return to the #bsinkhole#k now.")
sm.lockForIntro()
sm.removeAdditionalEffect()
sm.sendNext("I will open a doorway back for you. This is different from the #bsinkhole#k, and is far #bsafer#k.")

sm.sendDelay(500)
sm.startQuest(22751)
sm.completeQuest(22751)
sm.sendDelay(500)

sm.unlockForIntro()
sm.sendNext("Our Magician Association will continue to watch the #bWhite Mage#k and the #bsinkhole#k from Maple World. #bNero#k will be our point of contact.")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face2#So my name is going to be just Nero from now on?")

sm.setIntroBoxChat(OLD_MAN)
sm.sendSay("I am needed elsewhere. Before I go, let me teach you how to move between worlds yourself. I think this knowledge will serve us all well.\r\n#b(You can now teleport to your hideout when you use your return skill.)#k")
sm.sendSay("And take this, to commemorate your visit to a new world.\r\n\r\n#b#i3015244#  #t3015244#")

sm.giveItem(3015244)
sm.giveSkill(140001290, 1, 1)
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(5500)