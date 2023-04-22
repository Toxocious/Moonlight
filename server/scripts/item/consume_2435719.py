# Tradable Nodestone - 2435902

from net.swordie.ms.constants import QuestConstants

sm.openNodestone(parentID)
if sm.hasQuestCompleted(QuestConstants.FIFTH_JOB_QUEST):
    sm.consumeItem(parentID)
