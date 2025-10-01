# 7707 - Midnight Rest Quest (just keeps spamming until you complete it)

# 7707 - Midnight Rest Quest
# Source: starts with Maple Administrator, talk-only, no known rewards.
# Place: scripts/quest/q7707.py
MAPLE_ADMIN = 2007
QUEST_ID = 7707


def run():
    if sm.getChr().getLevel() >= 15 and sm.getChr().getField().isTown():

        sm.setSpeakerID(MAPLE_ADMIN)
        sm.removeEscapeButton()

        if sm.hasQuestCompleted(QUEST_ID):
            sm.dispose()
            return

        if not sm.hasQuest(QUEST_ID):
            # Auto-start if invoked from notifier/light-bulb
            sm.startQuest(QUEST_ID)

        # Simple informative line; talk-only quest
        sm.sendNext("Are you interested in soar through the sky? ")

        sm.sendSayOkay(
            "Meet #bIrvin Instructor#k in the Station of Victoria, Ereve, Orbis, Mu Lung, Ariant, Edelstein or Leafre.")
        sm.completeQuestNoRewards(QUEST_ID)
        sm.completeQuest(QUEST_ID)
        sm.dispose()
    else:
        sm.dispose()


run()
