# [Silent Crusade] Chance? Or Fate?

STARLING = 9120221
BLACK_WING_HENCHMAN = 9300470

sm.setSpeakerID(STARLING)
sm.sendNext("They're going to launch their attack any moment! "
            "We'll all be beaten if we let it happen! Take them down!")

for i in range(7):
    sm.spawnMob(BLACK_WING_HENCHMAN, -391, 64, False)
sm.startQuest(parentID)