# Hayato Tutorial Clipsence | Momijigaoka : Unfamiliar Hillside (807040000)

jobId = 0
if chr.getJob() == 4002:
    jobId = 4200
elif chr.getJob() == 4001:
    jobId = 4100
if jobId == 0: #won't give exp and set job every entrance like before
    sm.dispose()
sm.giveExp(3500)
sm.jobAdvance(jobId)
sm.resetAP(False, jobId)
sm.lockInGameUI(True)
#sm.playVideoByScript("JPHayato.avi")
sm.lockInGameUI(False)
