# Nodestone  |  (2435902)
from net.swordie.ms.constants import QuestConstants

if sm.hasQuestCompleted(QuestConstants.FIFTH_JOB_QUEST):
    if sm.hasItem(parentID):
        sm.openNodestone(2435902)
        sm.consumeItem(parentID)
