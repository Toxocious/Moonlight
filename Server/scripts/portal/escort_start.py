# [Grand Athenaeum] Ariant : Middle of the Desert
sm.removeEscapeButton()
sm.setSpeakerID(2510001)
if sm.hasQuest(32630):
    answer = sm.sendSay("Are you sure you can do this? I'm telling you, there's a load of big, bad monsters out there.\r\n#b#L0#Let's go.#l\r\n#L1#Let's wait.#l")
    if answer == 0:
        sm.sendNext("Wait for me!")
        sm.systemMessage("Starting the escort for Hatsar's Servant.")
        sm.warpInstanceIn(302010100, 0)
        sm.dispose()
    elif answer == 1:
        sm.addEscapeButton()
        sm.sendSayOkay("I told you it's too dangerous for one person!")
else:
    sm.sendSayOkay("Hey, are you gonna ignore me like that?!")