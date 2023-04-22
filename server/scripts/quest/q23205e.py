SKILLS = [30010166, 30011167, 30011168, 30011169, 30011170]
ARKARIUM = 2159309

sm.completeQuestNoRewards(parentID)
sm.deleteQuest(parentID)
for i in range(5):
    if sm.hasSkill(SKILLS[i]):
        sm.removeSkill(SKILLS[i])  # remove the skill
sm.removeNpc(ARKARIUM)
sm.warpInstanceIn(927000070, 0)
