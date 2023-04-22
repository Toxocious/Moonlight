# id 940200600 (Savage Terminal : Back Alley), field 940200600
sm.showFieldEffect("lightning/screenMsg/0", 0)
sm.giveSkill(60021278, 1, 1)
max = 30
i = 0
while i < 30:
    i += 1
    sm.waitForMobDeath(2400315)
    sm.progressMessageFont(1, 20, 4, 0, "Hoodlums taught a lesson: " + str(i) + "/" + str(max))

sm.completeQuest(34601)
sm.removeSkill(60021278)
sm.warp(402000002)