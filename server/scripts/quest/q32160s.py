# [Riena Strait] Get it Strait

mapid = 140000000

sm.setSpeakerID(1105012)
response = sm.sendAskYesNo("Are you #b#h0##k?\r\n"
                "We need your help! We have noticed weird changes going on around the Rien island\r\nAre you able to help?\r\n\r\n"
                "(accepting will warp you)")

if response:
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.warp(mapid, 0)
sm.dispose()
