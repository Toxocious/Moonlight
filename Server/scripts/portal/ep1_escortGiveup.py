# [Grand Athenaeum] Ariant : Middle of the Desert
sm.removeEscapeButton()
sm.setSpeakerID(2510001)

answer = sm.sendSay("Hey, are you gonna leave me behind?!\r\n#b#L0#Keep escorting.#l\r\n#L1#Quit escorting for now.#l")
if answer == 0:
    sm.sendSayOkay("Good. Let's keep going.")
elif answer == 1:
    sm.sendNext("I knew it. It's too dangerous for one person to take on. Let's go back to #m302010000#.")
    sm.warpInstanceOut(302010000, 0)