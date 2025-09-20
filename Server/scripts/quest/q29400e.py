# Created by Manu13
# Description: Quest script for Veteran Medal ending
MEDAL = 1142004
QUEST = 29400
DALAIR = 9000040

sm.setSpeakerID(DALAIR)
# "mobCount=0;startTime=" + date + ";endTime=0;state=0"
qrValue = sm.getQRValue(29400)
# separar strings por ;
data = qrValue.split(";")
mobCount = int(data[0].split("=")[1])
maxMobCount = int(data[1].split("=")[1])
startTime = data[2].split("=")[1]
endTime = data[3].split("=")[1]
state = int(data[4].split("=")[1])


def isCurrentDateMajorThan30days():
    # Year/month/day
    currentTime = sm.getCurrentDateAsString()
    currentTime = currentTime.split("/")
    start = startTime.split("/")
    yearDiff = int(currentTime[0]) - int(start[0])
    monthDiff = int(currentTime[1]) - int(start[1])
    dayDiff = int(currentTime[2]) - int(start[2])
    if yearDiff > 0:
        return True
    if monthDiff > 0:
        return True
    if dayDiff >= 30:
        return True
    return False


def run():
    if state == 1:
        sm.sendSayOkay("You have already completed this quest.")
        sm.dispose()
    elif mobCount >= 100000:
        sm.giveItem(MEDAL)
        sm.sendSayOkay(
            "Congratulations! You have hunted 100,000 monsters and earned the title of Veteran Hunter! Here is your medal.")
        date = sm.getCurrentDateAsString()
        sm.setQRValue(QUEST,
                      "mobCount=100000;maxMobCount=100000;startTime=" + startTime + ";endTime=" + date + ";state=1")
        sm.completeQuestNoCheck(QUEST)
        sm.dispose()
        return
    elif isCurrentDateMajorThan30days():
        sm.sendSayOkay(
            "The 30-day period has ended. You did not reach the goal of hunting 100,000 monsters. Better luck next time!")

        sm.getChr().getQuestManager().removeQuest(29400)
        sm.dispose()
        return
    else:
        sm.sendSayOkay("Since " + startTime + ", you have hunted " + str(
            mobCount) + " monsters so far. Keep going to reach 100,000!")

    sm.dispose()


run()
