# 34658 (Third Fighting Tactic, Cadena 3rd job adv)
sm.setSpeakerID(3001200) # Gen
if chr.getJob() == 6410:
    sm.sendNext("Now that I've taught you further, even difficult missions are cleared without issue.")
    sm.sendSay("You seem pretty comfortable with your current skill set, so let's move on to the third battle skill. This will be the last time I will be teaching you.")
    # add stats
    sm.setJob(6411)
    sm.addAP(5)
    sm.addSP(7)
    sm.addMaxHP(350)
    sm.addMaxMP(200)
    sm.sendNext("These skills even more challenging, but I'm sure you can handle 'em.")
    sm.sendSayOkay("Keep practicing and getting experience to keep your skills in top shape.")
    sm.completeQuestNoCheck(parentID)