# Soaring skill quest

from net.swordie.ms.constants import SkillConstants

sm.setSpeakerID(2085000)
sm.sendNext("Whoa, you've brought it! Just hold on a minute! I'll make you the special potion.")
sm.sendSay("Alrighty! Are you ready? If you're are, I will go ahead and sprinkle this potion on you. You'll be able to fly then!")
sm.consumeItem(4032531)
sm.completeQuestNoRewards(parentID)
sm.giveSkill(SkillConstants.getSoaringByJob(chr.getJob()))
sm.chatScript("You have obtained the Soaring skill!")
sm.sendPrev("Ok. Looks like you're all set to use the Soaring skill. There's one thing you should keep in mind. You can only use the Soaring skill where there's Dragon energy. The only such place that I know of is the Crimson Sky Dock.")
