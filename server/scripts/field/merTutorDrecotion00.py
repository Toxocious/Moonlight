# Black Road | After the Battle
# Field ID: 910150000

sm.heal(True) # has less than full hp/mp at start for some reason

if sm.sendAskYesNo("Would you like to skip the introduction?"):
    sm.startQuestNoCheck(24005)
    sm.warp(101050010)
    sm.dispose()
sm.giveSkill(20021181, 1)
sm.playVideoByScript("Mercedes.avi")
