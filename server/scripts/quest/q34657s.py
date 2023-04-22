# 34657 (Second Fighting Tactic, Cadena 2nd job adv)
sm.setSpeakerID(3001200) # Gen
if chr.getJob() == 6400:
    sm.sendNext("Now that I've taught you, you're completing missions in record time. Well done.")
    sm.sendSay("At first, I wasn't sure you took this seriously, but it looks like I was wrong.")
    sm.sendSay("You seem pretty comfortable with that skill, so let's move on to the second battle skill.")
    # add stats
    sm.setJob(6410)
    sm.addAP(5)
    sm.addSP(7)
    sm.addMaxHP(350)
    sm.addMaxMP(200)
    sm.sendNext("These skills are a lot more challenging, but I'm sure you can handle 'em.")
    sm.sendSay("I think you've improved enough to handle more dangerous missions now. Open your skill window (K) and take a look.")
    sm.sendSayOkay("Keep practicing and getting experience to keep your skills in top shape.")
    sm.completeQuestNoCheck(parentID)