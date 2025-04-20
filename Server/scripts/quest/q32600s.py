# [Grand Athenaeum] Episode 1 - The White Mage
sm.setSpeakerID(2500000)
if sm.hasQuest(32603):
    if sm.sendAskYesNo("Do you want to finish reading Ep 1. <The White Mage>?\r\n#b(Return to previous location.)#k"):
        sm.warp(302090000, 0)
        sm.dispose()
else:
    if sm.sendAskYesNo("Do you want to read Ep 1. <The White Mage>?"):
        sm.sendNext("You selected #eThe White Mage#n.")
        sm.sendSay("You will re-live the experiences from the past. Don't worry though, your actions will not change the future.")
        sm.sendSay("#h0#, you will go back in time and become a #e#bmercenary#k#n. Their identity was lost to history, but we know they existed.")
        sm.sendSay("#fNpc/2500001.img/stand/0#\r\nClick me any time you want to return to reality. I will always be somewhere in the story.")
        if sm.hasQuestCompleted(32629):
            sm.warp(302010000, 0)
        elif sm.hasQuestCompleted(32630):
            sm.warp(302010400, 0)
        else:
            sm.warp(302090000, 0)